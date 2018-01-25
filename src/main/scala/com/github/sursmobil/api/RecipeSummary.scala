package com.github.sursmobil.api

import spray.json.RootJsonFormat

case class RecipeSummary(id: String, name: String)

object RecipeSummary extends JsonSupport {
  implicit val format: RootJsonFormat[RecipeSummary] = jsonFormat2(RecipeSummary.apply)
}
