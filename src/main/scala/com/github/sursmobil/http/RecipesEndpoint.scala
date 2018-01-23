package com.github.sursmobil.http

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

class RecipesEndpoint extends Endpoint {

  def route: Route = path("recipes" ~ Slash.?) {
    getRecipes ~ postRecipes
  }

  private def getRecipes: Route = get {
    complete("gotcha")
  }

  private def postRecipes: Route = post {
    complete("postcha")
  }

}
