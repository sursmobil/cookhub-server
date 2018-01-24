package com.github.sursmobil

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

package object http {

  def Routes: Route =
    endpoints map {
      _.route
    } reduce {
      _ ~ _
    }

  def endpoints: Seq[Endpoint] = List(new RecipeEndpoint(), new RecipesEndpoint())

  trait Endpoint {
    def route: Route
  }
}
