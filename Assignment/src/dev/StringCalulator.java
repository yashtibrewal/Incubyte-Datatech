package dev;

import java.util.ArrayList;

public class StringCalulator {
	ArrayList<Character> delimiters;
	public StringCalulator() {
		delimiters = new ArrayList<Character>(2);
		addDefaultDelimeters();
	}
	
	public void addDefaultDelimeters() {
		delimiters.add(',');
		delimiters.add('\n');
	}
	
	public int add(String input) throws Exception {

		int sum = 0;
		
		
		if(input.length()==0) {
			return 0;
		}
		
		// handling delimiter change
		if(input.length()>=3)
			if(input.charAt(0)=='/' && input.charAt(1)=='/') {
				addDelimeter(input.charAt(2));
				input = input.substring(3);  // removing the "//delimiter"
			}
		
		
		String num = ""; //to hold 1 number at a time
		for(int i=0;i<input.length();i++) { // processing every character
			
			char ch = input.charAt(i);
			// checking for the delimiter
			if(!isDelimeter(ch))  
				num+=ch;
			//logic to feed one number
			if(isDelimeter(ch)) {
				if(Integer.parseInt(num)<0)
					throw new Exception("negatives not allowed "+num);
				sum+=Integer.parseInt(num);
				// handling negative numbers
				num="";
			}
			
			if(i==input.length()-1) { // to add the last number
				sum+=Integer.parseInt(num);
			}
			
		}
		
		return sum;
	}
	
	public boolean isDelimeter(char ch) {
		return delimiters.contains(ch);
	}
	
	public void addDelimeter(char ch) {
		delimiters.add(ch);
	}
	
}
