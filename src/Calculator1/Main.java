package Calculator1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static String x;
    public static String y;
    public static String operationValue;
    private static final String[] ROMAN = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final int[] ARABIC = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    // метод конвертации арабских чисел в римские
    public static String toRoman(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ARABIC.length; i++) {
            while (n >= ARABIC[i]) {
                n -= ARABIC[i];
                sb.append(ROMAN[i]);
            }
        }
        return sb.toString();
    }

    // метод конвертации римских чисел в арабские
    public static int fromRoman(String romanNumeral) {
        int num = 0;
        for (int i = 0; i < ROMAN.length; i++) {
            while (romanNumeral.startsWith(ROMAN[i])) {
                num += ARABIC[i];
                romanNumeral = romanNumeral.substring(ROMAN[i].length());
            }
        }
        return num;
    }

    // метод проверки римских чисел
    public static boolean isRoman(String s) {
        boolean b = false;
        int count=1;
        List<String> list = new ArrayList<>(List.of(ROMAN));
        for (int i = 0; i < s.length(); i++) {
            if (list.contains(String.valueOf(s.charAt(i)))) {
                b = true;
            } else {
                b = false;
                break;
            }
        }
        if (b&&s.length()>3) {
            for (int i = 1; i <s.length(); i++) {
                if (s.charAt(i) == s.charAt(i - 1)) count++;
                else count=1;
                if (count>3) {
                    b=false;
                    break;
                }
            }
        }
        return b;
    }

    // метод проверки арабских чисел
    public static boolean isDigit(String s) {
        boolean b = false;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) b = true;
            else {
                b = false;
                break;
            }
        }
        return b;
    }

    // метод получения значений первого и второго чисел
    private static void extractedXY(String[] strings) throws Exception {
        if (strings.length == 2) {
            x = strings[0];
            y = strings[1];
        } else throw new Exception("throws Exception");
    }

    // метод получения значения операции
    private static void extractedOp(String[] operation) throws Exception {
        if (operation.length < 1) throw new Exception("throws Exception");
        else {
            String str = operation[operation.length - 1];
            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                operationValue = str;
            } else throw new Exception("throws Exception");
        }
    }
    // метод проверки входных чилел, значения которых по условию задачи не должно превышать 10)
    private static void checkingNumbers(int a, int b) throws Exception {
        if (a >10|| b >10) throw new Exception("throws Exception");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().replaceAll(" ", "");

        String[] strings = input.split("[+\\-*/]");
        String[] operation = input.split("\\w");

        try {
            extractedXY(strings);
            extractedOp(operation);

            if (isDigit(x) && isDigit(y)) { // работаем с арабскими числами

                int a = Integer.parseInt(x);
                int b = Integer.parseInt(y);

                checkingNumbers(a, b);

                switch (operationValue) { // операции с арабскими числами
                    case "+" -> System.out.println(a + b);
                    case "-" -> System.out.println(a - b);
                    case "*" -> System.out.println(a * b);
                    case "/" -> {
                        if (b==0) throw new Exception("throws Exception");
                        else System.out.println(a / b);
                    }

                }
            } else if (isRoman(x) && isRoman(y)) { // работаем с римскими числами

                int a = fromRoman(x);
                int b = fromRoman(y);

                checkingNumbers(a, b);

                switch (operationValue) { // операции с римскими числами
                    case "+" -> System.out.println(toRoman(a + b));
                    case "-" -> {

                        if (a > b) System.out.println(toRoman(a - b));
                        else throw new Exception("throws Exception");
                    }
                    case "*" -> System.out.println(toRoman(a * b));
                    case "/" -> {
                        if (a > b) System.out.println(toRoman(a / b));
                        else throw new Exception("throws Exception");
                    }
                }
            } else throw new Exception("throws Exception");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
