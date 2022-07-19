package com.zt.javastudy.leetcode;

/**
 * @author zhengtao
 * @create 2022/7/19 22:46
 * 字符串相关题目
 */
public class LeetCodeForString {
    public static void main(String[] args) {
        LeetCodeForString leetCodeForString = new LeetCodeForString();
        System.out.println(leetCodeForString.reverseStr("krmyfshbspcgtesxnnljhfursyissjnsocgdhgfxubewllxzqhpasguvlrxtkgatzfybprfmmfithphckksnvjkcvnsqgsgosfxc", 20));
    }

    /**
     * 344. 反转字符串
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * <p>
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = ["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     * 示例 2：
     * <p>
     * 输入：s = ["H","a","n","n","a","h"]
     * 输出：["h","a","n","n","a","H"]
     * <p>
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 105
     * s[i] 都是 ASCII 码表中的可打印字符
     *
     * @param s
     */
    public void reverseString(char[] s) {
        int length = s.length - 1;
        for (int i = 0; i <= length / 2; i++) {
            char temp = s[i];
            s[i] = s[length - i];
            s[length - i] = temp;
        }
    }

    public String reverseStr(String s, int k) {
        char[] c = s.toCharArray();
        int left = 0, index = 0, length = s.length();
        while (length - index >= 2 * k || (length - index >= k && length - index < 2 * k)) {
            index += 2 * k;
            int right = left + k - 1;
            reverse(c, left, right);
            left = index;
        }
        if (left != length) {
            int right = length - 1;
            reverse(c, left, right);
        }
        return new String(c);
    }

    public void reverse(char[] c, int left, int right) {
        while (left <= right) {
            char temp = c[left];
            c[left] = c[right];
            c[right] = temp;
            left++;
            right--;
        }
    }
}
