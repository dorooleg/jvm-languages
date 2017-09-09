package ru.spbau.jvm.scala.calculator.expressions

import java.util

class ExpressionParser(tokens: Array[Expression]) {

  private def infixToRPN(): util.ArrayList[Expression] = {
    val output = new util.ArrayList[Expression]
    val stack = new util.Stack[Expression]

    for (token <- tokens) {
      token match {
        case d: DoubleExpression =>
          output.add(d)
        case _: UnaryExpression | _: BinaryExpression =>
          while (!stack.empty() &&
                  !stack.peek().isInstanceOf[LeftBracket] && (
                  (token.associativity == Associativity.LEFT &&
                    token.precedence <= stack.peek().precedence) ||
                  (token.associativity == Associativity.RIGHT &&
                    token.precedence < stack.peek().precedence))) {
              output.add(stack.pop())
          }
          stack.push(token)
        case l: LeftBracket =>
          stack.push(l)
        case r: RightBracket =>
          while (!stack.empty() && !stack.peek().isInstanceOf[LeftBracket]) {
              output.add(stack.pop())
          }

          if (stack.empty())
            throw new IllegalArgumentException("Not found left bracket")

          stack.peek() match {
            case l: LeftBracket =>
              if (!l.isBackwardBracket(r))
                throw new IllegalArgumentException("Invalid left bracket")
          }
          stack.pop()
      }

    }

    while (!stack.empty())
      output.add(stack.pop())

    output
  }

  private def RPNtoDouble(): DoubleExpression = {
    val tokens = infixToRPN()
    val stack = new util.Stack[DoubleExpression]

    for (token <- tokens.toArray()) {
      token match {
        case d: DoubleExpression => stack.push(d)
        case b: BinaryExpression => {
          if (stack.size() < 2)
            throw new IllegalArgumentException("Invalid expression!")
          stack.push(b.evaluate(stack.pop(), stack.pop()))
        }
        case u: UnaryExpression => {
          if (stack.size() < 1)
            throw new IllegalArgumentException("Invalid expression!")
          stack.push(u.evaluate(stack.pop()))
        }
        case _ => throw new IllegalArgumentException("Unknown token!")
      }
    }

    stack.pop()
  }

  def evaluate() : Double = RPNtoDouble().value
}
