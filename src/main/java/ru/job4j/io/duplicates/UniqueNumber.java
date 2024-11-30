package ru.job4j.io.duplicates;

import java.util.Arrays;
import java.util.HashSet;

public class UniqueNumber {
        public static void main(String[] args) {
            int[] nums = {1, 12, 2, 1, 2};
            System.out.println(Arrays.toString(findSingleNumber(nums)));
        }

        public static int[] findSingleNumber(int[] nums) {
            int sumAll = 0;
            int sum = 0;
            HashSet<Integer> uniqueNumbers = new HashSet<>();
            for (int num : nums) {
                sumAll += num;
                if (uniqueNumbers.add(num)) {
                    sum += num;
                }
            }

            return new int[] {2 * sum - sumAll};
        }
    }




