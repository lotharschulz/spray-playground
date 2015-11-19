package info.lotharschulz.domain
import spray.json.DefaultJsonProtocol

sealed case class Book (title: String)

object BookJsonProtocol extends DefaultJsonProtocol {
  implicit val bookFormat = jsonFormat1(Book)
}