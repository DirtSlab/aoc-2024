package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/day5/input.txt"));
        int[] before = new int[1176];
        int[] after = new int[1176];

        scanner.useDelimiter("[|\n]");
        for (int i = 0; i < 1176; i++) {
            before[i] = scanner.nextInt();
            after[i] = scanner.nextInt();
        }

        scanner.nextLine();
        scanner.nextLine();
        scanner.useDelimiter("\n");

        int sum1 = 0;
        int sum2 = 0;
        while (scanner.hasNextLine()) {
            boolean ok = true;
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(",");
            ArrayList<Integer> list = new ArrayList<>();
            while (lineScanner.hasNext()) {
                list.add(lineScanner.nextInt());
            }
            lineScanner.close();

            for (int i = 0; i < list.size() - 1; i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    int n1 = list.get(i);
                    int n2 = list.get(j);
                    for (int k = 0; k < 1176; k++) {
                        if (before[k] == n2 && after[k] == n1) {
                            ok = false;
                            break;
                        }
                    }
                }
            }

            if (ok) {
                //p1
                sum1 += list.get((list.size()) /2);
            } else {    //p2
                for (int i = 0; i < list.size() - 1; i++) {
                    for (int j = 0; j < list.size() - i - 1; j++) {
                        for (int k = 0; k < 1176; k++) {
                            if (before[k] == list.get(j) && after[k] == list.get(j + 1)) {
                                int temp = list.get(j);
                                list.set(j, list.get(j + 1));
                                list.set(j + 1, temp);
                                break;
                            }
                        }
                    }
                }
                sum2 += list.get((list.size()) /2);
            }

        }
        System.out.println(sum1);
        System.out.println(sum2);

    }
}
