package day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[][] cleannums;
    static int startposx;
    static int startposy;

    public static void main(String[] args) throws FileNotFoundException {
        int[][] nums = new int[130][130];

        int posx = 0;
        int posy = 0;

        Scanner scanner = new Scanner(new File("src/day6/input.txt"));
        for (int i = 0; i < 130; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < 130; j++) {
                if (line.charAt(j) == '.') {
                    nums[i][j] = 0;
                } else if (line.charAt(j) == '#') {
                    nums[i][j] = 1;
                } else if (line.charAt(j) == '^') {
                    posx = j;
                    posy = i;
                    nums[i][j] = 2;
                }
            }
        }
        scanner.close();

        startposx = posx;
        startposy = posy;
        cleannums = Arrays.copyOf(nums, nums.length);

        int dir = 0;    //0 up 1 right 2 down 3 left

        boolean done = false;
        while (!done) {
            switch (dir) {
                case 0:
                    if (!(posy - 1 < 0) && nums[posy - 1][posx] != 1) {
                        posy--;
                        nums[posy][posx] = 2;
                    } else if (posy - 1 < 0) {
                        done = true;
                    } else {
                        dir = rotate(dir);
                    }
                    break;
                case 1:
                    if (!(posx + 1 >= 130) && nums[posy][posx + 1] != 1) {
                        posx++;
                        nums[posy][posx] = 2;
                    } else if (posx + 1 >= 130) {
                        done = true;
                    } else {
                        dir = rotate(dir);
                    }
                    break;
                case 2:
                    if (!(posy + 1 >= 130) && nums[posy + 1][posx] != 1) {
                        posy++;
                        nums[posy][posx] = 2;
                    } else if (posy + 1 >= 130) {
                        done = true;
                    } else {
                        dir = rotate(dir);
                    }
                    break;
                case 3:
                    if (!(posx - 1 < 0) && nums[posy][posx - 1] != 1) {
                        posx--;
                        nums[posy][posx] = 2;
                    } else {
                        dir = rotate(dir);
                    }
                    break;
            }
//            for (int i = 0; i < 130; i++) {
//                System.out.println(Arrays.toString(nums[i]));
//            }
//            System.out.println();
        }

        System.out.println();
        int sum = 0;
        for (int i = 0; i < 130; i++) {
            for (int j = 0; j < 130; j++) {
                if (nums[i][j] == 2) {
                    sum++;
                }
            }
        }
        System.out.println(sum);

        //p2
        int sum2 = 0;
        for (int i = 0; i < 130; i++) {
            for (int j = 0; j < 130; j++) {
                if (nums[i][j] == 2) {
                    sum2 = test(j, i) ? sum2 + 1 : sum2;
                }
            }
        }
        System.out.println(sum2);

    }

    public static int rotate(int dir) {
        return (dir + 1) % 4;
    }

    public static boolean test(int xpos, int ypos) {
        int[][] nums = Arrays.copyOf(cleannums, cleannums.length);
        nums[ypos][xpos] = 1;

        int posy = startposy;
        int posx = startposx;

        int dir = 0;    //0 up 1 right 2 down 3 left

        int steps = 0;
        boolean done = false;
        while (!done && steps < 169000) {
            switch (dir) {
                case 0:
                    if (!(posy - 1 < 0) && nums[posy - 1][posx] != 1) {
                        posy--;
                        nums[posy][posx] = 2;
                    } else if (posy - 1 < 0) {
                        done = true;
                    } else {
                        dir = rotate(dir);
                    }
                    break;
                case 1:
                    if (!(posx + 1 >= 130) && nums[posy][posx + 1] != 1) {
                        posx++;
                        nums[posy][posx] = 2;
                    } else if (posx + 1 >= 130) {
                        done = true;
                    } else {
                        dir = rotate(dir);
                    }
                    break;
                case 2:
                    if (!(posy + 1 >= 130) && nums[posy + 1][posx] != 1) {
                        posy++;
                        nums[posy][posx] = 2;
                    } else if (posy + 1 >= 130) {
                        done = true;
                    } else {
                        dir = rotate(dir);
                    }
                    break;
                case 3:
                    if (!(posx - 1 < 0) && nums[posy][posx - 1] != 1) {
                        posx--;
                        nums[posy][posx] = 2;
                    } else if (posx - 1 < 0) {
                        done = true;
                    } else {
                        dir = rotate(dir);
                    }
                    break;
            }
            steps++;
//            for (int i = 0; i < 130; i++) {
//                System.out.println(Arrays.toString(nums[i]));
//            }
//            System.out.println();
        }

        nums[ypos][xpos] = 0;

        return !done;
    }
}
