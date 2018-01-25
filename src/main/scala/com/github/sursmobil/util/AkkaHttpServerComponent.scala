package com.github.sursmobil.util

import akka.http.scaladsl.Http
import com.github.sursmobil.http.RouteComponent
import com.typesafe.scalalogging.StrictLogging

import scala.concurrent.Future

trait AkkaHttpServerComponent {
  def httpServer: HttpServer

  trait HttpServer {
    def start(): Unit
  }
}

object AkkaHttpServerComponent {
  trait Default extends AkkaHttpServerComponent { this: RouteComponent with ActorSystemComponent =>

    override lazy val httpServer: HttpServer = new HttpServer with StrictLogging {
      override def start(): Unit = {
        val bindingFuture = Http().bindAndHandle(route, "0.0.0.0")

        sys.addShutdownHook {
          bindingFuture
            .flatMap(_.unbind())
            .onComplete { _ =>
              actorSystem.terminate()
            }
        }

        exitOnFailure(bindingFuture)
      }

      def exitOnFailure(futures: Future[_]*): Unit = {
        for (f <- futures) f.failed.foreach { t =>
          logger.error("Fatal error, exiting", t)
          System.exit(1)
        }
      }
    }
  }
}
