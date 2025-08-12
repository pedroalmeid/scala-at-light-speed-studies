package com.rockthejvm

object Basics extends App {
  val number: Int = 42
  val isActive = false
  val myString = "I love Scala"
  val composedString = "I" + "love" + "Scala" // Concatenation
  val interpolatedString = s"My number is ${number + 1}" // Interpolation
  println(interpolatedString)
  val ifExpression = if (number > 41) 100 else 200
  println(ifExpression)

  val codeBlock = {
    val firstLocalValue = 100
    val scndLocalValue = 50
    firstLocalValue + scndLocalValue + 300
  }
  println(codeBlock)

  def concatenateStringWithInteger(x: Int, y: String): String = y + " " + x
  val stringReturn = concatenateStringWithInteger(10, "Olá")

  println(stringReturn)

  def codeBlockFunction(x: Float): Float = {
    val aux = x * x
    2 * aux
  }

  println(codeBlockFunction(10))

  def factorial(n: Int): Int = if (n <= 1) 1 else n * factorial(n-1)
  println(factorial(5))
}