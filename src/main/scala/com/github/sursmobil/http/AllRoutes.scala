package com.github.sursmobil.http

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import com.github.sursmobil.service.recipes.RecipesServiceComponent

trait AllRoutes extends RecipeRoute with RecipesRoute {
  this: RecipesServiceComponent =>

  override def route: Route =
    super[RecipeRoute].route ~ super[RecipesRoute].route

}
