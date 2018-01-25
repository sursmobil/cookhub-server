package com.github.sursmobil.http

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.github.sursmobil.Stubs
import com.github.sursmobil.api.{Recipe, RecipeSummary}
import com.github.sursmobil.util._
import org.scalatest.{Matchers, WordSpec}
import spray.json.{DefaultJsonProtocol, JsObject, JsString, JsValue}

import scala.language.implicitConversions

trait EndpointSpec
    extends WordSpec
    with Matchers
    with ScalatestRouteTest
    with DefaultJsonProtocol
    with SprayJsonSupport
    with Stubs {

  implicit class MergeableJsObject(js: JsObject) {
    def ++(other: Map[String, JsValue]): JsObject =
      JsObject(js.fields ++ other)

    def ++(other: JsObject): JsObject =
      this.++(other.fields)

    def +(entry: (String, JsValue)): JsObject =
      this.++(Map(entry))

  }

  implicit class TestRecipe(recipe: Recipe) {
    def maybe(value: Option[String], name: String): Option[JsObject => JsObject] =
      value map (v => in => in + (name -> JsString(v)))

    def jsReq: JsObject =
      JsObject("name" -> JsString(recipe.name)) |>?
        maybe(recipe.ingredients, "ingredients") |>?
        maybe(recipe.description, "description") |>?
        maybe(recipe.preparation, "preparation")

    def jsRsp: JsObject = jsReq |>? maybe(recipe.id, "id")

    def jsRspSummary: JsObject = JsObject("name" -> JsString(recipe.name), "id" -> JsString(recipe.id.get))

    def summary: RecipeSummary = RecipeSummary(id = recipe.id.get, name = recipe.name)
  }

}
