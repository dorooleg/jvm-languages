package ru.spbau.jvm.scala.calculator

object Calculator {

  def main(args: Array[String]): Unit = {
    val input = scala.io.StdIn.readLine()
    val engine = new CalculatorEngine()
    println(engine.evaluate(input))
  }
}
