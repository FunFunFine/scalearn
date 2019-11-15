package com.fun.fine

object PrintableSyntax {

  implicit class PrintableOps[A](value: A) {
    def format(implicit pa: Printable[A]): Unit = println(value.format)

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

  import PrintableInstances._
  import PrintableSyntax._

  final case class Cat(name: String, age: Int, color: String)

  implicit val catPrintable: Printable[Cat] =
    (cat: Cat) => s"${cat.name.format} is a ${Printable.format(cat.age)} year-old ${Printable.print(cat.color)} cat"


}