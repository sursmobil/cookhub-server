package com.github.sursmobil.api

import spray.json.{JsonFormat, RootJsonFormat}

case class JsonResponse[D](data: D)

object JsonResponse extends JsonSupport {
  implicit def format[D: JsonFormat]: RootJsonFormat[JsonResponse[D]] = jsonFormat1(JsonResponse.apply[D])
}
