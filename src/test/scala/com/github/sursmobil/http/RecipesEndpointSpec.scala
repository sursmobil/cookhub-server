package com.github.sursmobil.http

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route

class RecipesEndpointSpec extends EndpointSpec {

  def route: Route = new RecipesEndpoint().route

  "Recipes endpoint" when {

    "return 200 to GET request on /recipes" in {
      Get("/recipes") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "gotcha"
      }
    }

    "return 200 to GET request on /recipes/" in {
      Get("/recipes/") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "gotcha"
      }
    }

    "return 200 to POST request on /recipes" in {
      Post("/recipes") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "postcha"
      }
    }

    "return 200 to POST request on /recipes/" in {
      Post("/recipes/") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual "postcha"
      }
    }

  }

}
