package com.fun.fine


object ELF {

  import cats.Eq
  import cats.instances.int._
  import cats.instances.string._
  import cats.syntax.eq._

  implicit val catEq: Eq[Cat] = {
    case (Cat(xn, xa, xc), Cat(yn, ya, yc))
    => xn === yn && xa === ya && xc === yc
  }
}
