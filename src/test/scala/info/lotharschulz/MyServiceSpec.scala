package info.lotharschulz

import org.specs2.mutable.Specification
import spray.http.{MediaTypes, StatusCodes}
import spray.testkit.Specs2RouteTest

class MyServiceSpec extends Specification with Specs2RouteTest with MyService  {
  def actorRefFactory = system
  "MyService" should {
    "return a json list" in {
      import Json4sProtocol._
      Get("/book") ~> route ~> check {
        val res = responseAs[List[Entity]]
        res.size === 2
        res(0).payload must be equalTo "smth"
        res(1).payload === "smth more"
        status === StatusCodes.OK
        mediaType === MediaTypes.`application/json`
      }
    }
  }
}