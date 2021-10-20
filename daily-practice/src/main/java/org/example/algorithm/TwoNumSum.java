package org.example.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 */
public class TwoNumSum {

    public static void main(String[] args) {
        int[] nums = {2, 4, 3};
        int target = 6;
        int[] result = numSum(nums, target);
        for (int i : result) {
            System.out.println(i);
        }
    }


    private static int[] numSum(int[] nums, int target) {
        if (null != nums && nums.length > 0) {
            Map<Integer, Integer> mup = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (null != mup.get(target - nums[i])) {
                    return new int[]{mup.get(target - nums[i]), i};
                }
                mup.put(nums[i], i);
            }
        }
        return new int[0];

    }






}
