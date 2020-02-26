package com.fun.fine.actinia


trait Rule {
  type Data

  def isFitPayment(payment: Payment): Boolean

  def isFitClient(data: Data): Boolean

  def name: String
}

object ECRule extends Rule {
  override type Data = (Eg, Counter)

  override def isFitPayment(payment: Payment): Boolean = payment.id endsWith "egc"

  override def isFitClient(data: (Eg, Counter)): Boolean = data match {
    case (Eg(eg), Counter(counter)) => eg == "egrul" && counter == "counter"
  }

  override def name: String = "EgrulAndCounterRule{ check if payment = *egc }"
}

object HyRule extends Rule {
  override type Data = Hy

  override def isFitPayment(payment: Payment): Boolean = payment.id.startsWith("hy")

  override def isFitClient(data: Hy): Boolean = data.message == "hydra"

  override def name: String = "HydraRule{ check if payment id = hy*}"
}

object CRule extends Rule {
  override type Data = Counter

  override def isFitPayment(payment: Payment): Boolean = payment.id.contains("C")

  override def isFitClient(data: Counter): Boolean = data.message == "counter"

  override def name: String = "CounterRule{ check if payment id = *C*} "
}
