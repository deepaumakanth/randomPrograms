package test;


import java.util.Scanner;

/**
 * Booths algorithm for multiplying 8 bit binary numbers.
 * The program takes in two values and produces the product result in 16 bit digits.
 *
 */
public class BoothAlgorithm
{
	//get the values from the user.
   public static Scanner s = new Scanner(System.in);
   
   public static void main (String[] args) 
   {
       Scanner scan = new Scanner(System.in);
       System.out.println("Program to multiply 8 bit numbers using Booth Algorithm");
       System.out.println("-----------------------------------------------------------");

       BoothAlgorithm b = new BoothAlgorithm();

       
       System.out.println("Enter two integer numbers");
       int number1 = scan.nextInt();
       int number2 = scan.nextInt();
       int[] result = b.multiply(number1, number2);
       
       System.out.println("\nAnswer : "+ number1 +" * "+ number2 +" = "+number1*number2);
       b.display(result,'P');                    
   }

   public int[] multiply(int n1, int n2)
   {
       int[] m = binary(n1);//5= 0101 M-->multiplicand
       int[] m1 = binary(-n1);
       int[] r = binary(n2);     //-6=1010  Q-->multiplier       
       int[] A = new int[17]; //product register plus one extra bit to decide what action to be done. so since 8 bits its total of 16 bits +1 =17bit declared.
       int[] S = new int[17];
       int[] P = new int[17];        
       for (int i = 0; i < 8; i++)
       {
           A[i] = m[i];
           S[i] = m1[i];
           P[i + 8] = r[i];
       }
       display(A, 'A');
       display(S, 'S');
       display(P, 'P');        
       System.out.println();

       /**
        * 00,11 - do nothing
        * 01- add, 10-subtract [take 2s complement and add]
        */
       for (int i = 0; i < 8; i++)
       {
           if (P[15] == 0 && P[16] == 0);
           else if (P[15] == 1 && P[16] == 0)
               add(P, S);                            
           else if (P[15] == 0 && P[16] == 1)
               add(P, A);            
           else if (P[15] == 1 && P[16] == 1);

           rightShift(P);
           display(P, 'P');
       }
       return P;
   }
   /**  
    * Function to get decimal values.
    * @param B
    * @return
    */
   public int getDecimal(int[] B)
   {
       int p = 0;
       int t = 1;
       for (int i = 15; i >= 0; i--, t *= 2)
           p += (B[i] * t);
       if (p > 64)
           p = -(256 - p);
       return p;        
   }
/**
 * function to shift bits.
 * @param A
 */
public void rightShift(int[] A)
   {        

       for (int i = 16; i >= 1; i--)
           A[i] = A[i - 1];        
   }
/**
 * adding two binary numbers
 * @param A
 * @param B
 */
public void add(int[] A, int[] B)
   {
       int carry = 0;
       for (int i = 16; i >= 0; i--)
       {
           int temp = A[i] + B[i] + carry;
           A[i] = temp % 2;
           carry = temp / 2;
       }        
   }
   /**
    * Get Binary of the number sent in
    * @param n
    * @return
    */
   public int[] binary(int n)
   {
       int[] bin = new int[8];
       int ctr = 7;
       int num = n;
       /** for negative numbers 2 complement **/
       if (n < 0)
          num = 256 + n; //2^8
       while (num != 0)
       {
           bin[ctr--] = num % 2;
           num /= 2;
       }

       return bin;
   }
   /**
    * Convience print method
    * @param P
    * @param ch
    */
   public void display(int[] P, char ch)
   { 
       System.out.print("\n"+ ch +" : ");
       for (int i = 0; i < P.length; i++)
       {
           if (i == 8)
               System.out.print(" ");
           if (i == 16)
               System.out.print(" ");
           System.out.print(P[i]);
       } 
   }
   
}