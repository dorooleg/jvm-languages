package ru.spbau.jvm.scala.calculator

import junit.framework.TestCase
import org.junit.{Assert, Test}

class CalculatorEngineTest extends TestCase {

  var engine: CalculatorEngine = _

  override def setUp() {
    engine = new CalculatorEngine
  }

  @Test
  def testEmpty() {
    var exception = false
    try {
      engine.evaluate("")
    } catch {
      case e: IllegalArgumentException => exception = true
    }
    Assert.assertEquals(true, exception)
  }

  def testSimple() {
    Assert.assertEquals(2.0, engine.evaluate("2"), 0.0001)
  }

  def testMultiply() {
    Assert.assertEquals(6.0, engine.evaluate("2 * 3"), 0.0001)
    Assert.assertEquals(24.0, engine.evaluate("2 * 3 * 4"), 0.001)
  }

  def testSum() {
    Assert.assertEquals(6.0, engine.evaluate("2 * 3"), 0.0001)
    Assert.assertEquals(24.0, engine.evaluate("2 * 3 * 4"), 0.001)
  }

  def testSub() {
    Assert.assertEquals(-1.0, engine.evaluate("2 - 3"), 0.0001)
    Assert.assertEquals(-5.0, engine.evaluate("2 - 3 - 4"), 0.001)
  }

  def testDiv() {
    Assert.assertEquals(2.0, engine.evaluate("6 / 3"), 0.0001)
    Assert.assertEquals(1.0, engine.evaluate("6 / 3 / 2"), 0.001)
  }

  def testLg() {
    Assert.assertEquals(1.0, engine.evaluate("lg 10"), 0.0001)
    Assert.assertEquals(2.0, engine.evaluate("lg 100"), 0.001)
  }

  def testPow() {
    Assert.assertEquals(4.0, engine.evaluate("2 ^ 2"), 0.0001)
    Assert.assertEquals(256.0, engine.evaluate("2 ^ 2 ^ 3"), 0.001)
  }

  def testBracket() {
    Assert.assertEquals(1.0, engine.evaluate("5 - (2 + 2)"), 0.0001)
    Assert.assertEquals(5.0, engine.evaluate("(5 - 2) + 2"), 0.0001)
  }

  def testPrecedence() {
    Assert.assertEquals(23.0, engine.evaluate("2 + 3 * 7"), 0.0001)
    Assert.assertEquals(9.0, engine.evaluate("2 + 21 / 3"), 0.0001)
  }

  def testAssociativity() {
    Assert.assertEquals(81.0, engine.evaluate("3 ^ 2 ^ 2"), 0.001)
    Assert.assertEquals(-6.0, engine.evaluate("2 - 3 - 5"), 0.0001)
  }

  def testDifficult() {
    Assert.assertEquals(-10.938, engine.evaluate("(3 + lg 100) * 2 + 3 / 7 ^ 2 - 21"), 0.01)
    Assert.assertEquals(2.0, engine.evaluate("lg (10 + 30 - 5 * 10 + 110)"), 0.001)
  }

  def testUnaryMinus() {
    Assert.assertEquals(-3.0, engine.evaluate("-3"), 0.001)
    Assert.assertEquals(2.0, engine.evaluate("(-3 + 5)"), 0.001)
    Assert.assertEquals(8.0, engine.evaluate("5 - -3"), 0.001)
    Assert.assertEquals(7.0, engine.evaluate("(5 + 5) - 3"), 0.001)
  }
}
