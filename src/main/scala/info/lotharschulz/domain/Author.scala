package info.lotharschulz.domain
import spray.json.DefaultJsonProtocol

case class Author (name: String)

object AuthorJsonProtocol extends DefaultJsonProtocol {
  implicit val authorFormat = jsonFormat1(Author)
}
