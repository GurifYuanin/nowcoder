package shangtang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* *
 * ��һ�ֽ���ĸ��������ֵķ�ʽ��'a'->1, 'b->2', ... , 'z->26'��
 * ���ڸ�һ�����֣������ж����ֿ��ܵ���������
 * */

/*
 * �������룺31717126241541717
 * ���������192
 * */

// ���ⷽ����DP
// ˼·������Ҫ��  x1x2x3x4...x8x9 �����ж����������������Ѿ�֪��  x1x2x3x4...x8����Ϊ m�� �Լ� x1x2x3x4...x7����Ϊ n�� �ж�����������
// ��ʱ���ٸ�������ж� x9 �ܲ��ܺ� x8 �ճ�һ�Է����:
// ������Դճ�һ�ԣ�Ҳ���Բ��ճ�һ�ԣ���ô��������Ϊ m + n
// �������ճ�һ�ԣ���ô��������Ϊ n
// ��� x9 Ϊ 0 �� x8 ��Ϊ 1 �� 2����ô�޷����루�ܲ������� 00 30 40 ��Щ������������Ϊ 0

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
						 res[i - 1] : // ����� 10,20�������ƴ�ճ�һ��
						 0; // ����� 00 �� 30, 40, 50, 60 ... ���� 0
			} else {
				res[i] = (arr[i - 1] == '1' || arr[i - 1] == '2' && arr[i] <= '6') ?
						 res[i - 2] + res[i - 1] : // ����ȿ��Դճ�һ��Ҳ���Ե���һ������
						 res[i - 1]; // ���ֻ�ܵ����ճ�һ������
			}
		}
		System.out.println(res[length - 1]);
	}
}
