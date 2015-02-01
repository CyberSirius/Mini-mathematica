package com.company;

public enum Functions {
    Add(1, true, 2),
    Negate(1, true, 1),
    Multiply(2, true, 2),
    Divide(2, true, 2),
    Exponentiate(3, false, 2),
    Sqrt(4, true, 1),
    Root(4, true, 2),
    Log(4, true, 2),
    Sin(4, true, 1),
    Cos(4, true, 1),
    Tan(4, true, 1),
    Cot(4, true, 1);
    final int precedence;
    final boolean isLeftAssociative;
    final int numberOfOperators;

    private Functions(int precedence, boolean isLeftAssociative, int numberOfOperators) {
        this.precedence = precedence;
        this.isLeftAssociative = isLeftAssociative;
        this.numberOfOperators = numberOfOperators;
    }
}
