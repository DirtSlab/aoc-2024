package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src/day11/input.txt"));

        ArrayList<Long> nums = new ArrayList<>();
        ArrayList<Long> times = new ArrayList<>();
        while (scanner.hasNextInt()) {
            nums.add(scanner.nextLong());
            times.add(1L);
        }
        scanner.close();

        for (int i = 0; i < 75; i++) {
            alterArray(nums, times);
            System.out.println(i + 1 + ". " + times.stream().mapToLong(num -> num).sum());
        }

        System.out.println(times.stream().mapToLong(num -> num).sum());

    }

    public static void alterArray(ArrayList<Long> nums, ArrayList<Long> times) {
        for (int i = 0; i < nums.size(); i++) {
            int numdigits = 0;
            long temp = nums.get(i);
            while (temp > 0) {
                temp /= 10;
                numdigits++;
            }

            if (nums.get(i) == 0) {
                nums.set(i, 1L);
            } else if (numdigits % 2 == 0) {
                temp = nums.get(i);
                long temp2 = 0;
                int count = 0;
                while (numdigits / 2 > count) {
                    temp2 += (long) ((temp % 10) * (Math.pow(10, (count))));
                    temp /= 10;
                    count++;
                }
                nums.set(i, temp);
                nums.add(i + 1, temp2);
                times.add(i + 1, times.get(i));
                i++;
            } else {
                nums.set(i, nums.get(i) * 2024);
            }
        }
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (Objects.equals(nums.get(i), nums.get(j))) {
                    times.set(i, times.get(i) + times.get(j));
                    times.remove(j);
                    nums.remove(j);
                }
            }
        }
    }
}
