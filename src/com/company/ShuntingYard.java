package com.company;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ShuntingYard {
    Map<String, Functions> ops = new HashMap<String, Functions>() {
        {
            put("+", Functions.Add);
            put("-", Functions.Negate);
            put("*", Functions.Multiply);
            put("/", Functions.Divide);
            put("pow", Functions.Exponentiate);
            put("sqrt", Functions.Sqrt);
            put("nrt", Functions.Root);
            put("log", Functions.Log);
            put("sin", Functions.Sin);
            put("cos", Functions.Cos);
            put("tan", Functions.Tan);
            put("cot", Functions.Cot);
        }
    };
    String sourceFile;

    public ShuntingYard(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public Map<String, Functions> getOps() {
        return ops;
    }

    public ArrayList<String> getResult() {
        return infixToPostfix(ReadFile(sourceFile));
    }

    private ArrayList<String> infixToPostfix(String infix) {
        //StringBuilder output = new StringBuilder();
        ArrayList<String> output = new ArrayList<String>();
        Deque<String> stack = new LinkedList<String>();

        // operator
        char[] split = infix.toCharArray();
        for (int i = 0; i < split.length; i++) {
            String token;

            if (Character.isDigit(split[i])) {
                StringBuilder temp = new StringBuilder();
                while (Character.isDigit(split[i]) || split[i] == '.') {
                    temp.append(split[i]);
                    i++;
                    if (i == split.length)
                        break;

                }
                token = temp.toString();
                i--;
            } else if (Character.isAlphabetic(split[i])) {
                StringBuilder temp = new StringBuilder();
                while (Character.isAlphabetic(split[i])) {
                    temp.append(split[i]);
                    i++;
                    if (i == split.length)
                        break;
                }
                token = temp.toString();
                i--;
            } else if (split[i] == '+' || split[i] == '-' || split[i] == '*' || split[i] == '/' || split[i] == '(' || split[i] == ')' || split[i] == ',') {
                token = String.valueOf(split[i]);
            } else continue;

            if (ops.containsKey(token)) {
                while (!stack.isEmpty() && isHigherPrecedence(token, stack.peek()))
                    output.add((stack.pop()));
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.peek().equals("("))
                    output.add(stack.pop());
                stack.pop();
                if (ops.containsKey(token))
                    output.add(stack.pop());
            } else if (token.equals(",")) {
                while (!stack.peek().equals("("))
                    output.add(stack.pop());
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals("e")) {
                output.add(String.valueOf(Math.E));
            } else if (token.equals("pi")) {
                output.add(String.valueOf(Math.PI));
            } else {
                output.add(token);
            }
        }

        while (!stack.isEmpty())
            output.add(stack.pop());

        return output;
    }

    private String ReadFile(String sourceFile) {
        File inputFile = new File(sourceFile);
        if (!inputFile.exists() || inputFile.isDirectory()) {

            return "File does not exist or is directory";
        }

        try {
            FileInputStream inStream = new FileInputStream(sourceFile);
            BufferedInputStream inFileStream = new BufferedInputStream(inStream);
            int currentCharacter = inFileStream.read();
            StringBuilder sb = new StringBuilder();
            while (currentCharacter != -1) {
                sb.append(((char) currentCharacter));
                currentCharacter = inFileStream.read();
            }
            inFileStream.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceFile;//clear up later

    }

    private boolean isHigherPrecedence(String op, String sub) {
        return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence && ops.get(op).isLeftAssociative);
    }
}
