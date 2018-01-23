package com.github.sursmobil.http

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

class RecipesEndpoint extends Endpoint {

  def route: Route = path("recipes" ~ Slash.?) {
    getRecipes ~ putRecipes
  }

  def getRecipes: Route = get {
    complete("gotcha")
  }

  def putRecipes: Route = get {
    complete("putcha")
  }

}
