package ru.spbau.jvm.scala.calculator.parser

import java.util

import ru.spbau.jvm.scala.calculator.expressions.{DoubleExpression, Expression}

class Tokenizer(text: String, expressions: util.HashMap[String, Expression]) {

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
        val expression = expressions.get(token.toString())
        if (expression != null) {
          tokens.add(expression)
          token.clear()
        }
      }
    }

    tokens.toArray(new Array[Expression](tokens.size()))
  }

}
