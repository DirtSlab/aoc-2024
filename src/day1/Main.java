package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //p1
        ArrayList<Integer> a1 = new ArrayList<>();
        ArrayList<Integer> a2 = new ArrayList<>();

        Scanner sc = new Scanner(new File("src/day1/input.txt"));
        while (sc.hasNextLine()) {
            a1.add(sc.nextInt());
            a2.add(sc.nextInt());
            sc.nextLine();
        }
        sc.close();

        a1.sort(null);
        a2.sort(null);

        int sum1 = 0;
        for (int i = 0; i < a1.size(); i++) {
            sum1 += Math.abs(a1.get(i) - a2.get(i));
        }

        System.out.println(sum1);


        //p2
        int sum2 = 0;
        for (int x : a1) {
            for (int y : a2) {
                if (x == y) {
                    sum2 += x;
                }
            }
        }

        System.out.println(sum2);
    }
}