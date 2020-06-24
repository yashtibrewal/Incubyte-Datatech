package dev;

import java.util.ArrayList;

public class StringCalulator {
	ArrayList<Character> delimiters;
	ArrayList<Integer> negatives;
	static int calls = 0;
	
	public StringCalulator() {
		delimiters = new ArrayList<Character>(2);
		negatives = new ArrayList<Integer>(1);
		addDefaultDelimeters();
	}

	public int getCalledCount() {
		return calls;
	}
	
	public void addDefaultDelimeters() {
		delimiters.add(',');
		delimiters.add('\n');
	}

	public int add(String input) throws Exception {
		boolean hasNegatives = false;
		int sum = 0;
		calls++;
		if (input.length() == 0) {
			return 0;
		}

		// handling delimiter change
		if (input.length() >= 3)
			if (input.charAt(0) == '/' && input.charAt(1) == '/') {
				addDelimeter(input.charAt(2));
				input = input.substring(3); // removing the "//delimiter"
			}

		String num = ""; // to hold 1 number at a time
		for (int i = 0; i < input.length(); i++) { // processing every character

			char ch = input.charAt(i);
			// checking for the delimiter
			if (!isDelimeter(ch))
				num += ch;
			// logic to feed one number
			if (isDelimeter(ch)) {
				if (checkNegative(num, false)) {
					if (!hasNegatives)
						hasNegatives = true;
					checkNegative(num, true);
				}
				sum += Integer.parseInt(num);
				// handling negative numbers
				num = "";
			}

			if (i == input.length() - 1) { // to add the last number
				checkNegative(num, true);
				sum += Integer.parseInt(num);
			}

		}
		if (hasNegatives)
			throw new Exception("negatives not allowed " + negatives);
		return sum;
	}

	/**
	 * @param num: parameter to be checked if its a negative number and return
	 *             result
	 * @param add: parameter to control whether to add the number to the list of
	 *             integers
	 * @return boolean
	 */
	public boolean checkNegative(String num, boolean add) {
		if (Integer.parseInt(num) < 0) {
			if (add)
				negatives.add(Integer.parseInt(num));
			return true;
		}
		return false;
	}

	public boolean isDelimeter(char ch) {
		return delimiters.contains(ch);
	}

	public void addDelimeter(char ch) {
		delimiters.add(ch);
	}

}
