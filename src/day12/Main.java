package day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int size1 = 140;
    static int size2 = size1 + 1;
    static int size3 = size2 + 1;
    
    static char[][] chars = new char[size1][size1];
    static int[][] ids = new int[size1][size1];

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/day12/input.txt"));

        for (int i = 0; i < size1; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < size1; j++) {
                chars[i][j] = line.charAt(j);
            }
        }
        scanner.close();

        int count = 1;
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size1; j++) {
                if (i > 0 && chars[i-1][j] == chars[i][j]) {
                    ids[i][j] = ids[i-1][j];
                }
                if (j > 0 && chars[i][j-1] == chars[i][j]) {
                    if (ids[i][j] == 0) {
                        ids[i][j] = ids[i][j-1];
                    } else {
                        fixArray(ids[i][j-1], ids[i][j]);
                    }
                }
                if (ids[i][j] == 0) {
                    ids[i][j] = count;
                    count++;
                }
            }
        }

        ArrayList<Integer> idsList = new ArrayList<>();
        ArrayList<Integer> perimeters = new ArrayList<>();
        ArrayList<Integer> areas = new ArrayList<>();

        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size1; j++) {
                int perimeter = 4;
                if (i > 0 && ids[i-1][j] == ids[i][j]) {
                    perimeter--;
                }
                if (j > 0 && ids[i][j-1] == ids[i][j]) {
                    perimeter--;
                }
                if (i < size1 - 1 && ids[i+1][j] == ids[i][j]) {
                    perimeter--;
                }
                if (j < size1 - 1 && ids[i][j+1] == ids[i][j]) {
                    perimeter--;
                }

                if (idsList.contains(ids[i][j])) {
                    perimeters.set(idsList.indexOf(ids[i][j]), perimeters.get(idsList.indexOf(ids[i][j])) + perimeter);
                    areas.set(idsList.indexOf(ids[i][j]), areas.get(idsList.indexOf(ids[i][j])) + 1);
                } else {
                    idsList.add(ids[i][j]);
                    perimeters.add(perimeter);
                    areas.add(1);
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < idsList.size(); i++) {
            sum += (perimeters.get(i) * areas.get(i));
        }

        System.out.println(sum);

        int[][] newids = new int[size3][size3];
        for (int i = 0; i < size3; i++) {
            newids[i][0] = -1;
            newids[i][size2] = -1;
            newids[0][i] = -1;
            newids[size2][i] = -1;
        }
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size1; j++) {
                newids[i + 1][j + 1] = ids[i][j];
            }
        }

        int[][] newids2 = new int[size3][size3];
        for (int i = 0; i < size3; i++) {
            for (int j = 0; j < size3; j++) {
                newids2[i][j] = newids[j][i];
            }
        }

        ArrayList<Integer> sides = new ArrayList<>();
        for (int i = 0; i < idsList.size(); i++) {
            int edgesum = getEdgesum(idsList, i, newids);
            edgesum += getEdgesum(idsList, i, newids2);
            sides.add(edgesum);
        }

        int sum2 = 0;
        for (int i = 0; i < idsList.size(); i++) {
            sum2 += (sides.get(i) * areas.get(i));
        }
        System.out.println(sum2);
    }

    private static int getEdgesum(ArrayList<Integer> idsList, int i, int[][] newids) {
        int edgesum = 0;
        ArrayList<Integer> inside = new ArrayList<>();
        ArrayList<Integer> outside = new ArrayList<>();
        int id = idsList.get(i);
        for (int j = 0; j < size3; j++) {
            for (int k = 0; k < size3 - 1; k++) {
                if (newids[j][k] == id && newids[j][k + 1] != id &&
                        !(inside.contains(k) && outside.contains(k+1) &&
                                (outside.get(inside.indexOf(k)).equals(k+1) || outside.get(inside.lastIndexOf(k)).equals(k+1)))) {
                    inside.add(k);
                    outside.add(k + 1);
                    edgesum++;
                }
                if (newids[j][k] != id && newids[j][k + 1] == id &&
                        !(outside.contains(k) && inside.contains(k+1) &&
                                (inside.get(outside.indexOf(k)).equals(k+1) || inside.get(outside.lastIndexOf(k)).equals(k+1)))) {
                    inside.add(k + 1);
                    outside.add(k);
                    edgesum++;
                }
            }
            for (int k = inside.size() - 1; k >= 0; k--) {
                if (!(newids[j][inside.get(k)] == id && newids[j][outside.get(k)] != id)) {
                    inside.remove(k);
                    outside.remove(k);
                }
            }
        }
        return edgesum;
    }

//    private static int traceShape(int starty, int startx, int id) {
//        int dir = 0;
//        int sides = 0;
//        int y = starty;
//        int x = startx;
//
//        do {
//            boolean changed = false;
//            switch (dir) {
//                case 0:
//                    if (y >= 0 && y < size1) {
//                        if (ids[y][x] == id) {
//                            dir = changeDir(dir);
//                            changed = true;
//                        }
//                    }
//            }
//            if (changed) sides++;
//        } while (dir != 0 && y != starty && x != startx);
//
//        return sides;
//    }
//
//    private static int changeDir(int dir) {
//        return (dir + 1) % 4;
//    }

    private static void fixArray(int from, int to) {
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size1; j++) {
                if (ids[i][j] == from) {
                    ids[i][j] = to;
                }
            }
        }
    }
}
