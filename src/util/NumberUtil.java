package util;

import java.util.Random;

public class NumberUtil {

	public static int randInt(int min, int max) {
		Random rn = new Random();
		int range = max - min + 1;
		int randomNum =  rn.nextInt(range) + min;
	    return randomNum;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
