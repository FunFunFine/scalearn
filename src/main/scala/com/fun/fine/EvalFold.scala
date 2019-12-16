package com.fun.fine

import cats.Eval

object EvalFold {

  import cats.Foldable

  implicit val listEvalFoldable: Foldable[List] = new Foldable[List] {

    override def foldRight[A, B](fa: List[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] = {
      fa match {
        case Nil => lb
        case a :: as => Eval.defer(foldRight(as, lb)(f).map(fr => f(a, Eval.now(fr)).value))
      }
    }

    override def foldLeft[A, B](fa: List[A], b: B)(f: (B, A) => B): B = ???
  }
}
