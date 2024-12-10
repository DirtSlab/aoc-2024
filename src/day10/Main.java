package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static String[] lines = new String[45];

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/day10/input.txt"));



        for (int i = 0; i < 45; i++) {
            lines[i] = scanner.nextLine();
        }
        scanner.close();

        int sum = 0;
        for (int i = 0; i < 45; i++) {
            for (int j = 0; j < 45; j++) {
                if (lines[i].charAt(j) == '0') {
                    sum += findPath(j, i, -1, new ArrayList<>());
                }
            }
        }

        System.out.println(sum);
    }

    public static int findPath(int x, int y, int current, ArrayList<String> visited) {
        try {
            lines[y].charAt(x);
        } catch (Exception e) {
            return 0;
        }
        int sum = 0;
        if (!(current + 1 == lines[y].charAt(x) - '0')) {
            return 0;
        }
        if (current == 8 && lines[y].charAt(x) == '9' && !visited.contains(y + "+" + x)) {
            //used for part 1
//            visited.add(y + "+" + x);
            return 1;
        }
        sum += findPath(x - 1, y, current + 1, visited);
        sum += findPath(x + 1, y, current + 1, visited);
        sum += findPath(x, y + 1, current + 1, visited);
        sum += findPath(x, y - 1, current + 1, visited);
        return sum;
    }
}
