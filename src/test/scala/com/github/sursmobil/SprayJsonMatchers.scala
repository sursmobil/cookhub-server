package com.github.sursmobil

import org.scalatest.matchers.{BeMatcher, MatchResult}
import spray.json.{JsObject, JsValue}

import scala.language.implicitConversions

trait SprayJsonMatchers {
  implicit def jsValueToBeMatcher(js: JsValue): BeMatcher[JsValue] =
    (left: JsValue) => MatchResult(left == js, s"$left is not equal to $js", s"$left equals $js")

  def obj(fields: (String, BeMatcher[JsValue])*): BeMatcher[JsValue] = (left: JsValue) => {
    def checkFields(required: Seq[(String, BeMatcher[JsValue])], actual: Map[String, JsValue]): MatchResult = {
      def field = required.head._1

      def matcher = required.head._2

      def value = actual.get(field)

      if (required.isEmpty && actual.isEmpty) {
        MatchResult(true, "", s"object matches")
      } else if (required.isEmpty) {
        MatchResult(false, s"object has unexpected fields: ${actual.keySet}", "")
      } else if (value.isEmpty) {
        MatchResult(false, s"object must contain field '$field'", "")
      } else {
        val result = matcher(value.get)
        if (!result.matches) {
          MatchResult(result.matches, s"$field:${result.rawFailureMessage}", "")
        } else {
          checkFields(required.tail, actual.tail)
        }
      }
    }

    if (!left.isInstanceOf[JsObject]) {
      MatchResult(false, s"value is not an object", s"")
    } else {
      checkFields(fields, left.asJsObject.fields)
    }
  }
}
