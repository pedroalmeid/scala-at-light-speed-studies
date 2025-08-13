package com.rockthejvm

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
  def methodWhichCanReturnNull(): String = "Hello, Scala" // Suppose it can return null
  if (methodWhichCanReturnNull() == null) { // In Java or C++, we do this
    // Defensive code against null
  }
  // In Scala, we don't have to do that if we use Option
  val option = Option(methodWhichCanReturnNull())
  // Option is like a collection that contains at most one element
  // If methodWhichCanReturnNull returns a valid value, option will be Some("Hello, Scala"). Some is a subtype of Option
  // Otherwise, if methodWhichReturnNull returns null, option will be None (singleton object equivalent to null, but it doesn't produce NullPointerException)

  val stringProcessing = option match {
    case Some(string) => string
    case None => "Nothing"
  }

  // 2) Try

}
