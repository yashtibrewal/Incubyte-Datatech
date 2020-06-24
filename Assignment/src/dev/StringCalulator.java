package dev;

import java.util.ArrayList;

public class StringCalulator {
	ArrayList<String> delimiters;
	ArrayList<Integer> negatives;
	boolean hasNegatives = false;
	static int calls = 0;
	char specialCharacters[] = {'\\', '^', '$', '.', '|', '?', '*', '+', '(', ')', '[', '{'};
	
	public StringCalulator() {
		delimiters = new ArrayList<String>(2);
		negatives = new ArrayList<Integer>(1);
		addDefaultDelimeters();
	}

	public int getCalledCount() {
		return calls;
	}
	
	public void addDefaultDelimeters() {
		delimiters.add(",");
		delimiters.add("\n");
	}
	
	public boolean canBeAdded(String num) {
		// numbers above 1000 cannot be added
		if(Integer.parseInt(num)>1000)
			return false;
		if(checkNegative(num, true)) {
			return false;
		}
		return true;
	}
	
	public void processDelimiterForMetaCharacters() {
		for(int i=0;i<delimiters.size();i++) {
			String delimiter = delimiters.get(i);
			String processedDelimiter = "";
			for(int j=0;j<delimiter.length();j++) {
				char ch = delimiter.charAt(j);
				if(isSpeacialCharacter(ch)) {
					processedDelimiter+="\\"+ch;
				}else {
					processedDelimiter+=ch;
				}
			}
			delimiters.set(i, processedDelimiter);
		}
	}
	
	boolean isSpeacialCharacter(char ch) {
		for(char specialCharacter: specialCharacters) {
			if(ch==specialCharacter)
				return true;
		}
		return false;
	}
	
	public String handleDelimiterChange(String input) {
		
		// handling delimiter change
		
		if (input.length() >= 3)
			if (input.charAt(0) == '/' && input.charAt(1) == '/') {
				if(input.charAt(2) == '[') {
					int end = input.lastIndexOf(']');
					String multiCharacterDelimiter = input.substring(3,end);
					addDelimeter(multiCharacterDelimiter);
					input = input.substring(end+1);
				}else {
					addDelimeter(Character.toString(input.charAt(2)));
					input = input.substring(3); // cleaning
				}
			}
		
		
		return input;
	}
	
	public String makeDelimiterRegex() {
		processDelimiterForMetaCharacters();
		String delimiterRegex = "";
		boolean first = true;
		for(String delimiter: delimiters) {
			if(first) {
				delimiterRegex=delimiter;
				first = false;
			}
			else
				delimiterRegex+="|"+delimiter;
		}
		
		return delimiterRegex;
	}
	
	public int add(String input) throws Exception {
		int sum = 0;
		calls++;
		if (input.length() == 0) {
			return 0;
		}
		
		input = handleDelimiterChange(input);
		
		String delimiterRegex = makeDelimiterRegex();
		String numbers[] = input.split(delimiterRegex);
		for(String number:numbers) {
			if(canBeAdded(number))
				sum+=Integer.parseInt(number);
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
			if(!hasNegatives)
				hasNegatives = true;
			if (add)
				negatives.add(Integer.parseInt(num));
			return true;
		}
		return false;
	}

	public boolean isDelimeter(String delimiter) {
		return delimiters.contains(delimiter);
	}

	public void addDelimeter(String delimiter) {
		delimiters.add(delimiter);
	}

}
