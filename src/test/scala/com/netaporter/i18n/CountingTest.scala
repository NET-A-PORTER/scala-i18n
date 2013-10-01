package com.netaporter.i18n

import org.scalatest.{OptionValues, WordSpec}
import org.scalatest.matchers.ShouldMatchers
import java.util.Locale

/**
 * Date: 01/10/2013
 * Time: 08:15
 */
class CountingTest
  extends WordSpec
  with ShouldMatchers
  with OptionValues {

  trait CountingTestCase {
    val messages = ResourceBundle("counting-test/counting")
    val noLang = Locale.ROOT
    val german = Locale.GERMAN
  }

  "keySet" should {
    "have size 5 for root locale" in new CountingTestCase {
      messages.keySet(noLang).size should equal(5)
    }

    "have size 10 for german locale" in new CountingTestCase {
      messages.keySet(german).size should equal(10)
    }
  }

  "iterator" should {
    "have size 5 for root locale" in new CountingTestCase {
      messages.iterator(noLang).size should equal(5)
    }

    "have size 10 for german locale" in new CountingTestCase {
      messages.iterator(german).size should equal(10)
    }
  }
}
