/**
 * 
 */
package design1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author PRATAP
 *
 */
public class AlienNumberSystem1 {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the alien number system (space separated): ");
		String[] input = br.readLine().split("\\s+");
		System.out.println("Enter the number in the above alien number system : ");
		String no = br.readLine();
		String nextNo = solve(input, no);
		System.out.println("The next number in the alien number system is : " + nextNo);
	}

	private static String solve(String[] input, String no) {
		// TODO Auto-generated method stub
		int carry = 1;
		StringBuilder sb = new StringBuilder();
		int n = input.length;
		for (int i = no.length() - 1; i >= 0; i--) {
			char ch1 = no.charAt(i);
			int num1 = -1;
			for (int j = 0; j < input.length; j++) {
				if (input[j].equals(String.valueOf(ch1))) {
					num1 = j;
					break;
				}
			}
			num1 += carry;
			if (num1 < input.length)
				carry = 0;
			int newNo = num1 % n;
			carry = num1 / n;
			sb.append(input[newNo]);
		}
		if (carry > 0) {
			sb.append(input[carry]);
		}
		return sb.reverse().toString();
	}

}
