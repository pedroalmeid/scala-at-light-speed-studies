package com.rockthejvm

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

object Advanced extends App {
  // Lazy evaluation: A lazy val is a value that is only evaluated when it is first used.
  lazy val lazyValue = 2

  val sideEffect = { // Remember: code block
    println("I'm not lazy")
    1 // value of the code block
  }
  lazy val lazyValueWithSideEffect = {
    println("I'm lazy")
    3 // value of the code block
  }
  /*
  When we run the program, we see "I'm not lazy" on the console, because, even sideEffect is never used,
  it was evaluated, and the println("I'm not lazy") side effect was executed to evaluate the last line
  of the code block, assigning 1 to the sideEffect val. In another hand, println("I'm lazy") was not executed
  because it was declared as a lazy val, and it will be executed only if lazyValueWithSideEffect was used
  */
  // Lazy vals are very useful for infinite collections

  // Pseudo-collections: They aren't collections, they're them own types, but are treated as collections
  // 1) Option
  def methodWhichCanReturnNull(): String = "Hello, Option" // Suppose it can return null
  if (methodWhichCanReturnNull() == null) { // In Java or C++, we do this
    // Defensive code against null
  }
  // In Scala, we don't have to do that if we use Option
  val option = Option(methodWhichCanReturnNull())
  // Option is like a collection that contains at most one element
  // If methodWhichCanReturnNull returns a valid value, option will be Some("Hello, Option"). Some is a subtype of Option
  // Otherwise, if methodWhichReturnNull returns null, option will be None (singleton object equivalent to null, but it doesn't produce NullPointerException)

  val stringProcessing = option match {
    case Some(validString) => validString
    case None => "Nothing"
  }

  // 2) Try
  def methodWhichCanThrowException(): String = throw new RuntimeException
  // Traditionally, we would need a try-catch block to handle a method which can throw an exception
  try {
    methodWhichCanThrowException()
  }
  catch {
    case e: Exception => "Defend against the exception"
  } // This behavior is kind of bad because it forces us to write a lot of defensive and not readable code

  // For that, we use a Try, a "collection" with either a value inside Success if the code went well,
  // or an exception inside Failure if the code threw one.
  // We can avoid try-catch blocks and use a more readable and idiomatic handling for exceptions
  val aTry = Try(methodWhichCanThrowException())
  val anotherStringProcessing = aTry match {
    case Success(validValue) => validValue
    case Failure(exception) => s"I have obtained an exception: $exception"
  }

  // Asynchronous Programming
  // We're gonna use another pseudo-collection called Future

  // Future allows us to execute code on other threads
  val future = Future {
    println("Loading...") // Was shown on console
    Thread.sleep(1000) // Java code for blocking the execution for 1 sec
    println("I have computed a value") // Doesn't appear on console, because it's been evaluated on another JVM thread
    100
  }
  println(future) // Future(<not completed>)

  Thread.sleep(2000) // When we add this, we see "I have computed a value" on console, because now we had time to.
  // A Feature singleton retrieves a value that will be executed asynchronous and be updated when it finishes
  // While it doesn't finish, it stores <not completed>

  println(future) // Future(Success(100))
  // When it's done, it's store a Success singleton with the evaluated value

  // Option, Try and Future are actually monads for functional programming, but we're not entering this for now

    // Implicits basics
    // Use case number 1: Implicit arguments
    def methodWithImplicitArgs(implicit arg: Int): Int = arg + 1
    implicit val implicitInt: Int = 45
    println(methodWithImplicitArgs) // 46
    // It is as if the compiler searches for the value we want to pass as an argument.

    // Use case number 2: Implicit conversions
    implicit class MyRichInteger(n: Int) {
      def isEven() = n % 2 == 0
    }
    println(23.isEven()) // false
    // Compiler turns smart enough to do new MyRichInteger(23).isEven

    // Implicits are very powerful to turn Scala a very expressive language,
    // But they need careful, because are dangerous by the possibility to generate unexpected behaviors

}