package com.github.sursmobil.service.recipes

import com.github.sursmobil.api.{Recipe, RecipeSummary}
import com.github.sursmobil.service.recipes.RecipesServiceComponent.RecipesService
import com.github.sursmobil.util.ExecutionContextComponent

import scala.concurrent.Future

trait RecipesServiceComponent {
  def recipesService: RecipesService
}

object RecipesServiceComponent {
  trait RecipesService {
    def getRecipeForId(id: String): Future[Option[Recipe]]
    def updateRecipeForId(id: String, recipe: Recipe): Future[Option[Recipe]]
    def getRecipes: Future[Iterable[RecipeSummary]]
    def createRecipe(recipe: Recipe): Future[Recipe]
  }

  trait Dummy extends RecipesServiceComponent {
    this: ExecutionContextComponent =>

    override lazy val recipesService: RecipesService = new DummyRecipesService
  }
}
