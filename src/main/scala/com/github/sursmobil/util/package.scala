package com.github.sursmobil

package object util {
  implicit class CanPipe[A](a: A) {

    def |>?(o: Option[A => A]): A =
      (o fold a) { _(a) }

    def |>[R](f: A => R): R =
      f(a)

  }
}
