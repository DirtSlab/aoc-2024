package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        //p1
        int sum = 0;
        int count = 0;

        Scanner scanner = new Scanner(new File("src/day2/input.txt"));

        while (scanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(scanner.nextLine());
            ArrayList<Integer> ints = new ArrayList<>();
            boolean asc = false;
            boolean safe = true;
            while (lineScanner.hasNext()) {
                ints.add(lineScanner.nextInt());
            }

            if (ints.get(0) < ints.get(1)) {
                asc = true;
            }

            for (int i = 0; i < ints.size() - 1; i++) {
                if (Math.abs(ints.get(i) - ints.get(i+1)) > 3) {safe = false;}
                if (asc && ints.get(i) >= ints.get(i + 1)) {safe = false;}
                else if (!asc && ints.get(i) <= ints.get(i + 1)) {safe = false;}
            }
            if (safe) {sum++;}
            count++;
        }

        System.out.println(sum);
        scanner.close();

        //p2
        sum = 0;
        count = 0;

        scanner = new Scanner(new File("src/day2/input.txt"));

        while (scanner.hasNextLine()) {
            Scanner lineScanner = new Scanner(scanner.nextLine());
            ArrayList<Integer> ints = new ArrayList<>();

            while (lineScanner.hasNext()) {
                ints.add(lineScanner.nextInt());
            }

            for (int k = 0; k < ints.size(); k++) {
                ArrayList<Integer> copy = new ArrayList<>(ints);
                copy.remove(k);
                boolean asc = false;
                boolean safe = true;
                if (copy.get(0) < copy.get(1)) {
                    asc = true;
                }

                for (int i = 0; i < copy.size() - 1; i++) {
                    if (Math.abs(copy.get(i) - copy.get(i + 1)) > 3) {
                        safe = false;
                    }
                    if (asc && copy.get(i) >= copy.get(i + 1)) {
                        safe = false;
                    } else if (!asc && copy.get(i) <= copy.get(i + 1)) {
                        safe = false;
                    }
                }
                if (safe) {
                    sum++;
                    break;
                }
                count++;
            }
        }

        System.out.println(sum);
        scanner.close();
    }
}
