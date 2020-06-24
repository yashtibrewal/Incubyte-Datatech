package dev;

public class StringCalulator {
	char[] delimeters = {',','\n'};
	public StringCalulator() {
		// TODO Auto-generated constructor stub
	}
	public int add(String input) {
		System.out.print("debug point");
		if(input.length()==0) {
			return 0;
		}
		
		int sum = 0;
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
		
		for(char delimeter:delimeters) {
			if(ch==delimeter)
				return true;
		}
		
		return false;
	}
	
}
