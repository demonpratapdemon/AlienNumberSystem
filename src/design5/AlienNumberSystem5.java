/**
 * 
 */
package design5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @author PRATAP
 *
 */
public class AlienNumberSystem5 {

	/**
	 * @param args
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
	 * Input : base system and the current number. Output : successor number of the
	 * current number.
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
		/*
		 * Creating a hashmap with the number system to break it down to further sub
		 * problem for O(n) time complexity.
		 * 
		 */
		HashMap<String, Integer> numberSystemMap = new HashMap<String, Integer>();
		for (int i = 0; i < numberSystem.length; i++) {
			numberSystemMap.put(numberSystem[i], i);
		}
		try {
			for (int i = inputNumber.length() - 1; i >= 0; i--) {
				char currDigit = inputNumber.charAt(i);
				/*
				 * Error Handling. Check if the digit is present in the Number System. If not,
				 * raise the exception.
				 */
				if (!numberSystemMap.containsKey(String.valueOf(currDigit)))
					throw new Exception("Please Enter a correct Number according to the Alien Number System provided.");
				NextDigitAndCarry next = findNextDigitAnDCarry(currDigit, carry, numberSystem, numberSystemMap);
				succNumber.append(numberSystem[next.nextDigit]);
				carry = next.carry;
			}
			if (carry > 0) {
				succNumber.append(numberSystem[carry]);
			}
			return succNumber.reverse().toString();
		} catch (Exception e) {
			return "Exception occured : " + e.getMessage();
		}
	}

	/*
	 * This helper function takes in the current digit, carry if any and the number
	 * system and produces the next digit and carry and returns it in a new class
	 * object.
	 */
	public static NextDigitAndCarry findNextDigitAnDCarry(char currDigit, int carry, String[] numberSystem,
			HashMap<String, Integer> numberSystemMap) {
		int base = numberSystem.length;
		int number = numberSystemMap.get(String.valueOf(currDigit));
		number += carry;
		if (number < base)
			carry = 0;
		int newDigit = number % base;
		carry = number / base;
		return new NextDigitAndCarry(newDigit, carry);
	}
}

/*
 * Object oriented way returning the next digit and the carry when the logic is
 * moved to a new method in order to breakdown the problem to smaller
 * subproblems.
 */
class NextDigitAndCarry {
	public int nextDigit;
	public int carry;

	public NextDigitAndCarry(int nextDigit, int carry) {
		super();
		this.nextDigit = nextDigit;
		this.carry = carry;
	}

}