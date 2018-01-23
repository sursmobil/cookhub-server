package com.github.sursmobil

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.StrictLogging
import com.github.sursmobil.http.Routes
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

object Main {
  val config: Config = ConfigFactory.load()
  // needed to run the route
  implicit val system: ActorSystem = ActorSystem("cookhub", config)
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  // needed for the future map/flatmap in the end and future in fetchItem and saveOrder
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  def main(args: Array[String]): Unit = {

    val bindingFuture = Http().bindAndHandle(Routes, "localhost", config.getInt("cookhub.server.port"))

    sys.addShutdownHook {
      bindingFuture
        .flatMap(_.unbind())
        .onComplete { _ => system.terminate() }
    }

    CLI.exitOnFailure(bindingFuture)
  }

  object CLI extends StrictLogging {

    def exitOnFailure(futures: Future[_]*)(implicit exec: ExecutionContext): Unit = {
      for (f <- futures) f.failed.foreach { t =>
        logger.error("Fatal error, exiting", t)
        System.exit(1)
      }
    }
  }
}
