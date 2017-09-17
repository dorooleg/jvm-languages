package ru.spbau.jvm.scala.calculator.expressions

trait Expression {
  def name: String
  def precedence: Int
  def associativity: Associativity.Value
}
