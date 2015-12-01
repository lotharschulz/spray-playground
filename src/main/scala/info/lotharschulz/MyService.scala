package info.lotharschulz

import akka.actor.{ActorContext, ActorLogging, Actor}
import info.lotharschulz.domain.{Author, Book}
import spray.routing._
import spray.http._

class MyServiceActor extends Actor with MyService with ActorLogging{

  def actorRefFactory:ActorContext = context
  def receive:Receive = runRoute(route)
}

trait MyService extends HttpService {

  val defaultRoute = {
    path("") & get & respondWithMediaType(MediaTypes.`application/json`) & complete((spray.http.StatusCodes.OK ,"{\nhello\n}"))
  }
  val bookRoute = {
    path("book") & get & respondWithMediaType(MediaTypes.`application/json`) & complete((spray.http.StatusCodes.OK ,Book("title").toString))
  }
  val authorRoute = {
    path("author") & get & respondWithMediaType(MediaTypes.`application/json`) & complete((spray.http.StatusCodes.OK , Author("author's name").toString ))
  }

  val route = defaultRoute ~ bookRoute ~ authorRoute
}