package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("1.txt"));
        String input = scan.nextLine();

        List<String> result = convertStringToBinary(input);
        result = result.stream().map(Application::replaceFAL).toList();

        List<Character> list = result.stream().map(Application::convertBinaryToChar).toList();

        try (FileWriter writer = new FileWriter("2.txt", false)) {
            List<String> list1 = list.stream().map(Object::toString).toList();

            list1.forEach(s-> {
                try {
                    writer.write(s);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> convertStringToBinary(String input) {
        List<String> result = new ArrayList<>();

        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.add(String.format("%8s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
                    .replaceAll(" ", "0"));
        }
        return result;
    }

    public static String replaceFAL(String s) {
        char[] str = s.toCharArray();
        char first = str[0];
        char second = str[str.length - 1];
        str[0] = second;
        str[str.length - 1] = first;
        return String.valueOf(str);
    }

    public static char convertBinaryToChar(String input) {
        return (char) Integer.parseInt(input, 2);
    }
}
