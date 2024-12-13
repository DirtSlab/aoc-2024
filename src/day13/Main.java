package day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    static int countChanges = 0;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/day13/input.txt"));

        scanner.useDelimiter("Button A: X\\+|, Y\\+|Button B: X\\+|Prize: X=|\n|, Y=");

        long tokensum = 0;
        long bigtokensum = 0;
        while (scanner.hasNext()) {
            long ax = scanner.nextLong();
            long ay = scanner.nextLong();
            scanner.nextLine();
            long bx = scanner.nextLong();
            long by = scanner.nextLong();
            scanner.nextLine();
            long px = scanner.nextLong();
            long py = scanner.nextLong();
            scanner.nextLine();
            if (scanner.hasNextLine()) scanner.nextLine();

            tokensum += findBest(ax, ay, bx, by, px, py);
            bigtokensum += findBestBig(ax, ay, bx, by, 10000000000000L + px, 10000000000000L + py);
        }

        System.out.println(tokensum);
        System.out.println(bigtokensum);

    }

    private static long findBestBig(long ax, long ay, long bx, long by, long px, long py) {
        long apress = (((bx*py) - (by*px))/((bx*ay) - (by*ax)));
        long bpress = ((px - (apress*ax))/bx);
        if (ax*apress + bx*bpress == px && ay*apress + by*bpress == py) {
            return (apress * 3) + bpress;
        }
        return 0;
    }

    private static long findBest(long ax, long ay, long bx, long by, long px, long py) {
        long min = Long.MAX_VALUE;
        for (long i = 0; i < 100; i++) {
            for (long j = 0; j < 100; j++) {
                if ((i * ax + j * bx == px) && (i * ay + j * by == py)) {
                    long temp = (i * 3) + j;
                    if (min > temp) {
                        min = temp;
                        countChanges++;
                    }
                }
            }
        }
        if (min == Long.MAX_VALUE) min = 0;
        return min;
    }
}
