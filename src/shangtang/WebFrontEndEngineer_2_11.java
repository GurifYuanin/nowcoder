package shangtang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* *
 * 有一种将字母编码成数字的方式：'a'->1, 'b->2', ... , 'z->26'。
 * 现在给一串数字，给出有多少种可能的译码结果。
 * */

/*
 * 样例输入：31717126241541717
 * 样例输出：192
 * */

// 解题方法：DP
// 思路：假如要求  x1x2x3x4...x8x9 可以有多少译码结果，假设已经知道  x1x2x3x4...x8（设为 m） 以及 x1x2x3x4...x7（设为 n） 有多少种译码结果
// 这时候再根据情况判断 x9 能不能和 x8 凑成一对分情况:
// 如果可以凑成一对，也可以不凑成一对，那么译码数量为 m + n
// 如果必须凑成一对，那么译码数量为 n
// 如果 x9 为 0 且 x8 不为 1 或 2，那么无法译码（总不能译码 00 30 40 这些），译码数量为 0

public class WebFrontEndEngineer_2_11 {
	public static void main(String[] args) throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		char arr[] = buf.readLine().toCharArray();
		int length = arr.length;
		int res[] = new int[length];
		buf.close();
		
		if (length == 1) {
			System.out.println(arr[0] == '0' ? 0 : 1);
			return;
		}
		res[0] = 1;
		res[1] = ((arr[0] < '2' || arr[0] == '2' && arr[1] <= '6') && arr[1] != '0') ?
				 2 :
				 1 ;
		for (int i = 2; i < length; i++) {
			if (arr[i] == '0') {
				res[i] = (arr[i - 1] == '1' || arr[i - 1] == '2') ?
						 res[i - 1] : // 如果是 10,20，则必须拼凑成一对
						 0; // 如果是 00 或 30, 40, 50, 60 ... 则置 0
			} else {
				res[i] = (arr[i - 1] == '1' || arr[i - 1] == '2' && arr[i] <= '6') ?
						 res[i - 2] + res[i - 1] : // 如果既可以凑成一对也可以单独一个数子
						 res[i - 1]; // 如果只能单独凑成一个数字
			}
		}
		System.out.println(res[length - 1]);
	}
}
