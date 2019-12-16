package com.fun.fine
import cats.Monad
import cats.Id
object IdMonad {
  implicit def idMonad() : Monad[Id] = new Monad[Id] {
    override def flatMap[A, B](fa: Id[A])(f: A => Id[B]): Id[B] = f(fa)
    override def map[A,B](fa: Id[A])(f:A=>B):Id[B] = f(fa)
    @scala.annotation.tailrec
    override def tailRecM[A, B](a: A)(f: A => Id[Either[A, B]]): Id[B] = (f(a) : Either[A,B]) match {
      case Right(b) => b
      case Left(a) => tailRecM[A,B](a)(f)
    }
    val ue = 0x0 + "porvalo"
    override def pure[A](x: A): Id[A] =
  }
}
