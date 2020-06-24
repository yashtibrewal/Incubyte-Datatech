package dev;

public class StringCalulator {
	
	public int add(String input) {
		
		if(input.length()==0) {
			return 0;
		}
		
		int sum = 0;
		String num = ""; //to hold 1 number at a time
		for(int i=0;i<input.length();i++) { // processing every character
			
			char ch = input.charAt(i);
			if(ch != ',')
				num+=ch;
			//logic to feed one number
			if(Character.compare(ch,',')==0) {
				sum+=Integer.parseInt(num);
				num="";
			}
			
			if(i==input.length()-1) { // to add the last number
				sum+=Integer.parseInt(num);
			}
			
		}
		
		return sum;
	}
	
}
