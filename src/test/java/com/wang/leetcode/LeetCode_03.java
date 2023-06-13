package com.wang.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_03 {

    public static void main(String[] args) {
        String str = "aab";
        int count = lengthOfLongestSubstring(str);
        System.out.println("count = " + count);
    }

    public static int lengthOfLongestSubstring(String s) {
        int count = 0;
        int max = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0, temp = 0; i < s.length(); i++) {
            if (set.add(s.charAt(i))) {
                count++;
            } else {
                max = Integer.max(count, max);
                count = 0;
                i = temp;
                temp++;
                set.clear();
            }
        }
        return Integer.max(count, max);
    }

}
