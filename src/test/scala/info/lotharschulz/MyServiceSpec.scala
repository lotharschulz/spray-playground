package info.lotharschulz

import org.specs2.mutable.Specification
import spray.http._
import spray.testkit.Specs2RouteTest
import spray.http.StatusCodes.NotFound

class MyServiceSpec extends Specification with Specs2RouteTest with MyService  {
  def actorRefFactory = system
  "MyService" should {
    "return a json list" in {
      import Json4sProtocol._
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

  "MyService" should {
    "create a new book" in {
      Post("/book", FormData(Map("title" -> "icke"))) ~> route ~> check {
        //status should be equalTo Created
        status === StatusCodes.OK
        mediaType === MediaTypes.`application/json`
      }
    }
  }

  "MyService" should {
    "not support PUT verb" in {
      Put() ~> sealRoute(route) ~> check {
        status === NotFound
      }
    }
  }
}