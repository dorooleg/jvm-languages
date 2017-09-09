package ru.spbau.jvm.scala.calculator.expressions

class BinaryExpression(operationName: String, operationPrecedence: Int, operationAssociativity: Associativity.Value, f: (Double, Double) => Double) extends Expression {
  override def name(): String = operationName

  override def precedence(): Int = operationPrecedence

  override def associativity(): Associativity.Value = operationAssociativity

  def evaluate(value1: DoubleExpression, value2: DoubleExpression) = new DoubleExpression(f(value1.value, value2.value))
}
