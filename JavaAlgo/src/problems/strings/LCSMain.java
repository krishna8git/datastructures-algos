package problems.strings;

import java.util.Scanner;

public class LCSMain {

	private static Scanner scanner;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		String input1 = scanner.next();
		String input2 = scanner.next();
		System.out.println(LongestCommonSubSequenceString
				.longestCommonSubSequenceString(input1, input2));
		System.out.println(LongestCommonSubstringProblem
				.longestCommonSubString(input1, input2));
	}
}
