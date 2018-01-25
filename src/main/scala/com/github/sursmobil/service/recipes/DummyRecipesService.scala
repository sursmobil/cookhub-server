package com.github.sursmobil.service.recipes

import com.github.sursmobil.api.{Recipe, RecipeSummary}

import scala.concurrent.{ExecutionContext, Future}
import RecipesServiceComponent.RecipesService

private class DummyRecipesService(implicit executionContext: ExecutionContext) extends RecipesService {
  override def getRecipeForId(id: String): Future[Option[Recipe]] =
    Future(None)

  override def updateRecipeForId(id: String, recipe: Recipe): Future[Option[Recipe]] =
    Future(Some(recipe.copy(id = Some(id))))

  override def getRecipes: Future[Iterable[RecipeSummary]] =
    Future(Nil)

  override def createRecipe(recipe: Recipe): Future[Recipe] =
    Future(recipe.copy(id = Some("not-important-id")))
}
