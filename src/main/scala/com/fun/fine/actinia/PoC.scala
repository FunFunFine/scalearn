package com.fun.fine.actinia

import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

object PoC extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val handler = new PaymentHandler[IO]
    val payment = Payment("hyegCc", 7)
    handler.handlePayment(payment).map(println(_)).as(ExitCode.Success)
  }
}

/*
  Hydra    --------> HyRule

  Egrul    ------------+
                       |
                       V
  Counter  -+------> ECRule
            |
            +------> CRule
 */

case class Counter(message: String)

case class Eg(message: String)

case class Hy(message: String)

object OutsideWorldDataProviders {

  def getCounter(payment: Payment): Option[Counter] = {
    println(s"Counter getting side effect: ${payment.amount} failing at 7")
    if (payment.amount % 7 != 0) Counter("counter").some else none[Counter]
  }

  def getEgrul(payment: Payment): Option[Eg] = {
    println(s"Egrul getting side effect: ${payment.amount} failing at 5")
    if (payment.amount % 5 != 0) Eg("egrul").some else none[Eg]
  }

  def getHydra(payment: Payment): Option[Hy] = {
    println(s"Hydra getting side effect: ${payment.amount} failing at 11")
    if (payment.amount % 11 != 0) Hy("hydra").some else none[Hy]
  }
}


