package com.fun.fine.fpif


object Parallel {
  def sum(is: IndexedSeq[Int]): Int = {
    if (is.size <= 1) {
      is.headOption getOrElse 0
    } else {
      val (left, right) = is.splitAt(is.size / 2)
      sum(left) + sum(right)
    }
  }

//  def sumFork(ints: IndexedSeq[Int])(Par: Par[Int]): Par[Int] = {
//    if (ints.length <= 1)
//      Par.unit(ints.headOption getOrElse 0) else {
//      val (l, r) = ints.splitAt(ints.length / 2)
//      Par.map2(Par.fork(sumFork(l)), Par.fork(sumFork(r)))(_ + _)
//    }
//  }

  trait Par[A] {
    def unit(a: A): Par[A]

    def lazyUnit(a: => A): Par[A] = fork(unit(a))

    def get(pa: Par[A]): A

    def map2[B, C](pa: Par[A], pb: Par[B])(f: (A, B) => C): Par[C]

    def fork(a: => Par[A]): Par[A]
  }
}
