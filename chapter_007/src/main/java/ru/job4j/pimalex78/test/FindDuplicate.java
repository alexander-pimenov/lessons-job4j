package ru.job4j.pimalex78.test;

import java.util.Arrays;

public class FindDuplicate {

    public static void main(String[] args) {
        int[] input1 = {1, 2, 3, 3, 4, 4, 5, 1, 2, 3, 3, 4, 4, 5, 1, 2, 3, 3, 4, 4, 5, 1, 2, 3, 3, 4, 4, 5, 1, 2, 3, 3, 4, 4, 5, 1, 2, 3, 3, 4, 4, 5, 1, 2, 3, 3, 4, 4, 5, 1, 2, 3, 3, 4, 4, 5};
        int[] input2 = new int[100];
        for (int i = 0; i < 100; i++) {
            input2[i] = i;
        }

        final boolean result1 = containsDuplicate(input1);
        System.out.println(result1);
        final boolean result2 = containsDuplicate(input2);
        System.out.println(result2);
        //System.out.println("----------------------------");
        final boolean res1 = containsDup(input1);
        System.out.println(res1);
        final boolean res2 = containsDup(input2);
        System.out.println(res2);
    }

    private static boolean containsDuplicate(int[] nums) {
        final long start = System.currentTimeMillis();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - start) + "\n");
        return false;
    }

    private static boolean containsDup(int[] nums) {
        final long start = System.currentTimeMillis();
        final long count = Arrays.stream(nums).distinct().count();
        System.out.println("Время выполнения: " + (System.currentTimeMillis() - start) + "\n");
        return nums.length == count;
    }
}
