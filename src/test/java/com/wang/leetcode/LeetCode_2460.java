package com.wang.leetcode;

public class LeetCode_2460 {

    public static void main(String[] args) {
        int[] nums = {847,847,0,0,0,399,416,416,879,879,206,206,206,272};
        int[] result = applyOperations(nums);
        for (int i : result) {
            System.out.print(i+",");
        }
    }
    public static int[] applyOperations(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i+1] == nums[i]) {
                nums[i] += nums[i ];
                nums[i+1] = 0;
            }
        }
        int index = 0;
        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] == 0) {
                index ++;
            } else {
                if (index !=0){
                    nums[i-index] = nums[i];
                    nums[i]=0;
                }
            }
        }
        return nums;
    }
}
