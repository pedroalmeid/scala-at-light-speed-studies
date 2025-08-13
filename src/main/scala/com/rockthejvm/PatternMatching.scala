package com.rockthejvm

object PatternMatching extends App {
  // In other languages, we have switch-case statements

  // In Scala, we have, similarly, the match structure
  val n = 55
  val order = n match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => s"${n}th" // "else" case
  }
  // Match is an expression, so it can be reduced to a value

  println(order) // 55th

  case class Person(name: String = "Rod", age: Int) // Setting default value for constructor argument
  val bob = Person("Bob", 30)
  val rod = Person(age = 10) // Using named argument

  // Pattern Matching allow us to compare a variable about structure and not just about its value
  def getPersonGreeting(person: Person): String = {
    person match {
      case Person("Rod", a) => s"Hi, I'm $a years old, and I'm ROD"
      case Person(n, a) => s"Hi, my name is $n, and I'm $a years old"
      case _ => "Hi"
    }
  }
  println(getPersonGreeting(bob)) // Hi, my name is Bob, and I'm 30 years old
  println(getPersonGreeting(rod)) // Hi, I'm 10 years old, and I'm ROD

  // Deconstructing Tuples
  val bandTuple = ("Arctic Monkeys", "Indie Rock", 7)
  val bandDescription = bandTuple match {
    case (name, genre, numberOfAlbums) => s"$name belong to $genre genre and they have $numberOfAlbums albums"
    case _ => "Band"
  }

  // Decomposing Lists
  val list = List(1,2,3)
  val listDescription = list match {
    case List(_, 2, _) => "List contains 2 in second position"
    case _ => "Unknown list"
  }
}
