package com.github.tanacasino.study

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

  case class User(id: Long, name: String, age: Int)

  def getUser(userId: Long): Task[User] = Task {
    User(1L, "tanaka", 30)
  }

  def listUsers(): Task[Seq[User]] = Task {
    Seq(User(1L, "tanaka", 30), User(2L, "nakata", 33))
  }

  val userService = HttpService {
    case request @ GET -> Root / "users" =>
      Ok(listUsers().map(_.asJson))
    case request @ GET -> Root / "users" / IntVar(userId) =>
      Ok(getUser(userId).map(_.asJson))
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
