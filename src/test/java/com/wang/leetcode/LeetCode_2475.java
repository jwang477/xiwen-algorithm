package com.wang.leetcode;

import java.util.Arrays;

/**
 * 给你一个下标从 0 开始的正整数数组 nums 。请你找出并统计满足下述条件的三元组 (i, j, k) 的数目：
 * <p>
 * 0 <= i < j < k < nums.length
 * nums[i]、nums[j] 和 nums[k] 两两不同 。
 * 换句话说：nums[i] != nums[j]、nums[i] != nums[k] 且 nums[j] != nums[k] 。
 * 返回满足上述条件三元组的数目。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/number-of-unequal-triplets-in-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 3 <= nums.length <= 100
 * 1 <= nums[i] <= 1000
 */
public class LeetCode_2475 {

    public static void main(String[] args) {
        int[] nums = {4, 4, 2, 4, 3};
        int count = unequalTriplets(nums);
        System.out.println("count = " + count);
    }

    public static int unequalTriplets(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] != nums[j] && nums[i] != nums[k] && nums[k] != nums[j]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static int unequalTripletsV2(int[] nums) {
        int count = 0;
        Arrays.sort(nums);


        return count;
    }

}
