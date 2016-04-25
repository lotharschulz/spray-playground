package info.lotharschulz

import java.util.concurrent.TimeUnit

import spray.json.DefaultJsonProtocol

import akka.util.Timeout
import spray.http._
import spray.httpx.SprayJsonSupport
import spray.routing._

case class Book(title: String)

object Json4sProtocol extends DefaultJsonProtocol with SprayJsonSupport {
  implicit val PortfolioFormats = jsonFormat1(Book)
}

trait MyService extends HttpService {
  import Json4sProtocol._

  implicit def executionContext = actorRefFactory.dispatcher
  implicit val timeout = Timeout.apply(5L, TimeUnit.SECONDS)

  val route = {
    path("book") {
      get {
        complete(List(Book("smth"), Book("smth more")))
      } ~
        post {
          respondWithMediaType(MediaType.custom("application/json")) {
            formFields('title.as[String]) { (title) =>
              complete {"{\"book\": { \"title\":\"%s\"]} }" format (title)
              }
            }
          }
        }
    }
  }
}