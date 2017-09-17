package ru.spbau.jvm.scala.calculator.expressions

case class UnaryExpression(operationName: String, f: Double => Double) extends Expression{
  override def name = operationName

  override def precedence = Int.MaxValue

  override def associativity = Associativity.RIGHT

  def evaluate(value: DoubleExpression): DoubleExpression = new DoubleExpression(f(value.value))
}
