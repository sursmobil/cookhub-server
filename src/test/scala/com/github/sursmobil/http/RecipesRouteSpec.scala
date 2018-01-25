package com.github.sursmobil.http

import akka.http.scaladsl.model.StatusCodes
import com.github.sursmobil.api.Recipe
import spray.json.{JsArray, JsObject, JsValue}

import scala.concurrent.Future

class RecipesRouteSpec extends EndpointSpec {

  trait Test extends RecipesRoute with StubRecipesService {
    val id = "magic-id"
    val kimChi = Recipe(name = "Kim chi", id = Some(id))
    val spaghetti = Recipe(name = "spaghetti", id = Some(id))
    val rspList = JsObject("data" -> JsArray(spaghetti.jsRspSummary, kimChi.jsRspSummary))
  }

  "Recipes endpoint" should {

    "return 200 to GET request on /recipes" in new Test {
      recipesService.getRecipes _ when () returns Future(List(spaghetti.summary, kimChi.summary))

      Get("/recipes") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[JsValue] shouldEqual rspList
      }
    }

    "return 200 to GET request on /recipes/" in new Test {
      recipesService.getRecipes _ when () returns Future(List(spaghetti.summary, kimChi.summary))

      Get("/recipes/") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[JsValue] shouldEqual rspList
      }
    }

    "return 200 to POST request on /recipes" in new Test {
      recipesService.createRecipe _ when * onCall { addIdToRecipeCreate(id) _ }

      Post("/recipes", kimChi.jsReq) ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[JsValue] shouldEqual kimChi.jsRsp
      }
    }

    "return 200 to POST request on /recipes/" in new Test {
      recipesService.createRecipe _ when * onCall { addIdToRecipeCreate(id) _ }

      Post("/recipes/", kimChi.jsReq) ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[JsValue] shouldEqual kimChi.jsRsp
      }
    }

  }

  def addIdToRecipeCreate(id: String)(r: Recipe): Future[Recipe] =
    Future(r.copy(id = Some(id)))

}
