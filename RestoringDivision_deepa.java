package test;

import java.util.Scanner;

/**
 * Restoring 8bit division program. Considers absolute value of the dividend and
 * divisor and then finds the sign based on: sign(R) = sign(D) sign(Q) = sign(D)
 * sign(V) where D = dividend, V = divisor, R = Remainder,Q - Quotient;
 * 
 * @author udeepa
 *
 */
public class RestoringDivision_deepa {

	public static void main(String args[]) {
		Scanner src = new Scanner(System.in);
		//initialize for inputs and number of bits
		int Q, Z, M, c1, i, y, ch, in, S, G, P;
		int[] a = new int[16];
		int[] b = new int[16];
		int[] b1 = new int[16];
		int[] q = new int[16];
		int dividend, divisor;
		int carry = 0, count = 0;
		long num;
		boolean negativeDividend = false;
		boolean negativeDivisor = false;
		boolean negativeNo = false;

		

			System.out.print("Restoring Division Program using the java language:\n");
			System.out.print("\nENTER DIVIDEND\t: ");
			Q = src.nextInt();
			dividend = Q;

			// checking to see if the number is negative for deciding sign in
			// the end.
			if (Q <= 0) {
				negativeNo = true;
				System.out.println("sign of dividen is negative:");
				negativeDividend = true;
				Q = Q * -1;
			}
			y = 7;
			System.out.print("ENTER DIVISOR\t: ");
			M = src.nextInt();
			divisor = M;

			if (M <= 0) {
				negativeNo = true;
				System.out.println("sign of divisor is negative:");
				negativeDivisor = true;
				M = M * -1;
			}
			Z = 7;

			for (i = Z; i >= 0; i--) {
				b1[i] = b[i] = M % 2;
				M = M / 2;
				b1[i] = 1 - b1[i];
			}
			carry = 1;
			for (i = Z; i >= 0; i--) {
				c1 = b1[i] ^ carry;
				carry = b1[i] & carry;
				b1[i] = c1;
			}
			for (i = 2 * Z; i > Z; i--) {
				a[i] = Q % 2;
				Q = Q / 2;
			}
			System.out.print("\n\n\tINITIALLY\t\t (A)\t:");
			for (i = 0; i <= 7; i++)
				System.out.print(a[i] = 0);
			System.out.print("\n\n\tDivisor\t\t(M)\t: ");
			for (i = 0; i <= Z; i++)
				System.out.print(b[i] + " ");
			System.out.print("\n\t2'C Divisor\t(M)\t: ");
			for (i = 0; i <= Z; i++)
				System.out.print(b1[i] + " ");
			System.out.print("\n\tDividend\t(Q)\t: ");
			for (i = Z + 1; i <= 2 * Z; i++)
				System.out.print(a[i] + " ");
			System.out.print("\n\nBITS CONSIDERED:[ A ] \t[ M ]");
			System.out.print("\n\t\t\t\t");
			for (i = 0; i <= Z; i++)
				System.out.print(a[i] + " ");
			System.out.print(" ");
			for (i = Z + 1; i <= 2 * Z; i++)
				System.out.print(a[i] + " ");
			count = Z;
			do {
				for (i = 0; i < 2 * Z; i++)
					a[i] = a[i + 1];
				System.out.print("\n\nLeft Shift\t\t");
				for (i = 0; i <= Z; i++)
					System.out.print(a[i] + " ");
				System.out.print(" ");
				for (i = Z + 1; i < 2 * Z; i++)
					System.out.print(a[i] + " ");
				carry = 0;
				for (i = Z; i >= 0; i--) {

					S = a[i] ^ (b1[i] ^ carry);
					G = a[i] & b1[i];
					P = a[i] ^ b1[i];
					carry = G | (P & carry);
					a[i] = S;
				}
				System.out.print("\nA< A-M \t\t");
				for (i = 0; i <= Z; i++)
					System.out.print(a[i] + " ");
				System.out.print(" ");
				for (i = Z + 1; i < 2 * Z; i++)
					System.out.print(a[i] + " ");
				ch = a[0];
				System.out.print("\nBIT Q:" + ch);
				switch (ch) {
				case 0:
					a[2 * Z] = 1;
					System.out.print(" Q0< -1\t");
					for (i = 0; i <= Z; i++)
						System.out.print(a[i] + " ");
					System.out.print(" ");
					for (i = Z + 1; i <= 2 * Z; i++)
						System.out.print(a[i] + " ");
					break;

				case 1:
					a[2 * Z] = 0;
					System.out.print(" Q0< 0\t");
					for (i = 0; i <= Z; i++)
						System.out.print(a[i] + " ");
					System.out.print(" ");
					for (i = Z + 1; i < 2 * Z; i++)
						System.out.print(a[i] + " ");
					carry = 0;
					for (i = Z; i >= 0; i--) {
						S = a[i] ^ (b[i] ^ carry);
						G = a[i] & b[i];
						P = a[i] ^ b[i];
						carry = G | (P & carry);
						a[i] = S;
					}
					System.out.print("\nA< -A+M");
					System.out.print("\t\t\t");
					for (i = 0; i <= Z; i++)
						System.out.print(a[i] + " ");
					System.out.print(" ");
					for (i = Z + 1; i <= 2 * Z; i++)
						System.out.print(a[i] + " ");
					break;
				}
				count--;
			} while (count != 0);
			num = 0;
			int quotient = 0;
			for (i = Z + 1; i <= 2 * Z; i++) {
				num = num + (int) Math.pow(2, 2 * Z - i) * a[i];
				quotient = (int) num;
			}

			System.out.println("\n\t\t");
			num = 0;
			int remainder = 0;

			for (i = 0; i <= Z; i++) {
				num = num + (int) Math.pow(2, Z - i) * a[i];
				remainder = (int) num;
			}

			System.out.println("Final value of A which is R :");
			for (i = 0; i <= Z; i++) {
				System.out.print(a[i] + " ");
			}

			System.out.println("\n Final value of M which is Q :");
			for (i = Z + 1; i <= 2 * Z; i++) {
				System.out.print(a[i] + " ");
			}
			System.out.print("\n DIVIDEND\t: " + dividend);
			System.out.print("\n DIVISOR\t: " + divisor);

			/**
			 * This is to add the sign to the end results based on : sign(R) =
			 * sign(D) sign(Q) = sign(D) *sign(V) where D = dividend, V =
			 * divisor, R = Remainder,Q - Quotient;
			 * 
			 */
			if (negativeNo) {
				int sofD = (negativeDividend) ? -1 : 1;
				int sofV = (negativeDivisor) ? -1 : 1;

				quotient = quotient * sofD * sofV;
				remainder = remainder * sofD;

			}
			System.out.print("\n Answer\t :" + quotient + "R" + remainder);



	}

}