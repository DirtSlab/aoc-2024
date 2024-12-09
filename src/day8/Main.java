package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static int[][] antinodeArray = new int[50][50];
    static int[][] repeatedAntinodeArray = new int[50][50];

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/day8/input.txt"));
        String[] lines = new String[50];

        for (int i = 0; i < 50; i++) {
            lines[i] = scanner.nextLine();
        }
        scanner.close();

        int sum = 0;
        int sum2 = 0;
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (lines[i].charAt(j) != '.') {
                    char node = lines[i].charAt(j);
                    int nodex = j;
                    int nodey = i;

                    for (int l = j + 1; l < 50; l++) {
                        if (lines[i].charAt(l) == node) {
                            sum += (calculateDistance(nodex, nodey, l, i));
                            sum2 += calculateRepeatedDistance(nodex, nodey, l, i);
                        }
                    }
                    for (int k = i + 1; k < 50; k++) {
                        for (int l = 0; l < 50; l++) {
                            if (lines[k].charAt(l) == node) {
                                sum += (calculateDistance(nodex, nodey, l, k));
                                sum2 += (calculateRepeatedDistance(nodex, nodey, l, k));
                            }
                        }
                    }

                }
            }
        }
        System.out.println(sum);
        System.out.println(sum2);
    }

    public static int calculateDistance(int nodex, int nodey, int anodex, int anodey) {
        int sum = 0;
        int diffx = anodex - nodex;
        int diffy = anodey - nodey;
        if ((anodex + diffx < 50) && (anodex + diffx >= 0) && (anodey + diffy < 50)) {
            if (antinodeArray[anodey + diffy][anodex + diffx] == 0) {
                sum += 1;
                antinodeArray[anodey + diffy][anodex + diffx] = 1;
            };
        }
        if (((nodex - diffx) >= 0) && (nodex - diffx < 50) && ((nodey - diffy) >= 0)) {
            if (antinodeArray[nodey - diffy][nodex - diffx] == 0) {
                sum += 1;
                antinodeArray[nodey - diffy][nodex - diffx] = 1;
            }
        }
        return sum;
    }

    public static int calculateRepeatedDistance(int nodex, int nodey, int anodex, int anodey) {
        int sum = 0;
        int diffx = anodex - nodex;
        int diffy = anodey - nodey;
        int k = 0;
        while (k < 50) {
            if (((anodex + k*diffx) < 50) && ((anodex + k*diffx) >= 0) && ((anodey + k*diffy) < 50)) {
                if (repeatedAntinodeArray[anodey + k*diffy][anodex + k*diffx] == 0) {
                    sum += 1;
                    repeatedAntinodeArray[anodey + k*diffy][anodex + k*diffx] = 1;
                }
                ;
            }
            if (((nodex - k*diffx) >= 0) && ((nodex - k*diffx) < 50) && ((nodey - k*diffy) >= 0)) {
                if (repeatedAntinodeArray[nodey - k*diffy][nodex - k*diffx] == 0) {
                    sum += 1;
                    repeatedAntinodeArray[nodey - k*diffy][nodex - k*diffx] = 1;
                }
            }
            k++;
        }
        return sum;
    }
}
