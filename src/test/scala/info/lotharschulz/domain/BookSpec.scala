package info.lotharschulz.domain

import org.specs2.mutable.Specification

class BookSpec extends Specification {
  "Book" should {
    "contains an bookFormat" in {
      val bf = BookJsonProtocol.bookFormat
      bf must not be empty
    }
  }
}