package com.github.sursmobil.api

import spray.json.RootJsonFormat

case class Recipes(data: List[RecipeSummary])

object Recipes extends JsonSupport {
  implicit val format: RootJsonFormat[Recipes] = jsonFormat1(Recipes.apply)
}
