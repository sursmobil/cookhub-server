package com.github.sursmobil.http

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

class RecipeEndpoint extends Endpoint {
  def route: Route = path("recipes" / Segment ~ Slash.?) { id =>
    getRecipe(id) ~ putRecipe(id)
  }

  def getRecipe(id: String): Route = get {
    complete(s"gotcha $id")
  }

  def putRecipe(id: String): Route = put {
    complete(s"putcha $id")
  }
}
