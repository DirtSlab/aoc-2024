package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/day4/input.txt"));
        String[] lines = new String[140];
        for (int i = 0; i < 140; i++) {
            lines[i] = scanner.nextLine();
        }
        scanner.close();

        //p2
        int sum = 0;
        int[] sums = new int[8];
        for (int i = 0; i < 140; i++) {
            int index = -1;
            while (lines[i].indexOf("X", index + 1) != -1) {
                index = lines[i].indexOf("X", index + 1);
                try {
                    if (lines[i].substring(index, index + 4).equals("XMAS")) {
                        sum++;
                        sums[1]++;
                    }
                } catch (Exception ignored) {
                }
                try {
                    if (lines[i].substring(index - 3, index + 1).equals("SAMX")) {
                        sum++;
                        sums[2]++;
                    }
                } catch (Exception ignored) {
                }
                try {
                    if (lines[i + 1].charAt(index) == 'M'
                            && lines[i + 2].charAt(index) == 'A'
                            && lines[i + 3].charAt(index) == 'S') {
                        sum++;
                        sums[3]++;
                    }
                } catch (Exception ignored) {
                }
                try {
                    if (lines[i - 1].charAt(index) == 'M'
                            && lines[i - 2].charAt(index) == 'A'
                            && lines[i - 3].charAt(index) == 'S') {
                        sum++;
                        sums[4]++;
                    }
                } catch (Exception ignored) {
                }
                try {
                    if (lines[i + 1].charAt(index + 1) == 'M'
                            && lines[i + 2].charAt(index + 2) == 'A'
                            && lines[i + 3].charAt(index + 3) == 'S') {
                        sum++;
                        sums[5]++;
                    }
                } catch (Exception ignored) {
                }
                try {
                    if (lines[i - 1].charAt(index + 1) == 'M'
                            && lines[i - 2].charAt(index + 2) == 'A'
                            && lines[i - 3].charAt(index + 3) == 'S') {
                        sum++;
                        sums[6]++;
                    }
                } catch (Exception ignored) {
                }
                try {
                    if (lines[i + 1].charAt(index - 1) == 'M'
                            && lines[i + 2].charAt(index - 2) == 'A'
                            && lines[i + 3].charAt(index - 3) == 'S') {
                        sum++;
                        sums[7]++;
                    }
                } catch (Exception ignored) {
                }
                try {
                    if (lines[i - 1].charAt(index - 1) == 'M'
                            && lines[i - 2].charAt(index - 2) == 'A'
                            && lines[i - 3].charAt(index - 3) == 'S') {
                        sum++;
                        sums[0]++;
                    }
                } catch (Exception ignored) {
                }
            }
        }

        System.out.println(sum);
        System.out.println(Arrays.toString(sums));

        //p2
        int sum2 = 0;
        for (int i = 0; i < 140; i++) {
            int index = -1;
            while (lines[i].indexOf("A", index + 1) != -1) {
                index = lines[i].indexOf("A", index + 1);
                try {
                    char tl = lines[i - 1].charAt(index - 1);
                    char tr = lines[i - 1].charAt(index + 1);
                    char bl = lines[i + 1].charAt(index - 1);
                    char br = lines[i + 1].charAt(index + 1);
                    if (tl != br && tr != bl
                            && (tl == 'M' | tl == 'S')
                            && (bl == 'M' | bl == 'S')
                            && (tr == 'M' | tr == 'S')
                            && (br == 'M' | br == 'S')) {
                        sum2++;
                    }
                } catch (Exception ignored) {
                }
            }
        }
        System.out.println(sum2);
    }
}
