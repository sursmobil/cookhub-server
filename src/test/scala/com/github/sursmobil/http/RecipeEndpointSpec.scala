package com.github.sursmobil.http

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route

class RecipeEndpointSpec extends EndpointSpec {

  val id: String = "some-test-id"

  def route: Route = new RecipeEndpoint().route

  "Recipes endpoint" when {

    "return 200 to GET request on /recipes/<id>" in {
      Get(s"/recipes/$id") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual s"gotcha $id"
      }
    }

    "return 200 to GET request on /recipes/<id>/" in {
      Get(s"/recipes/$id/") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual s"gotcha $id"
      }
    }

    "return 200 to PUT request on /recipes/<id>" in {
      Put(s"/recipes/$id") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual s"putcha $id"
      }
    }

    "return 200 to PUT request on /recipes/<id>/" in {
      Put(s"/recipes/$id/") ~> route ~> check {
        status shouldEqual StatusCodes.OK
        responseAs[String] shouldEqual s"putcha $id"
      }
    }

  }

}
