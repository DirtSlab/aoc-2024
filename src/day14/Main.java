package day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int width = 101;
    static int height = 103;
    static int[][] grid = new int[height][width];

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        boolean found = false;
        while (!found || !sc.nextLine().equals("0")) {
            found = false;
            grid = new int[height][width];
            Scanner scanner = new Scanner(new File("src/day14/input.txt"));

            scanner.useDelimiter("p=|,| v=|\n");
            while (scanner.hasNext()) {
                int iposx = scanner.nextInt();
                int iposy = scanner.nextInt();
                int vx = scanner.nextInt();
                int vy = scanner.nextInt();
                scanner.nextLine();
                calculateFinalPos(iposx, iposy, vx, vy, count);
            }
            scanner.close();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width - 8; j++) {
                    if (grid[i][j] != 0 && grid[i][j + 1] != 0 && grid[i][j + 2] != 0 && grid[i][j + 3] != 0 && grid[i][j + 4] != 0 && grid[i][j + 5] != 0
                    && grid[i][j + 6] != 0 && grid[i][j + 7] != 0) {found = true;}
                }
            }
            if (found){
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (grid[i][j] == 0) {
                            System.out.print(" ");
                        } else {
                            System.out.print("#");
                        }
                    }
                    System.out.println();
                }
                System.out.println(count + " Seconds");
            }
            count++;
        }

        int score = 0;
        int sumq1 = 0;
        int sumq2 = 0;
        int sumq3 = 0;
        int sumq4 = 0;
        for (int i = 0; i < height / 2; i++) {
            for (int j = 0; j < width / 2; j++) {
                sumq1 += grid[i][j];
            }
        }
        for (int i = (height / 2) + 1; i < height; i++) {
            for (int j = 0; j < width / 2; j++) {
                sumq2 += grid[i][j];
            }
        }
        for (int i = 0; i < height / 2; i++) {
            for (int j = (width / 2) + 1; j < width; j++) {
                sumq3 += grid[i][j];
            }
        }
        for (int i = (height / 2) + 1; i < height; i++) {
            for (int j = (width / 2) + 1; j < width; j++) {
                sumq4 += grid[i][j];
            }
        }

        score = sumq1 * sumq2 * sumq3 * sumq4;

        System.out.println(score);
    }

    private static void calculateFinalPos(int iposx, int iposy, int vx, int vy, int count) {
        int fposx = iposx + count*vx;
        int fposy = iposy + count*vy;
        fposx %= width;
        fposy %= height;
        if (fposx < 0) fposx += width;
        if (fposy < 0) fposy += height;
        grid[fposy][fposx] += 1;
    }
}
