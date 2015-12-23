package info.lotharschulz.book
import akka.actor.{Actor, ActorLogging}
import info.lotharschulz.Entity

object BookActor {
  case class Create(entitiy: Entity)
  case class Book (title: String = "")
}

class BookActor extends Actor with ActorLogging {
  import BookActor._

  def receive = {
    case Create(ent) => {
      log.info(s"Create ${ent}")
      sender ! Book("new title")
    }
  }
}