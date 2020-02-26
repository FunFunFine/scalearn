package com.fun.fine

object PrintableSyntax {

  implicit class PrintableOps[A](value: A) {
    def format_(implicit pa: Printable[A]): Unit = println(value.format)

    def format(implicit pa: Printable[A]): String = pa.format(value)

  }

}

trait Printable[A] {
  def format(value: A): String
}

object PrintableInstances {
  implicit val intPrintable: Printable[Int] = (value: Int) => s"$value"
  implicit val stringPrintable: Printable[String] = identity
}

object Printable {
  def print[A](value: A)(implicit p: Printable[A]): Unit = println(format(value))

  def format[A](value: A)(implicit p: Printable[A]): String = p.format(value)
}

object Application {

  import cats.Show
  import cats.instances.int._
  import cats.instances.string._
  import cats.syntax.show._


  implicit val catShow: Show[Cat] =
    (cat:Cat) => s"${cat.name.show} is a ${cat.age.show} year-old ${cat.color.show} cat"

}