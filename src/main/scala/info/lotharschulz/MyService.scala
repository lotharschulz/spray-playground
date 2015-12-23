package info.lotharschulz

import java.util.concurrent.TimeUnit

import akka.actor.Props
import akka.util.Timeout
import info.lotharschulz.book.BookActor
import org.json4s.{Formats, DefaultFormats}
import spray.http.StatusCodes._
import spray.httpx.Json4sSupport
import spray.routing._
import akka.pattern.ask

object Json4sProtocol extends Json4sSupport {
  implicit def json4sFormats: Formats = DefaultFormats
}

case class Entity(payload: String)

trait MyService extends HttpService {
  import Json4sProtocol._
  import BookActor._

  implicit def executionContext = actorRefFactory.dispatcher
  implicit val timeout = Timeout.apply(5L, TimeUnit.SECONDS)

  val bookworker = actorRefFactory.actorOf(Props[BookActor], "worker")

  val route = {
    path("book") {
      get {
        complete(List(Entity("smth"), Entity("smth more")))
      } ~
        post {
          respondWithStatus(Created) {
            entity(as[Entity]) { someObject =>
              doCreate(someObject)
            }
          }
        }
    }
  }

  def doCreate[T](ent: Entity) = {
    complete {
      (bookworker ? Create(ent))
        .mapTo[BookActor]
        .map(result => s"I got a response: ${result}")
        .recover { case _ => "error" }
    }
  }

}