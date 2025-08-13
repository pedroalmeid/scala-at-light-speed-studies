package com.rockthejvm

object Functional extends App {
  // Scala's based on OOP
  class Person(name: String) {
    def apply(age: Int): Unit = println(s"I have aged $age years")
  }

  val bob = new Person("Bob")
  bob.apply(42)
  bob(42) // Invoking bob as a function == bob.apply

  // The JVM is built for Java, so it works with objects as first-class elements of programming, since Java is an OOP language
  // On functional programming we want to use functions as first-class elements of programming
  // That means we want to compose functions, pass functions as arguments, return functions, etc.
  // For that, Scala created FunctionX or functionN (Function1, Function2, ..., Function22)

  val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg + 1
  } // We defined an anonymous class of a FunctionN trait that uses "apply" to receive arguments and return a value.
  // So, it's an instance that basically works as a function => We defined a function!

  println(simpleIncrementer(32)) // 33
  // All Scala functions are actually Instances of these FunctionX

  // Syntax sugars
  val doubler: Int => Int = (x: Int) => 2 * x
  val tripler = (x: Int) => 3 * x // Type inference

  // Higher-order functions: take functions as arguments or as return type
  // Map function
  val mappedList = List(1,2,3).map[Float](n => 2*n)
  val tripledList = List(10,20,30).map(tripler)
  println(mappedList) // List(2.0, 4.0, 6.0)
  println(tripledList) // List(30, 60, 90)

  // flatMap
  val flapMappedList = List(1,2,3).flatMap(x => List(x, 2*x))
  println(flapMappedList) // List(1, 2, 2, 4, 3, 6)

  // Alternative syntax for passing lambdas as last argument
  val altFlapMappedList = List(1,2,3).flatMap { x =>
    List(x, 2*x)
  }

  // Filter
  val filteredList = List(1,2,3,4,5,6).filter { x => x < 4 }
  println(filteredList) // List(1,2,3)
  // Alternative syntax for filter
  val altFilteredList = List(1,2,3,4,5,6).filter(_ < 4)
  println(altFilteredList)

  // Usage example of a map and a flatMap to generate a list containing all combinations between two lists
  val allPairs = List(1,2,3).flatMap { number =>
    List('a', 'b', 'c').map(letter => s"$number-$letter")
  }
  println(allPairs) // List(1-a, 1-b, 1-c, 2-a, 2-b, 2-c, 3-a, 3-b, 3-c)

  // For comprehensions == Alternative syntax for a map and flatMap chain more human-readable
  val altPairs = for {
    number <- List(1,2,3)
    letter <- List('a', 'b', 'c')
  } yield s"$number-$letter"

  // More about collections

  // Adding elements to lists
  val numbersList = List(1,2,3,4,5)
  val extendedList = 0 +: numbersList :+ 6 // Prepending and appending elements
  println(extendedList) // List(0,1,2,3,4,5,6)

  // Sequences
  val sequence: Seq[Int] = Seq(1, 2, 3)
  val accessedElementByIndex = sequence(2) // element at index 2
  println(accessedElementByIndex) // 3
  // Sequences are actually a trait for all ordered collections like lists or vectors

  // Vectors are a particular type of sequences (implementation of it) but very fast
  val vector = Vector(1,2,3,10)

  // Sets
  val set = Set(1,2,1,2,3)
  println(set) // Set(1,2,3)
  val setHas5 = set.contains(5) // false
  val addedSet = set + 5 // Set(1,2,3,5)
  val subtractedSet = set - 1 // Set(2,3)

  // Ranges
  val range = 1 to 100 // [1,100]
  val evenRange = range.filter(_ % 2 == 0) // [2,4,...,98, 100]
  // Ranges are not actually a collection, but they retrieve the necessary information to represent the range
  println(evenRange.toList) // So, they can be converted to collections like lists, seqs, sets

  // Tuples
  val tuple = ("Bon Jovi", "Rock", 1982)
  // A tuple store multiple information (that can be of different types) on a singular element
  println(tuple._1) // "Bon Jovi"
  println(tuple._3) // 1982

  // Maps
  val phoneBook: Map[String, Int] = Map(
    ("Daniel", 99928), // tuple notation
    "Jane" -> 999481, // alternative syntax
    "Manny" -> 12134
  ) // They work as a collection of tuples indicating key-value pairs
}
