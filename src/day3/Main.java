package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {


        //did this manually with the regex mul\([0-9][0-9]*,[0-9][0-9]*\)

        Scanner sc = new Scanner(new File("src/day3/input2.txt"));

        int sum = 0;
        while (sc.hasNextLine()) {
            int i1 = sc.nextInt();
            int i2 = sc.nextInt();
            sum += i1 * i2;
        }

        System.out.println(sum);
    }
}
