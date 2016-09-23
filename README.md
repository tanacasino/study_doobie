# Study doobie (with http4s and circe) #

Simple API Server using http4s, circe and doobie


## Run ##

```
$ sbt
[study-doobie] $ console

# Run
scala> val server = com.github.tanacasino.study.Hello.server(List()).run

# Stop
scala> server.shutdownNow
```

## Note ##

```
$ curl -i -v -X POST -H "Content-Type: application/json" http://localhost:9000/v1/api/users -d'{"name": "tanaka2", "password": "password", "isAdmin": true}'
*   Trying ::1...
* connect to ::1 port 9000 failed: Connection refused
*   Trying 127.0.0.1...
* Connected to localhost (127.0.0.1) port 9000 (#0)
> POST /v1/api/users HTTP/1.1
> Host: localhost:9000
> User-Agent: curl/7.43.0
> Accept: */*
> Content-Type: application/json
> Content-Length: 60
>
* upload completely sent off: 60 out of 60 bytes
< HTTP/1.1 200 OK
HTTP/1.1 200 OK
< Content-Type: application/json
Content-Type: application/json
< Date: Fri, 23 Sep 2016 08:53:17 GMT
Date: Fri, 23 Sep 2016 08:53:17 GMT
< Content-Length: 40
Content-Length: 40

<
* Connection #0 to host localhost left intact
{"id":8,"name":"tanaka2","isAdmin":true} 



$ curl -i -v http://localhost:9000/v1/api/users/8
*   Trying ::1...
* connect to ::1 port 9000 failed: Connection refused
*   Trying 127.0.0.1...
* Connected to localhost (127.0.0.1) port 9000 (#0)
> GET /v1/api/users/8 HTTP/1.1
> Host: localhost:9000
> User-Agent: curl/7.43.0
> Accept: */*
>
< HTTP/1.1 200 OK
HTTP/1.1 200 OK
< Content-Type: application/json
Content-Type: application/json
< Date: Fri, 23 Sep 2016 08:53:25 GMT
Date: Fri, 23 Sep 2016 08:53:25 GMT
< Content-Length: 40
Content-Length: 40

<
* Connection #0 to host localhost left intact
{"id":8,"name":"tanaka2","isAdmin":true} 



❯ curl -i -v http://localhost:9000/v1/api/users/0
*   Trying ::1...
* connect to ::1 port 9000 failed: Connection refused
*   Trying 127.0.0.1...
* Connected to localhost (127.0.0.1) port 9000 (#0)
> GET /v1/api/users/0 HTTP/1.1
> Host: localhost:9000
> User-Agent: curl/7.43.0
> Accept: */*
>
< HTTP/1.1 404 Not Found
HTTP/1.1 404 Not Found
< Date: Fri, 23 Sep 2016 08:53:28 GMT
Date: Fri, 23 Sep 2016 08:53:28 GMT
< Content-Length: 0
Content-Length: 0

<
* Connection #0 to host localhost left intact



$ curl -i -v http://localhost:9000/v1/api/users
*   Trying ::1...
* connect to ::1 port 9000 failed: Connection refused
*   Trying 127.0.0.1...
* Connected to localhost (127.0.0.1) port 9000 (#0)
> GET /v1/api/users HTTP/1.1
> Host: localhost:9000
> User-Agent: curl/7.43.0
> Accept: */*
>
< HTTP/1.1 200 OK
HTTP/1.1 200 OK
< Content-Type: application/json
Content-Type: application/json
< Date: Fri, 23 Sep 2016 08:53:22 GMT
Date: Fri, 23 Sep 2016 08:53:22 GMT
< Content-Length: 82
Content-Length: 82

<
* Connection #0 to host localhost left intact
[{"id":7,"name":"tanaka","isAdmin":true},{"id":8,"name":"tanaka2","isAdmin":true}] 

```

