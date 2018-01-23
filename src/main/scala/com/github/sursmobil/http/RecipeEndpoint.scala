package com.github.sursmobil.http

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

class RecipeEndpoint extends Endpoint {
  def route: Route = path(("recipes" / Segment) ~ Slash.?) { implicit id =>
    getRecipe ~ putRecipe
  }

  def getRecipe(implicit id: String): Route = get {
    complete(s"gotcha $id")
  }

  def putRecipe(implicit id: String): Route = put {
    complete(s"putcha $id")
  }
}
