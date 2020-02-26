package com.fun.fine.actinia

import cats.effect.Sync
import cats.implicits._

class PaymentHandler[F[_] : Sync]() {
  implicit def implTup2OptF[A, B](implicit a: F[Option[A]], b: F[Option[B]]): F[Option[(A, B)]] = (a, b).tupled.map(_.tupled)

  import OutsideWorldDataProviders._

  def handlePayment(payment: Payment): F[PaymentReport] = {
    implicit val counter: F[Option[Counter]] = Sync[F].delay(getCounter(payment))
    implicit val egrul: F[Option[Eg]] = Sync[F].delay(getEgrul(payment))
    implicit val hydra: F[Option[Hy]] = Sync[F].delay(getHydra(payment))

    collectPassedRules(payment).map {
      passedRules =>
        PaymentReport(s"for payment $payment with counter $counter, egrul $egrul, hydra $hydra", passedRules map (_.name))
    }
  }

  def collectPassedRules(payment: Payment)(implicit co: => F[Option[Counter]], eo: => F[Option[Eg]], ho: => F[Option[Hy]]): F[Seq[Rule]] = {
    implicit val p: Payment = payment
    List(
      ECRule.applyRule[F],
      CRule.applyRule[F],
      HyRule.applyRule[F]
    ).sequence.map(_.collect {
      case Some(r) => r
    })
  }

//  def applyRule(rule: Rule)(implicit payment: Payment, data: => F[Option[rule.Data]]): F[Option[Rule]] = {
//    if (!rule.isFitPayment(payment)) {
//      none[Rule].pure[F]
//    } else {
//      data.map {
//        case Some(value) if rule.isFitClient(value) => rule.some
//        case _ => none[Rule]
//      }
//    }
//  }

}
