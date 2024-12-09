package day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/day9/input.txt"));

        String line = scanner.nextLine();
        scanner.close();
        ArrayList<Integer> places = new ArrayList<>();

        int counter = 0;
        for (int i = 0; i < line.length(); i++) {
            int i1 = line.charAt(i) - '0';
            for (int j = 0; j < i1; j++) {
                places.add(counter);
            }
            counter++;
            i++;
            if (i < line.length()) {
                i1 = line.charAt(i) - '0';
                for (int j = 0; j < i1; j++) {
                    places.add(-1);
                }
            }
        }

        //p1
//        for (int i = 0; i < places.size(); i++) {
//            if (places.get(i) == -1) {
//                places.set(i, places.get(places.size() - 1));
//                do {
//                    places.remove(places.size() - 1);
//                } while (places.get(places.size() - 1) == -1);
//            }
//        }

        //p2
        int pos = places.size() - 1;
        while (pos > 0) {
            int endpos = pos;
            int id = places.get(pos);
            while (places.get(endpos) == id && endpos != 0) {
                endpos--;
            }
            for (int i = 0; i < endpos; i++) {
                if (places.get(i) == -1) {
                    int count = 1;
                    while (places.get(i + count) == -1) {
                        count++;
                    }
                    if (pos - endpos <= count) {
                        count = pos - endpos;
                        while (count > 0) {
                            places.set(i + count - 1, places.get(pos));
                            count--;
                        }
                        for (int j = endpos + 1; j <= pos; j++) {
                            places.set(j, -1);
                        }
                        break;
                    }
                }
            }
            pos = endpos;
            while (places.get(pos) == -1) {
                pos--;
            }
        }

        long sum = 0;
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i) != -1) {
                sum += places.get(i) * i;
            }
        }

        System.out.println(sum);
    }
}
