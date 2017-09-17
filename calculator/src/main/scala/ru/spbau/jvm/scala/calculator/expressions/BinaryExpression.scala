package ru.spbau.jvm.scala.calculator.expressions

case class BinaryExpression(operationName: String, operationPrecedence: Int, operationAssociativity: Associativity.Value, f: (Double, Double) => Double) extends Expression {
  override def name = operationName

  override def precedence = operationPrecedence

  override def associativity = operationAssociativity

  def evaluate(value1: DoubleExpression, value2: DoubleExpression) = new DoubleExpression(f(value1.value, value2.value))
}
