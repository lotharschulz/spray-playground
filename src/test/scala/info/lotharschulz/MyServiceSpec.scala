package info.lotharschulz

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._

class MyServiceSpec extends Specification with Specs2RouteTest with MyService {
  def actorRefFactory = system
  
  "MyService" should {

    "return hello for GET requests to the root path" in {
      Get() ~> route ~> check {
        responseAs[String] must contain("hello")
        status === StatusCodes.OK
        mediaType === MediaTypes.`application/json`
      }
    }

    "return /book called GET requests to /book" in {
      Get("/book") ~> route ~> check {
        responseAs[String] === ("Book(title)")
        status === StatusCodes.OK
        mediaType === MediaTypes.`application/json`
      }
    }

    "return author's name called GET requests to /author" in {
      Get("/author") ~> route ~> check {
        responseAs[String] must contain("Author(author's name)")
        status === StatusCodes.OK
        mediaType === MediaTypes.`application/json`
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put() ~> sealRoute(route) ~> check {
        status === StatusCodes.MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    }
  }
}