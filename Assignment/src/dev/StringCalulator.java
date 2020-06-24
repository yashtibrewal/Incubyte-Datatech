package dev;

import java.util.ArrayList;

public class StringCalulator {
	ArrayList<Character> delimeters;
	public StringCalulator() {
		delimeters = new ArrayList(2);
		addDefaultDelimeters();
	}
	
	public void addDefaultDelimeters() {
		delimeters.add(',');
		delimeters.add('\n');
	}
	
	public int add(String input) {

		int sum = 0;
		
		
		if(input.length()==0) {
			return 0;
		}
		
		// handling delimiter change
		if(input.length()>=3)
			if(input.charAt(0)=='/' && input.charAt(1)=='/') {
				addDelimeter(input.charAt(2));
				input = input.substring(3);
			}
		
		
		String num = ""; //to hold 1 number at a time
		for(int i=0;i<input.length();i++) { // processing every character
			
			char ch = input.charAt(i);
			if(!isDelimeter(ch))  // checking for the delimeter
				num+=ch;
			//logic to feed one number
			if(isDelimeter(ch)) {
				sum+=Integer.parseInt(num);
				num="";
			}
			
			if(i==input.length()-1) { // to add the last number
				sum+=Integer.parseInt(num);
			}
			
		}
		
		return sum;
	}
	
	public boolean isDelimeter(char ch) {
		return delimeters.contains(ch);
	}
	
	public void addDelimeter(char ch) {
		delimeters.add(ch);
	}
	
}
