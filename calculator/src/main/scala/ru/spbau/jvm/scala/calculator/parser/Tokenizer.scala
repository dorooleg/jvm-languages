package ru.spbau.jvm.scala.calculator.parser

import java.util

import ru.spbau.jvm.scala.calculator.expressions._

class Tokenizer(text: String, expressions: util.HashMap[String, Array[Expression]]) {

  private def isUnary(tokens: util.ArrayList[Expression]): Boolean = {
    if (tokens.isEmpty)
      return true

    val expression = tokens.get(tokens.size() - 1)
    if (!expression.isInstanceOf[DoubleExpression] && !expression.isInstanceOf[RightBracket])
      return true
    false
  }

  private def getExpression(tokens: util.ArrayList[Expression], canditates: Array[Expression]): Expression = {
    if (tokens == null || canditates == null)
      return null
    val unary = isUnary(tokens)
    for (candidate <- canditates) {
      if (unary && candidate.isInstanceOf[UnaryExpression])
        return candidate
      if (!unary && !candidate.isInstanceOf[UnaryExpression])
        return candidate
    }
    canditates.apply(0)
  }

  def evaluate() : Array[Expression] = {
    val tokens = new util.ArrayList[Expression]()
    val token = new StringBuilder()

    val stack = new util.Stack[Char]()
    stack.push(' ')
    for (ch <- text.reverse)
      stack.push(ch)

    while (!stack.empty()) {
      var ch = stack.pop()
      var isNumber = false
      while (!stack.empty() && (ch.isDigit || ch == '.')) {
        token.append(ch)
        ch = stack.pop()
        isNumber = true
      }

      if (isNumber) {
        tokens.add(new DoubleExpression(token.toString().toDouble))
        token.clear()
      }

      if (ch != ' ' && ch != '\n' && ch != '\t') {
        token.append(ch)
        val expression = getExpression(tokens, expressions.get(token.toString()))
        if (expression != null) {
          tokens.add(expression)
          token.clear()
        }
      }
    }

    tokens.toArray(new Array[Expression](tokens.size()))
  }
}
