package com.fun.fine
import cats.implicits._

object ValidForm {
  type Strings = List[String]
  type ErrorsEither[A] = Either[Strings, A]
  case class User(name: String, age: Int)

  def readName(data:Map[String, String]): ErrorsEither[String] = data.get("name") match {
    case Some(value) => value.asRight[Strings]
    case None => List("No name").asLeft[String]
  }
  
}
