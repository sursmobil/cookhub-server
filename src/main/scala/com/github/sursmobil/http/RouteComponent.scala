package com.github.sursmobil.http

import akka.http.scaladsl.server.Route

trait RouteComponent {
  def route: Route
}
