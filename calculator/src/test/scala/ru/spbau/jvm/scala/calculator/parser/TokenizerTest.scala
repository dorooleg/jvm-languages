package ru.spbau.jvm.scala.calculator.parser

import java.util

import junit.framework.TestCase
import org.junit.{Assert, Test}
import ru.spbau.jvm.scala.calculator.expressions._

class TokenizerTest extends TestCase {

  private var map: util.HashMap[String, Array[Expression]]  = _

  override def setUp() {
    map = new util.HashMap[String, Array[Expression]]
    map.put("+", Array(new BinaryExpression("+", 0, Associativity.LEFT, (d1, d2) => d1 + d2)))
    map.put("*", Array(new BinaryExpression("*", 5, Associativity.LEFT, (d1, d2) => d2 * d1)))
    map.put("/", Array(new BinaryExpression("/", 5, Associativity.LEFT, (d1, d2) => d2 / d1)))
    map.put("-", Array(new BinaryExpression("-", 0, Associativity.LEFT, (d1, d2) => d2 - d1),
      new UnaryExpression("-", d => -d)))
    map.put("^", Array(new BinaryExpression("^", 10, Associativity.RIGHT, (d1, d2) => Math.pow(d2, d1))))
    map.put("lg", Array(new UnaryExpression("lg", d => Math.log10(d))))
    map.put("(", Array(new LeftBracket("(", ")")))
    map.put(")", Array(new RightBracket(")", "(")))
  }

  private def evaluate(text: String) = new Tokenizer(text, map).evaluate()

  def testEvaluate(): Unit = {
    val res = evaluate("2 + 3")
    Assert.assertTrue(res.apply(0).isInstanceOf[DoubleExpression])
    Assert.assertTrue(res.apply(1).isInstanceOf[BinaryExpression])
    Assert.assertTrue(res.apply(2).isInstanceOf[DoubleExpression])
  }
}
