package info.lotharschulz.domain

import org.specs2.mutable.Specification

class AuthorSpec extends Specification {
  "Author" should {
    "contains an authorFormat" in {
      val af = AuthorJsonProtocol.authorFormat
      af must not be empty
    }
  }
}
