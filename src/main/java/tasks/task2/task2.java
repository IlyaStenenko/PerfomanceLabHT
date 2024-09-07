package tasks.task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class task2 {

    public static class Coordinate {
        double x;
        double y;
        Coordinate(double coor1, double coor2) {
            x = coor1;
            y = coor2;
        }
        double distance(Coordinate point) {
            return Math.sqrt(Math.pow(point.x - x,2) +
                    Math.pow(point.y - y,2));
        }
    }

    public static void main(String[] args) {
        String pathFile1 = args[0];
        String pathFile2 = args[1];
        double r = 0;
        Coordinate centre = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathFile1));
            String line = reader.readLine();
            String[] s = line.split(" ");
            centre = new Coordinate(Double.parseDouble(s[0]),Double.parseDouble(s[1]));
            line = reader.readLine();
            r = Double.parseDouble(line);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Coordinate> coordinates = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathFile2));
            String line = reader.readLine();
            do {
                String[] s = line.split(" ");
                coordinates.add(new Coordinate(Double.parseDouble(s[0]),Double.parseDouble(s[1])));
                line = reader.readLine();
            } while (line != null);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Coordinate coordinate : coordinates) {
            assert centre != null;
            if (coordinate.distance(centre) < r)
                System.out.println(1);
            else if (coordinate.distance(centre) == r) {
                System.out.println(0);
            } else System.out.println(2);
        }
    }
    }
