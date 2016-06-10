package info.lotharschulz

import org.specs2.mutable.Specification
import spray.http.{HttpEntity, ContentType, StatusCodes, MediaTypes}
import spray.testkit.Specs2RouteTest

class MyServiceSpec extends Specification with Specs2RouteTest with MyService  {
  def actorRefFactory = system
  "MyService" should {
    "return a json list" in {
      import BookJsonFormat._
      Get("/book") ~> route ~> check {
        val res = responseAs[List[Book]]
        res.size === 2
        res(0).title must be equalTo "smth"
        res(1).title === "smth more"
        status === StatusCodes.OK
        mediaType === MediaTypes.`application/json`
      }
    }
  }

  val jsonBook = """{"title": "icke"}"""

  "MyService" should {
    "create a new book" in {
      //Post("/book", HttpEntity(ContentType(`application/json`, `UTF-8`), jsonBook)) ~> route ~> check {
      Post("/book", jsonBook) ~> route ~> check {
        println("mediaType: %s" format (mediaType))
        status === StatusCodes.OK
      }
    }
  }

  "MyService" should {
    "HTTP verb PUT not supported" in {
      Put() ~> sealRoute(route) ~> check {
        status === StatusCodes.NotFound
      }
    }
  }
}