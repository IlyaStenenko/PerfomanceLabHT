package tasks.task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class task4 {

    public static int minMoves(List<Integer> nums) {

        Collections.sort(nums);

        int median = nums.get(nums.size() / 2);

        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }

        return moves;
    }

    public static void main(String[] args) {
        String pathFile = args[0];
        List<Integer> m = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathFile));
            String line = reader.readLine();
            do {
                m.add(Integer.parseInt(line));
                line = reader.readLine();
            } while (line != null);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(minMoves(m));
    }
}
