package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/day7/input.txt"));

        long total = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(": | ");
            long sum = lineScanner.nextLong();
            ArrayList<Long> nums = new ArrayList<>();
            while (lineScanner.hasNext()) {
                nums.add(lineScanner.nextLong());
            }
            lineScanner.close();

            if (testLine(sum, nums, 0)) {
                total += sum;
            }
        }
        System.out.println(total);
    }

    public static boolean testLine(long sum, ArrayList<Long> nums, long total) {
        long temp = total;
        if (nums.isEmpty()) {
            return temp == sum;
        }

        ArrayList<Long> newNums = new ArrayList<>();
        for (int i = 1; i < nums.size(); i++) {
            newNums.add(nums.get(i));
        }

        temp += nums.get(0);
        if (testLine(sum, newNums, temp)) return true;

        temp -= nums.get(0);
        temp = temp * nums.get(0);
        if (testLine(sum, newNums, temp)) return true;

        temp = temp / nums.get(0);
        temp = Long.parseLong(temp + "" + nums.get(0));
        if (testLine(sum, newNums, temp)) return true;
        return false;
    }
}
