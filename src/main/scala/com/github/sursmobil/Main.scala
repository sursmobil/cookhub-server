package com.github.sursmobil

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.github.sursmobil.http.Routes
import com.typesafe.scalalogging.StrictLogging

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

object Main extends StrictLogging {
  // needed to run the route
  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  // needed for the future map/flatmap in the end
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  def main(args: Array[String]): Unit = {

    val bindingFuture = Http().bindAndHandle(Routes, "0.0.0.0")

    sys.addShutdownHook {
      bindingFuture
        .flatMap(_.unbind())
        .onComplete { _ =>
          system.terminate()
        }
    }

    exitOnFailure(bindingFuture)
  }

  def exitOnFailure(futures: Future[_]*)(implicit exec: ExecutionContext): Unit = {
    for (f <- futures) f.failed.foreach { t =>
      logger.error("Fatal error, exiting", t)
      System.exit(1)
    }
  }

}
