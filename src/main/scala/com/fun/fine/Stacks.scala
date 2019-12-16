package com.fun.fine

import cats.Monad
import cats.data.EitherT
import cats.instances.future._
import cats.syntax.flatMap._
import cats.syntax.functor._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

// for Monad
object Stacks {
  type Response[A] = EitherT[Future, String, A]
  val powerLevels = Map(
    "Jazz" -> 6,
    "Bumblebee" -> 8,
    "Hot Rod" -> 10
  )

  def canSpecialMove(ally1: String, ally2: String): Response[Boolean] =
    for {
      lvl1 <- getPowerLevel(ally1)
      lvl2 <- getPowerLevel(ally2)
    } yield (lvl2 + lvl1) > 15

  def getPowerLevel(autobot: String): Response[Int] = {
    powerLevels.get(autobot) match {
      case Some(avg) => EitherT.right(Future(avg))
      case None => EitherT.left(Future(s"$autobot unreachable"))
    }
  }

  def product[F[_], A, B](fa: F[A], fb: F[B])(implicit ff: Monad[F]): F[(A, B)] =
    fa.flatMap(a => fb.map(b => (a, b))
  )
}
