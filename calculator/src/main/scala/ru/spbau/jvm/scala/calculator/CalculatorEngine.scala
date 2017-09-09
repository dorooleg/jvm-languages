package ru.spbau.jvm.scala.calculator

import java.util

import ru.spbau.jvm.scala.calculator.expressions._
import ru.spbau.jvm.scala.calculator.parser.Tokenizer

class CalculatorEngine {

  private val map = new util.HashMap[String, Expression]
  map.put("+", new BinaryExpression("+", 0, Associativity.LEFT, (d1, d2) => d1 + d2))
  map.put("*", new BinaryExpression("*", 5, Associativity.LEFT, (d1, d2) => d2 * d1))
  map.put("/", new BinaryExpression("/", 5, Associativity.LEFT, (d1, d2) => d2 / d1))
  map.put("-", new BinaryExpression("-", 0, Associativity.LEFT, (d1, d2) => d2 - d1))
  map.put("^", new BinaryExpression("^", 10, Associativity.RIGHT, (d1, d2) => Math.pow(d2, d1)))
  map.put("lg", new UnaryExpression("lg", Int.MaxValue, (d1) => Math.log10(d1)))
  map.put("(", new LeftBracket("(", ")"))
  map.put(")", new RightBracket(")", "("))

  def evaluate(text: String) = {
    val tokens = new Tokenizer(text, map).evaluate()
    new ExpressionParser(tokens).evaluate()
  }

}
