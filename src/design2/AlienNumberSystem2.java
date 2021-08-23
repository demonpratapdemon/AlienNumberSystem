/**
 * 
 */
package design2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author PRATAP
 *
 */
public class AlienNumberSystem2 {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the alien number system (space separated): ");
		String[] numberSystem = br.readLine().split("\\s+");
		System.out.println("Enter the number in the above alien number system : ");
		String inputNumber = br.readLine();
		String nextNo = findSucc(numberSystem, inputNumber);
		System.out.println("The next number in the alien number system is : " + nextNo);
	}

	/*
	 * Input : base system and the current number.
	 *  Output : successor number of the current number.
	 */
	private static String findSucc(String[] numberSystem, String inputNumber) {
		// TODO Auto-generated method stub
		/*
		 * Proper Naming Convention of all the variables and methods for understanding
		 * easily which variable resembles what. For getting the successor, we need to
		 * add 1 to the current number and calculate the successor number. So, we need
		 * to maintain a carry variable which will keep track of the carries. It is
		 * initialised with 1 because we are also adding 1 to the current number to get
		 * the next number and hence we can avoid an extra variable.
		 */
		int carry = 1;
		StringBuilder succNumber = new StringBuilder();
		int base = numberSystem.length;
		for (int i = inputNumber.length() - 1; i >= 0; i--) {
			char currDigit = inputNumber.charAt(i);
			int number = -1;
			for (int j = 0; j < numberSystem.length; j++) {
				if (numberSystem[j].equals(String.valueOf(currDigit))) {
					number = j;
					break;
				}
			}
			number += carry;
			if (number < base)
				carry = 0;
			int newDigit = number % base;
			carry = number / base;
			succNumber.append(numberSystem[newDigit]);
		}
		if (carry > 0) {
			succNumber.append(numberSystem[carry]);
		}
		return succNumber.reverse().toString();
	}
}
