package com.github.sursmobil.http

import akka.http.scaladsl.model.StatusCodes.{InternalServerError, NotFound, OK}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.github.sursmobil.api.Recipe
import com.github.sursmobil.service.recipes.RecipesServiceComponent

import scala.util.{Failure, Success}

trait RecipeRoute extends RouteComponent { this: RecipesServiceComponent =>

  def route: Route = path("recipes" / Segment ~ Slash.?) { id =>
    getRecipe(id) ~ putRecipe(id)
  }

  private def getRecipe(id: String): Route = get {
    onComplete(recipesService.getRecipeForId(id)) {
      case Success(Some(recipe)) => complete(OK -> recipe)
      case Success(None)         => complete(NotFound)
      case Failure(_)            => complete(InternalServerError)
    }
  }

  private def putRecipe(id: String): Route = put {
    entity(as[Recipe]) { recipe =>
      onComplete(recipesService.updateRecipeForId(id, recipe)) {
        case Success(Some(updated)) => complete(OK -> updated)
        case Success(None)          => complete(NotFound)
        case Failure(_)             => complete(InternalServerError)
      }
    }
  }

}
