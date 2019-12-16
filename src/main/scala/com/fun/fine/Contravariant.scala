package com.fun.fine

object Contravariant {

  trait Printable[A] {
    pa =>
    def format(value: A): String

    def contramap[B](func: B => A): Printable[B] =
      (value: B) => pa.format(func(value))
  }

}

