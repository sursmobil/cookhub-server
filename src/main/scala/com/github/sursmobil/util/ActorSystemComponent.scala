package com.github.sursmobil.util

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext

trait ActorSystemComponent extends ExecutionContextComponent {
  implicit def actorSystem: ActorSystem
  implicit def actorMaterializer: ActorMaterializer
  override implicit def executionContext: ExecutionContext = actorSystem.dispatcher
}

object ActorSystemComponent {
  trait Default extends ActorSystemComponent {
    override implicit val actorSystem: ActorSystem = ActorSystem()
    override implicit val actorMaterializer: ActorMaterializer = ActorMaterializer()
  }
}
