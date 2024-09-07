package tasks.task3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class task3 {

    public class Test {
        @SerializedName("id")
        int id;
        @SerializedName("title")
        String title;
        @SerializedName("value")
        String value;
        @SerializedName("values")
        List<Test> values;
    }

    class TestsWrapper {
        @SerializedName("tests")
        public List<Test> tests;
    }

    public static class Value implements Comparable<Value>{
        @SerializedName("id")
        int id;
        @SerializedName("value")
        String value;
        Value(int id, String value) {
            this.id = id;
            this.value = value;
        }

        @Override
        public int compareTo(Value o) {
            return Integer.compare(this.id, o.id);
        }
    }

    public class ValuesWrapper {
        @SerializedName("values")
        List<Value> values;
    }

    public static void takeTest(List<Test> tests, List<Value> values) {
        for (Test test : tests) {
            if (Objects.equals(test.value, "")) {
                Optional<Value> foundValue = values.stream()
                        .filter(v -> v.id == test.id)
                        .findFirst();
                foundValue.ifPresent(value -> test.value = value.value);
                System.out.println("Test id = " + test.id + " , value = " + test.value);
            }
            if (test.values != null)
                takeTest(test.values,values);
        }
    }

    public static void main(String[] args) {
        String pathFileValues = args[0];
        String pathFileTests = args[1];
        String pathFileReport = args[2];

        Gson gson = new GsonBuilder().setPrettyPrinting().create();;

        try (FileWriter fileWriter = new FileWriter(pathFileReport)){
            FileReader readerTest = new FileReader(pathFileTests);
            FileReader readerValue = new FileReader(pathFileValues);

            TestsWrapper testsWrapper = gson.fromJson(readerTest, TestsWrapper.class);
            ValuesWrapper valuesWrapper = gson.fromJson(readerValue, ValuesWrapper.class);

            takeTest(testsWrapper.tests,valuesWrapper.values);

            String json = gson.toJson(testsWrapper.tests);
            //System.out.println(json);
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
