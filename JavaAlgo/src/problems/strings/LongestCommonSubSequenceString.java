package problems.strings;

import utils.Utils;

public class LongestCommonSubSequenceString {

	public static int longestCommonSubSequenceString(String input1, String input2) {
		int len1 = input1.length();
		int len2 = input2.length();
		int arr[][] = new int[len1 + 1][len2 + 1];

		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (input1.charAt(i - 1) == input2.charAt(j - 1)) {
					arr[i][j] = arr[i][j-1] + 1;
				} else {
					arr[i][j] = Utils.max(arr[i - 1][j], arr[i][j - 1]);
				}
			}
		}

		return arr[len1][len2];
	}
}
