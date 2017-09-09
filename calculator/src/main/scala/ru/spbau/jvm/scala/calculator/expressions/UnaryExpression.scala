package ru.spbau.jvm.scala.calculator.expressions

class UnaryExpression(operationName: String, operationPrecedence: Int, operationAssociativity: Associativity.Value, f: Double => Double) extends Expression{
  override def name(): String = operationName

  override def precedence(): Int = operationPrecedence

  override def associativity(): Associativity.Value = operationAssociativity

  def evaluate(value: DoubleExpression): DoubleExpression = new DoubleExpression(f(value.value))
}
