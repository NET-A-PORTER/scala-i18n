package com.netaporter.i18n

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{OptionValues, WordSpec}
import java.util.{MissingResourceException, Locale}

/**
 * Date: 30/09/2013
 * Time: 18:07
 */
class ResourceBundleTest
  extends WordSpec
  with ShouldMatchers
  with OptionValues {

  trait BasicTestCase {
    val messages = ResourceBundle("basic-test/messages")
    val noLang = Locale.ROOT
    val english = Locale.ENGLISH
    val german = Locale.GERMAN
    val french = Locale.FRENCH
    val canadaFrench = Locale.CANADA_FRENCH
  }

  "get" should {
    "return default message for no Locale" in new BasicTestCase {
      messages.get("greeting", noLang) should equal("Hello")
    }

    "return language specific message" in new BasicTestCase {
      messages.get("greeting", german) should equal("hallo")
    }

    "return throws exception for message that does not exist" in new BasicTestCase {
      intercept[MissingResourceException] {
        messages.get("does not exist", english)
      }
    }

    "return country and language specific message" in new BasicTestCase {
      messages.get("greeting", canadaFrench) should equal("bonjour Canada")
    }

    "fallback to language specific message when country+language specific message does not exist" in new BasicTestCase {
      //Our locale is canadaFrench, but the key farewell doesn't exists in the fr_CA file
      //Instead fallback to the fr file
      messages.get("farewell", canadaFrench) should equal("au revoir")
    }

    "fallback to root message when specific message does not exist" in new BasicTestCase {
      val res = messages.get("only.in.default", canadaFrench)
      res should equal("This message is not in any of the language specific resource bundles")
    }

    "return default for message that does not exist when default supplied" in new BasicTestCase {
      val res = messages.getOrElse("does not exist", english, "i am default")
      res should equal("i am default")
    }

    "does not evaluate default when message exists" in new BasicTestCase {
      var called = false
      val res = messages.getOrElse("greeting", english, { called = true; "Jeff" })
      called should equal(false)
    }

    "return message with params" in new BasicTestCase {
      messages.getWithParams("welcome", english, "Jeff") should equal("Welcome Jeff")
    }
  }

  "find" should {
    "return Some for message that exists" in new BasicTestCase {
      messages.find("greeting", german) should equal(Some("hallo"))
    }

    "return None for message that does not exist" in new BasicTestCase {
      messages.find("does not exist", english) should equal(None)
    }

    "return message with params" in new BasicTestCase {
      messages.findWithParams("welcome", english, "Jeff") should equal(Some("Welcome Jeff"))
    }

    "return None for non existent message with params" in new BasicTestCase {
      val res = messages.findWithParams("does not exist", english, "Jeff")
      res should equal(None)
    }
  }

  "contains" should {
    "return true for message that exists" in new BasicTestCase {
      messages.contains("greeting", french) should equal(true)
    }

    "return false for message that does not exist" in new BasicTestCase {
      messages.contains("does not exist", english) should equal(false)
    }
  }

}
