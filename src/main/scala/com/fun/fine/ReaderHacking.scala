package com.fun.fine

import cats.data.Reader

object ReaderHacking {

  type DbReader[A] = Reader[Db, A]

  def findUsername(userId: Int): DbReader[Option[String]] =
    DbReader(db => db.usernames.get(userId))

  def checkPassword(username: String,
                    password: String): DbReader[Boolean] =
    DbReader(db => db.passwords.get(username).contains(password))

  def DbReader[A]: (Db => A) => Reader[Db, A] = Reader[Db, A]



  case class Db(usernames: Map[Int, String],
                passwords: Map[String, String]
               )





}
