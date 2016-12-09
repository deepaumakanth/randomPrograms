package test;
 
public class Dijkstra
{
	
    static final int V=8;

	public static void main (String[] args)
    {
		//set up graph based on the mapping given.
//      int graph[][] = new int[][]{
//    		   {10000, 3, 1, 0, 0, 0, 0, 0},
//               {3, 0, 0, 2, 2, 0, 0, 0},
//               {1, 0, 0, 0, 2, 0, 0, 2},
//               {0, 0, 0, 2, 1, 0, 3, 0},
//               {0, 2, 2, 1, 2, 6, 0, 0},
//               {0, 0, 0, 0, 6, 6, 2, 4},
//               {0, 0, 0, 3, 6, 2, 0, 5},
//               {0, 0, 2, 0, 0, 4, 0, 5},
//              };
      //for infinite cose:
      int graph[][] = new int[][]{
    		  {10000, 8, 1, 10000, 10000, 10000, 10000, 10000},
              {3, 10000, 10000, 2, 2, 10000, 10000, 10000},
              {1, 10000, 10000, 10000, 2, 10000, 10000, 2},
              {10000, 10000, 10000, 2, 1, 10000, 3, 10000},
              {10000, 2, 2, 1, 2, 6, 10000, 10000},
              {10000, 10000, 10000, 10000, 6, 6, 2, 4},
              {10000, 10000, 10000, 3, 6, 2, 10000, 5},
              {10000, 10000, 2, 10000, 10000, 4, 10000, 5},
             };
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.findShortestPath(graph, 0);
    }
	
    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
	/**
	 * funtion the finds vertex with min distance value from vertices not used.
	 * @param dist
	 * @param sptSet
	 * @return
	 */
    private int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;
 
        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
 
        return min_index;
    }
 
    // A utility function to print the constructed distance array
    /**
     * Display the results
     * @param dist
     * @param n
     */
    private void printSolution(int dist[], int n)
    {
        System.out.println("Vertex   \t\tDistance from Source");
        for (int i = 0; i < V; i++)
            System.out.println("1 \t\t to \t\t"+i+" \t\t "+dist[i]);
    }
 
   /**
     * implementation of the Dijkstra's algorithm using the graph setup
     * @param graph
     * @param src
     */
   private void findShortestPath(int graph[][], int src)
    {
        int dist[] = new int[V]; // The output array. dist[i] will hold
                                 // the shortest distance from src to i
 
        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];
 
        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false; //making sure i is not included.
        }
 
        // Distance of source vertex from itself is always 0
        dist[src] = 0;
 
        // Find shortest path for all vertices
        for (int count = 0; count < V-1; count++)
        {
            // Choose the least distance vertex that is not processed
            int u = minDistance(dist, sptSet);
 
            // Mark the picked vertex to true to indicate it has been used.
            sptSet[u] = true;
 
            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++)
 
              
                if (!sptSet[v] && graph[u][v]!=0 &&
                        dist[u] != Integer.MAX_VALUE &&
                        dist[u]+graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
 
        printSolution(dist, V);
    }
 
}
