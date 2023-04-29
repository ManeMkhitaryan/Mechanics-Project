package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SpringArray{
    public static Spring equivalentSpring(String springExpr) {
            Stack<Character> s = new Stack<>();
            return SpringArray.inParallel(springs(springExpr, s, 0));
    }

    public static Spring equivalentSpring(String springExpr, Spring[] springs) {
        return null;
    }

    public static List<Spring> springs(String str, Stack<Character> stack, int i) {
            List<Spring> spr = new ArrayList<>();
            for (; i < str.length(); i++) {
                if (str.charAt(i) == '[') {
                    stack.push(str.charAt(i));
                    spr.add(inParallel(springs(str, stack, i)));
                } else if (str.charAt(i) == ']') {
                    stack.push(str.charAt(i));
                    spr.add(inSeries(springs(str, stack, i)));
                } else if (str.charAt(i) == ']' && stack.peek() == '[') {
                    stack.pop();
                    return spr;
                } else if (str.charAt(i) == '}' && stack.peek() == '{') {
                    stack.pop();
                    return spr;
                }
            }
            return spr;
    }

    public static Spring inSeries(List<Spring> spr) {
        if (spr.isEmpty()) {
            return new Spring();
        }

        double k = 0;
        for (Spring s : spr) {
            k += 1 / s.getK();
        }

        return new Spring(1 / k);
    }

    public static Spring inParallel(List<Spring> springs) {
            if (springs.isEmpty()) {
                return new Spring();
            }

            double k = 0;
            for (Spring s : springs) {
                k += s.getK();
            }

            return new Spring(k);
    }


}

