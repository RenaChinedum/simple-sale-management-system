package com.rena.simplemanagementsystem.challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniqueTriplets {
    public static List<List<Integer>> findTriplets(int[] nums, int targetSum) {
        List<List<Integer>> triplets = new ArrayList<>();

        if (nums == null || nums.length < 3)
            return triplets;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int left = i + 1;
                int right = nums.length - 1;
                int target = targetSum - nums[i];

                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        triplets.add(Arrays.asList(nums[i], nums[left], nums[right]));

                        // avoid duplicates
                        while (left < right && nums[left] == nums[left + 1])
                            left++;
                        while (left < right && nums[right] == nums[right - 1])
                            right--;

                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return triplets;
    }

    public static void main(String[] args) {
        int[] nums = { -1, 0, 1, 2, -1, -4 };
        int targetSum = 2;
        List<List<Integer>> result = findTriplets(nums, targetSum);

        for (List<Integer> triplet : result) {
            System.out.println(triplet);
        }
    }
}
