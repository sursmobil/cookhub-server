package com.github.sursmobil

import com.github.sursmobil.service.recipes.RecipesServiceComponent.RecipesService
import com.github.sursmobil.service.recipes.RecipesServiceComponent
import org.scalamock.scalatest.MockFactory

trait Stubs {
  trait StubRecipesService extends RecipesServiceComponent with MockFactory {
    override val recipesService: RecipesService = stub[RecipesService]
  }
}
