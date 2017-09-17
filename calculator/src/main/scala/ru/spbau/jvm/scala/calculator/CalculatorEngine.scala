package ru.spbau.jvm.scala.calculator

import java.util

import ru.spbau.jvm.scala.calculator.expressions._
import ru.spbau.jvm.scala.calculator.parser.Tokenizer

class CalculatorEngine {

  private val map = new util.HashMap[String, Array[Expression]]
  map.put("+", Array(new BinaryExpression("+", 0, Associativity.LEFT, (d1, d2) => d1 + d2)))
  map.put("*", Array(new BinaryExpression("*", 5, Associativity.LEFT, (d1, d2) => d2 * d1)))
  map.put("/", Array(new BinaryExpression("/", 5, Associativity.LEFT, (d1, d2) => d2 / d1)))
  map.put("-", Array(new BinaryExpression("-", 0, Associativity.LEFT, (d1, d2) => d2 - d1),
                     new UnaryExpression("-", d => -d)))
  map.put("^", Array(new BinaryExpression("^", 10, Associativity.RIGHT, (d1, d2) => Math.pow(d2, d1))))
  map.put("lg", Array(new UnaryExpression("lg", d => Math.log10(d))))
  map.put("(", Array(new LeftBracket("(", ")")))
  map.put(")", Array(new RightBracket(")", "(")))

  def evaluate(text: String) = {
    val tokens = new Tokenizer(text, map).evaluate()
    new ExpressionParser(tokens).evaluate()
  }

}
