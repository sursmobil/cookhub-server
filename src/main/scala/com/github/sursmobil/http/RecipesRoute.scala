package com.github.sursmobil.http

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.github.sursmobil.api.{JsonResponse, Recipe}
import com.github.sursmobil.service.recipes.RecipesServiceComponent

import scala.util.{Failure, Success}

trait RecipesRoute extends RouteComponent with SprayJsonSupport { this: RecipesServiceComponent =>

  def route: Route = path("recipes" ~ Slash.?) {
    getRecipes ~ postRecipes
  }

  private def getRecipes: Route = get {
    onComplete(recipesService.getRecipes) {
      case Success(v) => complete(StatusCodes.OK -> JsonResponse(v))
      case Failure(_) => complete(StatusCodes.InternalServerError)
    }
  }

  private def postRecipes: Route = post {
    entity(as[Recipe]) { recipe =>
      onComplete(recipesService.createRecipe(recipe)) {
        case Success(v) => complete(StatusCodes.OK -> v)
        case Failure(_) => complete(StatusCodes.InternalServerError)
      }
    }
  }

}
