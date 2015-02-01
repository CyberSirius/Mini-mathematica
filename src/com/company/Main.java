package com.company;

public class Main {

    public static void main(String[] args) {
        ShuntingYard RNP = new ShuntingYard("src/com/company/expression.txt");
        RPNEvaluation result = new RPNEvaluation(RNP.getResult(), RNP.getOps());
        System.out.println(RNP.getResult());
        System.out.println(result.getResult());
    }
}
