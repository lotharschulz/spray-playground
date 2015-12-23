package info.lotharschulz

import akka.actor._
import akka.io.IO
import akka.util.Timeout
import spray.can.Http

import scala.concurrent.duration._

object Boot extends App {

  implicit val system:ActorSystem = ActorSystem("spray-playground")
  system.registerOnTermination { system.log.info("Shutdown") }

  val service = system.actorOf(Props[MyServiceActor], "spray-playground-service")
  implicit val timeout = Timeout(2.seconds)
  IO(Http) ! Http.Bind(service, system.settings.config.getString("service.host"), system.settings.config.getInt("service.port"))
}

class MyServiceActor extends Actor with MyService with ActorLogging{

  def actorRefFactory:ActorContext = context
  def receive:Receive = runRoute(route)
}
