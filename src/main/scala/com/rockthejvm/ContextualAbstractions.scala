package com.rockthejvm

object ContextualAbstractions extends App {
  // Specific for Scala 3

  // When we talk about contextual abstractions, we refer to two main techniques:

  // 1. Contextual Parameters or Arguments
  val list = List(2,1,3,4)
  val orderedList = list.sorted // A priori, List(1,2,3,4)

  /*When we use sorted method (without argument) on a List[Int], the compiler knows by default how to compare the
  elements for the sort, without a need for a comparison function. That happens because, in Scala, we have an existing
  comparison function/object called Ordering which has its default implementation*/

  // But we can customize our comparison logic by creating a new Ordering instance
  val descendingOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _) // short syntax for lambda (a,b) => a > b

  // If we want to use it, traditionally we should do this
  val newOrderedList = list.sorted(descendingOrdering)

  // Be we can use contextual parameters by replacing val with the word "given" on our contextual argument
  given contextualDescendingOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)

  // It will use it on all sorted now, even on those which were defined previously, because the compiler uses the context of the scope
  println(orderedList) // List(4,3,2,1)

  // Defining a custom use of ours of contextual parameters
  trait Combinator[A] {
    def combine(x: A, y: A): A
  }
  def combineAll[A](list: List[A])(using combinator: Combinator[A]): A = { // the keyword "using" identifies a contextual parameter
    list.reduce((a, b) => combinator.combine(a,b))
    // We could use list.reduce(combinator.combine) as shorter syntax as well
  }
  given intCombinator: Combinator[Int] = new Combinator[Int] {
    override def combine(x: Int, y: Int): Int = x + y
  }
  val sum = combineAll(list)
  println(sum) // 10

  // The priority order for compiler search for "given" contextual parameters is:
    // 1. Local Scope
    // 2. Imported Scope (we can import given instances with the command import package.given)
    // 3. Companion scopes of all types involved in the call

  // Contextual Bounds: Other forms (syntax) of declaring a use of contextual parameter method
  def combineAllV2[A](list: List[A])(using Combinator[A]): A = ???
  def combineAllV3[A : Combinator](list: List[A]): A = ???

  // (For the future) Where are contextual arguments used? Type classes, dependency injection, context-depending functionality, type-level programming

  // 2. Extension methods
  case class Person(name: String) {
    def greet(): String = s"Hi, my name is $name"
  }
  extension (string: String) {
    def greet(): String = Person(string).greet()
  }

  val danielsGreeting = "Daniel".greet()
  // We're basically extending the greet method for the String class. We can call this "Type Enrichment"
  println(danielsGreeting) // Hi, my name is Daniel
  println("Anything".greet()) // Hi, my name is Anything

  // Mixing contextual arguments with extension methods,
  // We could extend the List type to work with a combinator
  extension [A] (list: List[A]) {
    def combineAllValues(using combinator: Combinator[A]): A = {
      list.reduce(combinator.combine)
    }
  }
  println(List(1,4,3).combineAllValues) // 8
}
