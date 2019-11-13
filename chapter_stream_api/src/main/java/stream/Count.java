package stream;

import java.util.List;
import java.util.stream.Collectors;

public class Count {
    public static int count1(List<Integer> data) {
        return data.stream()
                .filter(x -> x % 2 == 0)
                .map(x -> x * x)
                .reduce(Integer::sum)
                .orElse(-1);
        //.get();
    }


    public static int count2(List<Integer> data) {
        return data.stream()
                .filter(x -> x % 2 == 0)
                .mapToInt(x -> x * x)
                .sum();
    }
    public static int count3(List<Integer> data) {
        return data.stream()
                .mapToInt(p -> p % 2 == 0 ? p * p : 0)
                .sum();
    }




    public static void main(String[] args) {
        List<Integer> data = List.of(1, 2, 3, 4, 5);
        int count = count1(data);
        System.out.println(count);

        System.out.println(count2(data));
        System.out.println(count3(data));
    }

}
