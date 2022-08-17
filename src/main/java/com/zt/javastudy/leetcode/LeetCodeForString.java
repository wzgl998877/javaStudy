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
        System.out.println(leetCodeForString.reverseWords("  hello  world  "));
        System.out.println(leetCodeForString.reverseWords1("  hello  world  "));
        System.out.println(leetCodeForString.reverseWords2("  hello  world  "));
        System.out.println(leetCodeForString.myAtoi("-2147483647"));
        System.out.println(leetCodeForString.myAtoi1("2147483648"));
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

    /**
     * 151. 颠倒字符串中的单词
     * 给你一个字符串 s ，颠倒字符串中 单词 的顺序。
     * <p>
     * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
     * <p>
     * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
     * <p>
     * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "the sky is blue"
     * 输出："blue is sky the"
     * 示例 2：
     * <p>
     * 输入：s = "  hello world  "
     * 输出："world hello"
     * 解释：颠倒后的字符串中不能存在前导空格和尾随空格。
     * 示例 3：
     * <p>
     * 输入：s = "a good   example"
     * 输出："example good a"
     * 解释：如果两个单词间有多余的空格，颠倒后的字符串需要将单词间的空格减少到仅有一个。
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        int left = s.length() - 1, right = left + 1;
        while (left >= 0) {
            if (s.charAt(left) == ' ') {
                if (left + 1 != right) {
                    stringBuilder.append(s, left + 1, right);
                    if (stringBuilder.length() != 0) {
                        stringBuilder.append(" ");
                    }
                }
                right = left;
            }
            left--;
        }
        stringBuilder.append(s, left + 1, right);
        // 去除后面空格
        right = stringBuilder.length();
        for (int i = stringBuilder.length() - 1; i >= 0; i--) {
            if (stringBuilder.charAt(i) != ' ') {
                break;
            }
            right--;
        }
        return stringBuilder.substring(0, right);
    }

    public String reverseWords2(String s) {
        int i = 0, right = s.length();
        // 去除前面空格
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(i) != ' ') {
                break;
            }
            i++;
        }
        // 去除后面空格
        for (int j = s.length() - 1; j >= 0; j--) {
            if (s.charAt(i) != ' ') {
                break;
            }
            right--;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int left = right - 1;
        while (left >= i) {
            if (s.charAt(left) == ' ') {
                if (left + 1 != right) {
                    stringBuilder.append(s, left + 1, right);
                    stringBuilder.append(" ");
                }
                right = left;
            }
            left--;
        }
        stringBuilder.append(s, left + 1, right);
        return stringBuilder.toString();
    }

    public String reverseWords1(String s) {
        //源字符数组
        char[] initialArr = s.toCharArray();
        //新字符数组
        char[] newArr = new char[initialArr.length + 1];//下面循环添加"单词 "，最终末尾的空格不会返回
        int newArrPos = 0;
        //i来进行整体对源字符数组从后往前遍历
        int i = initialArr.length - 1;
        while (i >= 0) {
            while (i >= 0 && initialArr[i] == ' ') {
                i--;
            }  //跳过空格
            //此时i位置是边界或!=空格，先记录当前索引，之后的while用来确定单词的首字母的位置
            int right = i;
            while (i >= 0 && initialArr[i] != ' ') {
                i--;
            }
            //指定区间单词取出(由于i为首字母的前一位，所以这里+1,)，取出的每组末尾都带有一个空格
            for (int j = i + 1; j <= right; j++) {
                newArr[newArrPos++] = initialArr[j];
                if (j == right) {
                    newArr[newArrPos++] = ' ';//空格
                }
            }
        }
        //若是原始字符串没有单词，直接返回空字符串；若是有单词，返回0-末尾空格索引前范围的字符数组(转成String返回)
        if (newArrPos == 0) {
            return "";
        } else {
            return new String(newArr, 0, newArrPos - 1);
        }
    }

    /**
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int left = 0, right = s.length() - 1;
        int flag = 0;
        for (int i = 0; i <= right; i++) {
            if ((s.charAt(i) == ' ' && flag == 0) || s.charAt(i) == '0') {
                left++;
                if (s.charAt(i) == '0') {
                    flag = 1;
                }
            } else {
                break;
            }
        }
        if (left <= right && (s.charAt(left) == '-' || s.charAt(left) == '+') && flag == 0) {
            if (s.charAt(left) == '-') {
                flag = 2;
            }
            left++;
        }
        if (flag != 0) {
            for (int i = left; i <= right; i++) {
                if (s.charAt(i) == '0') {
                    left++;
                } else {
                    break;
                }
            }
        }
        int result = 0, nums = 0;
        boolean temp = false;
        for (int i = right; i >= left; i--) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                if (nums > 10 || (Math.pow(10, nums) * (s.charAt(i) - '0') + result > Integer.MAX_VALUE)) {
                    result = Integer.MAX_VALUE;
                    temp = true;
                } else {
                    result += nums == 0 ? (s.charAt(i) - '0') : Math.pow(10, nums) * (s.charAt(i) - '0');
                }
                nums++;
            } else {
                result = 0;
                nums = 0;
            }
        }
        if (flag == 2) {
            result = result == Integer.MAX_VALUE && temp ? Integer.MIN_VALUE : -result;
        }
        return result;
    }

    public int myAtoi1(String s) {
        if(s.length() == 0) {
            return 0;
        }
        int left = 0, right = s.length() - 1;
        boolean flag = false;
        // 去除前面的空格
        for (int i = 0; i <= right; i++) {
            if (s.charAt(i) != ' ') {
                break;
            }
            left++;
        }
        // 去除正负号
        if (left <= right && (s.charAt(left) == '-' || s.charAt(left) == '+')) {
            if (s.charAt(left) == '-') {
                flag = true;
            }
            left++;
        }
        int result = 0;
        for (int i = left; i <= right; i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int num = s.charAt(i) - '0';
                if (result > (Integer.MAX_VALUE - num) / 10) {
                    return flag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result = result * 10 + num;
            } else {
                break;
            }
        }
        return flag ? -result : result;
    }

}
