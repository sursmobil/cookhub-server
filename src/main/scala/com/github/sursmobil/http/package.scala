package com.github.sursmobil

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route


package object http {
  trait Endpoint {
    def route: Route
  }

  def endpoints: Seq[Endpoint] = List(
    new RecipeEndpoint(),
    new RecipesEndpoint()
  )

  def Routes: Route =
    endpoints map { _.route } reduce { _ ~ _ }
}
