package day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

// THIS CODE IS TRASHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH

public class Main {
    static int size = 50;
    static int dwidth = 2 * size;

    static char[][] map = new char[size][size];
    static char[][] wideMap = new char[size][dwidth];
    static int posx = 0;
    static int posy = 0;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/day15/input.txt"));

        for (int i = 0; i < size; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < size; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '@') {
                    posx = j;
                    posy = i;
                }
            }
        }

        sc.nextLine();
        String instructions = sc.nextLine();
        sc.close();

        for (int i = 0; i < instructions.length(); i++) {
            moveBoxes(instructions.charAt(i));
//            for (int j = 0; j < size; j++) {
//                for (int k = 0; k < size; k++) {
//                    System.out.print(map[j][k]);
//                }
//                System.out.println();
//            }
        }

        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == 'O') {
                    sum += 100 * i + j;
                }
            }
        }


        System.out.println(sum);


        //p2

        sc = new Scanner(new File("src/day15/input.txt"));
        for (int i = 0; i < size; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < size; j++) {
                switch (line.charAt(j)) {
                    case 'O' -> {
                        wideMap[i][2 * j] = '[';
                        wideMap[i][2 * j + 1] = ']';
                    }
                    case '.' -> {
                        wideMap[i][2 * j] = '.';
                        wideMap[i][2 * j + 1] = '.';
                    }
                    case '#' -> {
                        wideMap[i][2 * j] = '#';
                        wideMap[i][2 * j + 1] = '#';
                    }
                    case '@' -> {
                        wideMap[i][2 * j] = '@';
                        wideMap[i][2 * j + 1] = '.';
                    }
                }
                if (line.charAt(j) == '@') {
                    posx = 2 * j;
                    posy = i;
                }
            }
        }

        int boxNum = countBoxesWide();
        for (int i = 0; i < instructions.length(); i++) {
//            if (i == 5867) {
//                for (int j = 0; j < size; j++) {
//                    for (int k = 0; k < dwidth; k++) {
//                        System.out.print(wideMap[j][k]);
//                    }
//                    System.out.println();
//                }
//                System.out.println(instructions.charAt(i));
//            }
            moveBigBoxes(instructions.charAt(i));
//            for (int j = 0; j < size; j++) {
//                for (int k = 0; k < dwidth; k++) {
//                    System.out.print(wideMap[j][k]);
//                }
//                System.out.println();
//            }
//            System.out.println(instructions.charAt(i));
            if (countBoxesWide() != boxNum) {
                for (int j = 0; j < size; j++) {
                    for (int k = 0; k < dwidth; k++) {
                        System.out.print(wideMap[j][k]);
                    }
                    System.out.println();
                }
                System.out.println(instructions.charAt(i));
                System.out.println();
                boxNum = countBoxesWide();
            }
        }

        sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < dwidth; j++) {
                if (wideMap[i][j] == '[') {
                    sum += 100 * i + j;
                }
            }
        }
        System.out.println(sum);

    }

    private static void moveBigBoxes(char c) {
        int x = posx;
        int y = posy;
        switch (c) {
            case '^':
                boolean end = false;
                ArrayList<Integer> xcoords = new ArrayList<>();
                xcoords.add(x);
                ArrayList<Integer> ycoords = new ArrayList<>();
                ycoords.add(y);
                int checked = 0;
                while (!end) {
                    end = true;
                    boolean ignore = false;
                    for (int i = checked; i < xcoords.size(); i++) {
                        if (ignore) {
                            ignore = false;
                        } else if (wideMap[ycoords.get(i) - 1][xcoords.get(i)] == '[') {
                            xcoords.add(xcoords.get(i));
                            ycoords.add(ycoords.get(i) - 1);
                            xcoords.add(xcoords.get(i) + 1);
                            ycoords.add(ycoords.get(i) - 1);
                            end = false;
                            checked++;
                            if (wideMap[ycoords.get(i) - 1][xcoords.get(i)] == wideMap[ycoords.get(i)][xcoords.get(i)]) {
                                checked++;
                                ignore = true;
                            }
                        } else if (wideMap[ycoords.get(i) - 1][xcoords.get(i)] == ']') {
                            xcoords.add(xcoords.get(i));
                            ycoords.add(ycoords.get(i) - 1);
                            xcoords.add(xcoords.get(i) - 1);
                            ycoords.add(ycoords.get(i) - 1);
                            end = false;
                            checked++;
                            if (wideMap[ycoords.get(i) - 1][xcoords.get(i)] == wideMap[ycoords.get(i)][xcoords.get(i)]) {
                                checked++;
                                ignore = true;
                            }
                        } else if (wideMap[ycoords.get(i) - 1][xcoords.get(i)] == '#') {
                            return;
                        } else {
                            checked++;
                        }
                    }
                }
                for (int i = 0; i < xcoords.size(); i++) {
                    for (int j = i + 1; j < xcoords.size(); j++) {
                        if (Objects.equals(xcoords.get(i), xcoords.get(j)) && Objects.equals(ycoords.get(i), ycoords.get(j))) {
                            xcoords.remove(j);
                            ycoords.remove(j);
                        }
                    }
                }
                for (int i = xcoords.size() - 1; i >= 0; i--) {
                    wideMap[ycoords.get(i) - 1][xcoords.get(i)] = wideMap[ycoords.get(i)][xcoords.get(i)];
                    wideMap[ycoords.get(i)][xcoords.get(i)] = '.';
                }
                wideMap[y][x] = '.';
                posy--;
                break;
            case 'v':
                end = false;
                xcoords = new ArrayList<>();
                xcoords.add(x);
                ycoords = new ArrayList<>();
                ycoords.add(y);
                checked = 0;
                while (!end) {
                    end = true;
                    boolean ignore = false;
                    for (int i = checked; i < xcoords.size(); i++) {
                        if (ignore) {
                            ignore = false;
                        } else if (wideMap[ycoords.get(i) + 1][xcoords.get(i)] == '[') {
                            xcoords.add(xcoords.get(i));
                            ycoords.add(ycoords.get(i) + 1);
                            xcoords.add(xcoords.get(i) + 1);
                            ycoords.add(ycoords.get(i) + 1);
                            end = false;
                            checked++;
                            if (wideMap[ycoords.get(i) + 1][xcoords.get(i)] == wideMap[ycoords.get(i)][xcoords.get(i)]) {
                                checked++;
                                ignore = true;
                            }
                        } else if (wideMap[ycoords.get(i) + 1][xcoords.get(i)] == ']') {
                            xcoords.add(xcoords.get(i));
                            ycoords.add(ycoords.get(i) + 1);
                            xcoords.add(xcoords.get(i) - 1);
                            ycoords.add(ycoords.get(i) + 1);
                            end = false;
                            checked++;
                            if (wideMap[ycoords.get(i) + 1][xcoords.get(i)] == wideMap[ycoords.get(i)][xcoords.get(i)]) {
                                checked++;
                                ignore = true;
                            }
                        } else if (wideMap[ycoords.get(i) + 1][xcoords.get(i)] == '#') {
                            return;
                        } else {
                            checked++;
                        }
                    }
                }
                for (int i = 0; i < xcoords.size(); i++) {
                    for (int j = i + 1; j < xcoords.size(); j++) {
                        if (Objects.equals(xcoords.get(i), xcoords.get(j)) && Objects.equals(ycoords.get(i), ycoords.get(j))) {
                            xcoords.remove(j);
                            ycoords.remove(j);
                        }
                    }
                }
                for (int i = xcoords.size() - 1; i >= 0; i--) {
                    wideMap[ycoords.get(i) + 1][xcoords.get(i)] = wideMap[ycoords.get(i)][xcoords.get(i)];
                    wideMap[ycoords.get(i)][xcoords.get(i)] = '.';
                }
                wideMap[y][x] = '.';
                posy++;
                break;
            case '<':
                while (wideMap[y][x - 1] == '[' || wideMap[y][x - 1] == ']') {
                    x--;
                }
                if (wideMap[y][x - 1] == '.') {
                    while (wideMap[y][x] == '[' || wideMap[y][x] == ']') {
                        wideMap[y][x - 1] = wideMap[y][x];
                        x++;
                    }
                    wideMap[y][x - 1] = '@';
                    wideMap[y][x] = '.';
                    posx--;
                }
                break;
            case '>':
                while (wideMap[y][x + 1] == '[' || wideMap[y][x + 1] == ']') {
                    x++;
                }
                if (wideMap[y][x + 1] == '.') {
                    while (wideMap[y][x] == '[' || wideMap[y][x] == ']') {
                        wideMap[y][x + 1] = wideMap[y][x];
                        x--;
                    }
                    wideMap[y][x + 1] = '@';
                    wideMap[y][x] = '.';
                    posx++;
                }
                break;
        }
    }

    private static void moveBoxes(char c) {
        int x = posx;
        int y = posy;
        switch (c) {
            case '^':
                while (map[y - 1][x] == 'O') {
                    y--;
                }
                if (map[y - 1][x] == '.') {
                    while (map[y][x] == 'O') {
                        map[y - 1][x] = 'O';
                        y++;
                    }
                    map[y - 1][x] = '@';
                    map[y][x] = '.';
                    posy--;
                }
                break;
            case 'v':
                while (map[y + 1][x] == 'O') {
                    y++;
                }
                if (map[y + 1][x] == '.') {
                    while (map[y][x] == 'O') {
                        map[y + 1][x] = 'O';
                        y--;
                    }
                    map[y + 1][x] = '@';
                    map[y][x] = '.';
                    posy++;
                }
                break;
            case '<':
                while (map[y][x - 1] == 'O') {
                    x--;
                }
                if (map[y][x - 1] == '.') {
                    while (map[y][x] == 'O') {
                        map[y][x - 1] = 'O';
                        x++;
                    }
                    map[y][x - 1] = '@';
                    map[y][x] = '.';
                    posx--;
                }
                break;
            case '>':
                while (map[y][x + 1] == 'O') {
                    x++;
                }
                if (map[y][x + 1] == '.') {
                    while (map[y][x] == 'O') {
                        map[y][x + 1] = 'O';
                        x--;
                    }
                    map[y][x + 1] = '@';
                    map[y][x] = '.';
                    posx++;
                }
                break;
        }
    }

    public static int countBoxesWide() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < dwidth; j++) {
                if (wideMap[i][j] == '[') {
                    sum++;
                }
            }
        }
        return sum;
    }
}
