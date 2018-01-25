package com.github.sursmobil.http
import akka.http.scaladsl.model.StatusCodes
import com.github.sursmobil.api.Recipe
import spray.json._

import scala.concurrent.Future

class RecipeRouteSpec extends EndpointSpec {

  trait Test extends RecipeRoute with StubRecipesService {
    val id: String = "some-test-id"
    val kimChi = Recipe(name = "Kim chi", id = Some(id))
  }

  "Recipes endpoint" should {

    "return 200 to GET request on /recipes/<id>" in new Test {
      recipesService.getRecipeForId _ when * returns Future(Some(kimChi))

      Get(s"/recipes/$id") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[JsObject] shouldBe kimChi.jsRsp
      }
    }

    "return 200 to GET request on /recipes/<id>/" in new Test {
      recipesService.getRecipeForId _ when * returns Future(Some(kimChi))

      Get(s"/recipes/$id/") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[JsObject] shouldBe kimChi.jsRsp
      }
    }

    "return 200 to PUT request on /recipes/<id>" in new Test {
      recipesService.updateRecipeForId _ when (*, *) onCall { rewriteIdToRecipeUpdate _ }

      Put(s"/recipes/$id", kimChi.jsReq) ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[JsObject] shouldBe kimChi.jsRsp
      }
    }

    "return 200 to PUT request on /recipes/<id>/" in new Test {
      recipesService.updateRecipeForId _ when (*, *) onCall { rewriteIdToRecipeUpdate _ }

      Put(s"/recipes/$id/", kimChi.jsReq) ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[JsObject] shouldBe kimChi.jsRsp
      }
    }

  }

  def rewriteIdToRecipeUpdate(id: String, r: Recipe): Future[Option[Recipe]] = {
    Future(Some(r.copy(id = Some(id))))
  }

}
