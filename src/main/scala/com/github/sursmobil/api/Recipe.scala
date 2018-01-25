package com.github.sursmobil.api

import spray.json.RootJsonFormat

case class Recipe(
  name: String,
  id: Option[String],
  description: Option[String] = None,
  ingredients: Option[String] = None,
  preparation: Option[String] = None
)

object Recipe extends JsonSupport {
  implicit val format: RootJsonFormat[Recipe] = jsonFormat5(Recipe.apply)
}
