package tasks.task1;

import java.util.ArrayList;
import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {
        int n,m;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        ArrayList<Integer> way = new ArrayList<>();
        int i = 1;
        do {
            way.add(i == 0 ? n : i);
            i = (i + m - 1) % n;
        } while (i != 1);
        for (Integer integer : way) {
            System.out.print(integer + " ");
        }
    }
}
