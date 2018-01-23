package com.github.sursmobil.http

import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

trait EndpointSpec extends WordSpec
  with Matchers
  with ScalatestRouteTest