package com.github.sursmobil.util

import scala.concurrent.ExecutionContext

trait ExecutionContextComponent {
  implicit def executionContext: ExecutionContext
}

object ExecutionContextComponent {
  trait Global extends ExecutionContextComponent {
    import scala.concurrent.ExecutionContext.Implicits.global

    override implicit val executionContext: ExecutionContext = global
  }
}
