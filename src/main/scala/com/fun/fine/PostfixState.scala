package com.fun.fine

object PostfixState {

  import cats.data.State
  import cats.syntax.applicative._

  type CalcState[A] = State[List[Int], A]

  def evalAll(input: List[String]): CalcState[Int] =
    input.foldLeft(0.pure[CalcState]) {
      case (acc, i) => acc.flatMap(_ => evalOne(i))
    }

  def evalOne(sym: String): CalcState[Int] = sym match {
    case "+" => operator(_ + _)
    case "-" => operator(_ - _)
    case "*" => operator(_ * _)
    case "/" => operator(_ / _)
    case num => operand(num.toInt)
  }

  def operator(function: (Int, Int) => Int): CalcState[Int] = CalcState[Int] {
    case x :: y :: l =>
      val result = function(x, y)
      (result :: l) -> result
    case _ => sys.error("illegal smth")
  }

  def operand(value: Int): CalcState[Int] = CalcState[Int](state => (value :: state) -> value)

  def CalcState[A]: (List[Int] => (List[Int], A)) => State[List[Int], A] = State[List[Int], A]

}
