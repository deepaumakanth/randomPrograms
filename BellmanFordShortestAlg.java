package test;

import java.util.Scanner;

/**
 * Program that determines the shortes path routing using the BellmanFord Algorithm
 * @author udeepa
 *
 */
public class BellmanFordShortestAlg{
	//The nodes used in the program, starting node is W.
    static char[] nodeNames = {'W','Y','Z','V','X','U'};

public static void main(String... arg) 
    {
        int inputMatrix[][]; //Holds the matrics and the distance given from one node to another.
        int countVertices; // Number of vertics 
        int [] d = new int[6]; // Array to hold the number of rows flows 
        int [] dd = new int[6]; 
        int pos =0; // position 
       for (int i = 0; i < 6 ; i++) // set all values to infinity 
        {
            d[i] = 10000; 
         }
        Scanner scan = new Scanner(System.in); // Input number of vertices and the matrix of the graph  
        
        {
            System.out.println("Enter the number of Vertices given...");
            countVertices = scan.nextInt();
            inputMatrix = new int[countVertices + 1][countVertices + 1]; // Define matrix boundaries   
            System.out.println("Uses 10000 for infinity for nodes not directly reachable..");

            System.out.println("Entering the matrix distances...");
            for (int i = 1; i <= countVertices; i++)
            {
                for (int j = 1; j <= countVertices; j++)
                {
                    inputMatrix[i][j] = scan.nextInt(); // read the matrix from user input 
                    if (i == j)
                    {
                        inputMatrix[i][j] = 0;
                    }
                } 
            } 
            System.out.println("Begining Vertics: "+nodeNames[0]); // Belman Ford Algorithm
            for (int i = 0; i < countVertices;i++){
            System.out.print(" "+nodeNames[i]+" ");} // print first table title 
            for (int i = 1; i<= countVertices; i++){
            d[i-1]=inputMatrix[1][i];
            }
            int y =1; // Printing out  the result of the algorithm calculation (Iteration)
            int r = 2;//to start from the second array since the first one is initialization [0,0,0,0,0,0]
            for (int j=1 ;j < countVertices; j++){
            int x = 0;
            System.out.println("\n"+"Showing iteration outputs "+j); 
            System.out.println("\n"+"Iteration "+j); 
            for (int i=1 ;i <= countVertices; i++){
            dd[x]=inputMatrix[r][i]+d[y];
            System.out.print(" "+d[x]);
            if (d[x] > dd[x]){ // value is checked and the array is set to if 
              d[x]=dd[x];}
            x++;
            }
            y++; //here we keep count for the number of iterations, since vertice =6 , iterations are 5 so we have 5 iterations.
            r++;
            }
            System.out.println("\n"+"Final Result ");
            for (int i=0;i< countVertices; i++){
            System.out.print(" "+d[i]);
            }
            int[] hop = new int [6];  // Calculating the number of hops
            for (int v = 1; v <=countVertices;v++){
            for (int i = 1; i<=countVertices;i++){
            	System.out.println("\n inputMatrix[i][v]:"+inputMatrix[i][v]);
            	System.out.println("d matrics:"+d.toString());
            	System.out.println("d[v-1]:"+d[v-1]);
              if (inputMatrix[i][v] < d[v-1] && inputMatrix[i][v] > 0){
                  hop[v-1]=hop[v-1]+1;
              }
            }
            }
            for (int v = 1; v <=countVertices;v++){  
            for (int i = 1; i<=countVertices;i++){
              if (inputMatrix[i][v] == d[v-1] && hop[v-1]==0){
                  hop[v-1]=hop[v-1]+1;
              }
            }
            }
            System.out.println("\n"); // Print second table Destination and number of hops to each one 
            System.out.println("Destination      Hop");
            if(hop[pos]==1)
              hop[pos]= 0;
            for (int i= 0; i< countVertices;i++)
              System.out.println(nodeNames[i]+"                 "+hop[i]);
        } 
        scan.close();
    }
}
