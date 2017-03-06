package problems.arrays;

import java.util.Scanner;

public class LongestCommonSubstringProblem {

	private static Scanner scanner;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		String input1 = scanner.next();
		String input2 = scanner.next();
		System.out.println(longestCommonSubString(input1, input2));
	}

	private static int longestCommonSubString(String input1, String input2) {
		int len1 = input1.length();
		int len2 = input2.length();
		int arr[][] = new int[len1 + 1][len2 + 1];

		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (input1.charAt(i - 1) == input2.charAt(j - 1)) {
					arr[i][j] = arr[i][j-1] + 1;
					System.out.print(arr[i][j] +" ");
				} else {
					arr[i][j] = max(arr[i - 1][j], arr[i][j - 1]);
					System.out.print(arr[i][j] + " ");
				}
			}
			System.out.println();
		}

		return arr[len1][len2];
	}

	private static int max(int i, int j) {
		return (i > j) ? i : j;
	}

}
