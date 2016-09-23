package com.github.tanacasino.study

import doobie.imports._
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._
import org.http4s.server.blaze._
import org.http4s.server.{ Server, ServerApp }
import scalaz.concurrent.Task

object Hello extends ServerApp with CirceInstances {

  val helloWorldService = HttpService {
    case GET -> Root / "hello" / name =>
      Ok(s"Hello, $name.")
  }

  val xa = DriverManagerTransactor[Task](
    "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/study_doobie", "root", ""
  )

  case class UserForm(name: String, password: String, isAdmin: Boolean)

  case class User(id: Long, name: String, isAdmin: Boolean)

  def convertToUser(record: Records.UserRecord): User = {
    User(record.userId, record.name, record.isAdmin)
  }

  def getUser(userId: Long): Task[Option[User]] = {
    Records.find(userId).transact(xa).map(_.map(convertToUser))
  }

  def listUsers(): Task[Seq[User]] = {
    Records.findAll().transact(xa).map(_.map(convertToUser))
  }

  def createUser(form: UserForm): Task[User] = {
    Records.insert(form.name, form.password, form.isAdmin).transact(xa).map(convertToUser)
  }

  val userService = HttpService {
    case request @ GET -> Root / "users" =>
      Ok(listUsers().map(_.asJson))
    case request @ GET -> Root / "users" / IntVar(userId) =>
      getUser(userId).flatMap {
        case Some(user) => Ok(user.asJson)
        case None => NotFound()
      }
    case request @ POST -> Root / "users" =>
      request.as(jsonOf[UserForm]).flatMap { form =>
        Ok(createUser(form).map(_.asJson))
      }
  }

  override def server(args: List[String]): Task[Server] = {
    println(s"Start with args $args")
    BlazeBuilder
      .bindHttp(9000, "localhost")
      .mountService(helloWorldService, "/")
      .mountService(userService, "/v1/api")
      .start
  }

}

object Records {

  case class UserRecord(userId: Long, name: String, password: String, isAdmin: Boolean)

  def find(userId: Long): ConnectionIO[Option[UserRecord]] = {
    sql"SELECT user_id, name, password, is_admin FROM users WHERE user_id = $userId".query[UserRecord].option
  }

  def findAll(): ConnectionIO[List[UserRecord]] = {
    sql"SELECT user_id, name, password, is_admin FROM users".query[UserRecord].list
  }

  def insert(name: String, password: String, isAdmin: Boolean): ConnectionIO[UserRecord] = {
    for {
      id <- sql"INSERT INTO users (name, password, is_admin) VALUES ($name, $password, $isAdmin)".update.withUniqueGeneratedKeys[Long]("user_id")
      u <- sql"SELECT user_id, name, password, is_admin FROM users WHERE user_id = $id".query[UserRecord].unique
    } yield u
  }

}
