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
      /*
*/
      Post("/book", HttpEntity(ContentType(`application/json`, `UTF-8`), jsonBook)) ~> route ~> check {
        // , FormData(Seq("title" -> "icke")) .... , Map("title" -> "icke")
        //status should be equalTo Created
        //status === StatusCodes.OK
        println("mediaType: %s" format (mediaType))
        status === StatusCodes.Created
        //mediaType === MediaTypes.`application/json`
      }
      /*
Das geht wenn ' entity(as[String]){ book => ' in zeile 32
      Post("/book", FormData(Seq("x" -> "1"))) ~> route ~> check {
        responseAs[String] === "x=1"
      }
    }
*/
    }
  }

  /*
http://spray.io/documentation/1.2.3/spray-routing/
https://github.com/spray/spray/tree/release/1.2/spray-routing-tests/src/test/scala/spray/routing
http://spray.io/documentation/1.2.2/spray-testkit/
https://github.com/spray/spray/blob/release/1.2/spray-routing-tests/src/test/scala/spray/routing/AnyParamDirectivesSpec.scala
http://sysgears.com/articles/scala-rest-api-integration-testing-with-spray-testkit/
https://www.google.de/search?q=%22Request+was+rejected+with+List%28UnsupportedRequestContentTypeRejection%28Expected+%27application%2Fjson%27%29%22
http://stackoverflow.com/questions/27073173/in-spray-how-to-unmarshall-plain-text
http://stackoverflow.com/questions/30212905/not-able-to-set-header-for-content-type-request-in-spray
   */


  "MyService" should {
    "HTTP verb PUT not supported" in {
      Put() ~> sealRoute(route) ~> check {
        status === StatusCodes.NotFound
      }
    }
  }
}