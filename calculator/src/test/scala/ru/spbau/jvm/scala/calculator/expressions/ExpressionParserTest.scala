package ru.spbau.jvm.scala.calculator.expressions

import junit.framework.TestCase
import org.junit.Assert

class ExpressionParserTest extends TestCase {

  def testEvaluate(): Unit = {
    val tokens = Array(new DoubleExpression(23.0),
      new BinaryExpression("+", 0, Associativity.LEFT, (d1, d2) => d1 + d2),
      new DoubleExpression(7))
    val parser = new ExpressionParser(tokens)
    Assert.assertEquals(30.0, parser.evaluate(), 0.001)
  }
}
