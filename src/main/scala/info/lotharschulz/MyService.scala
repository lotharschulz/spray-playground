package info.lotharschulz

import java.util.concurrent.TimeUnit

import spray.json.DefaultJsonProtocol

import akka.util.Timeout
import spray.http._
import spray.httpx.SprayJsonSupport
import spray.routing._

case class Book(title: String)

object BookJsonFormat extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val BookFormat = jsonFormat1(Book)
}

trait MyService extends HttpService {
  import BookJsonFormat._

  implicit def executionContext = actorRefFactory.dispatcher
  implicit val timeout = Timeout.apply(5L, TimeUnit.SECONDS)

  val route = {
    path("book") {
      get {
        complete(List(Book("smth"), Book("smth more")))
      } ~
      post {
        //respondWithMediaType(MediaType.custom("application/json")) {
          //entity(as[Book]){ book =>
          entity(as[String]){ book =>
              //println("book.title : %s" format (book.title) )
              println("book.title: %s" format (book) )
              complete (book)
          }
        //}
      }
    }
  }
}