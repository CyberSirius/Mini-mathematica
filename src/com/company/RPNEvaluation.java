package com.company;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

public class RPNEvaluation {
    private ArrayList<String> input;
    private Map<String, Functions> ops;

    public RPNEvaluation(ArrayList<String> input, Map<String, Functions> ops) {
        this.input = input;
        this.ops = ops;
    }

    public Double getResult() {
        return Eval();
    }

    private double Eval() {
        double result = 0;
        Stack<String> stack = new Stack<String>();
        for (String token : input) {
            if (!ops.containsKey(token)) {
                stack.push(token);
            } else {
                double a = Double.valueOf(stack.pop());
                double b = 0;
                if (ops.get(token).numberOfOperators == 2) {
                    if (!stack.isEmpty()) {
                        b = Double.valueOf(stack.pop());
                    }

                }
                try {
                    switch (ops.get(token)) {
                        case Add:
                            stack.push(String.valueOf(a + b));
                            break;
                        case Negate:
                            stack.push(String.valueOf(-a));
                            break;
                        case Multiply:
                            stack.push(String.valueOf(a * b));
                            break;
                        case Divide:
                            if (a == 0) {
                                throw new IllegalArgumentException("Error. Division by zero.");
                            } else
                                stack.push(String.valueOf(b / a));
                            break;
                        case Exponentiate:
                            stack.push(String.valueOf(Math.pow(b, a)));
                            break;
                        case Sin:
                            stack.push(String.valueOf(Math.sin(a)));
                            break;
                        case Cos:
                            stack.push(String.valueOf(Math.cos(a)));
                            break;
                        case Tan:
                            stack.push(String.valueOf(Math.tan(a)));
                            break;
                        case Cot:
                            stack.push(String.valueOf(1.0 / Math.tan(a)));
                            break;
                        case Log:
                            stack.push(String.valueOf(Math.log(a) / Math.log(b)));
                            break;
                        case Root:
                            stack.push(String.valueOf(Math.pow(b, 1 / a)));
                            break;
                        case Sqrt:
                            stack.push(String.valueOf(Math.sqrt(a)));
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        while (!stack.isEmpty()) {
            result += Double.valueOf(stack.pop());
        }
        return result;
    }
}
