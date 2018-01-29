package com.github.sursmobil.http

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.github.sursmobil.service.recipes.RecipesServiceComponent
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._

trait AllRoutes extends RecipeRoute with RecipesRoute {
  this: RecipesServiceComponent =>

  override def route: Route =
    cors() { super[RecipeRoute].route ~ super[RecipesRoute].route }

}
