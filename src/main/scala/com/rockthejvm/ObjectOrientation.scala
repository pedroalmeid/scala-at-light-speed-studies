package com.rockthejvm

object ObjectOrientation extends App {
  // Class
  class Animal {
    // define fields as values
    val age: Int = 0
    // define methods
    def eat(): Unit = println("I'm eating")
  }
  // Instance of a class
  val animal = new Animal

  // Inheritance
  class Dog(name: String) extends Animal { // constructor definition
    override def eat(): Unit = println("I'm eating as a dog") // Override
  }
  // Constructor arguments are NOT fields

  val dog = new Dog("Lassie")
  dog.eat() // Subtype polymorphism

  abstract class WalkingAnimal { // Abstracts
    val hasLegs = true
    def walk(): Unit
  }

  // Traits are like Java interfaces
  trait JumpingAnimal {
    def jump(): Unit
  }

  // Single-class inheritance and multi-trait mixing
  class Squirrel extends WalkingAnimal with JumpingAnimal { // We could mix more "with's"
    override def walk(): Unit = println("Walking as a Squirrel")
    override def jump(): Unit = println("Jumping as a Squirrel")
  }

  trait Carnivore {
    def eat(prey: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def eat(prey: Animal): Unit = println("I'm eating you, animal")
  }
  val croc = new Crocodile
  croc.eat(dog) // traditional notation = object.method(argument)
  croc eat dog // infix notation = object method argument

  // So, operators in Scala are actually methods used with infix notation
  val basicMath = 1 + 2
  val equivalentBasicMath = 1.+(2)

  // Anonymous classes
  val dinosaur = new Carnivore {
    override def eat(prey: Animal): Unit = print("Eating as a dinosaur")
  }
  /* What the compiler does
  class CarnivoreAnonymous359831 with Carnivore {
    override def eat(prey: Animal): Unit = print("Eating as a dinosaur")
  }
  val dinosaur = new CarnivoreAnonymous359831
  */

  // Singletons
  object MySingleton { // the only instance of MySingleton type
    val specialValue = 120
    def specialMethod(): Int = 1201
    def apply(x: Int): Int = x + 1
  }
  val n = MySingleton.apply(64)
  val n1 = MySingleton(64) // implicit call for "apply"
  println(n, n1)

  object Animal { // Companion object
    // companions can access each other's private fields
    // singleton Animal and instances of Animal class are different things
    val canLiveForever = false
  }
  val animalCanLiveForever = Animal.canLiveForever // "static" fields/methods

  // Case classes = lightweight data structures with some boilerplate
    // sensible equals and hash code
    // serialization
    // generates a companion object with "apply" method => allow them to be constructed without "new" keyword
    // pattern matching
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 60) // equivalent to new Person("Bob", 60) or Person.apply("Bob", 60)

  // Exceptions = special objects that can be thrown to interrupt the JVM execution
  // So, we have try-catch blocks
  try {
    val x: String = null
    println(x.length)
  }
  catch {
    case e: Exception => println("Some error message")
  }
  finally {
    // Executed no matter what
  }

  // Generics with []
  abstract class MyClassWithGenerics[T] {
    def dependsOfT: T
    def dependsOfK[K]: MyClassWithGenerics[K]
  }

  // Collections with generics
  val list: List[Int] = List(1, 10, 30)
  println(list.head) // First element of the list
  println(list.tail) // Rest of the list without head
}