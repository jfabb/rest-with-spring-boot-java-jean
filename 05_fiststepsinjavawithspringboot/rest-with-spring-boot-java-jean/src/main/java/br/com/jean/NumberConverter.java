package br.com.jean;

public class NumberConverter {
	

	
	public static double convertToDouble(String str) {
		if(str == null)return 0D;
		String number = str.replaceAll(",", ".");
		if(isNumber(number))return Double.parseDouble(number);
		return 0D;
	}

	public static boolean isNumber(String str) {
		if(str == null)return false;
		String number = str.replaceAll(",", ".");
		
	  return  number.matches("-?\\d+(\\.\\d+)?");
	  
	}

}
