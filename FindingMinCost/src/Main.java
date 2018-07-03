import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Math.min;

public class Main {

    /**
     * This task is solved by finding the minimum distance in the graph.
     * To find the minimum distance, I use the dijkstra algorithm
     */

    private static Map<String, Integer> cities; // Key- name of city, Value- number of vertex in graph
    private static int vertexNum; // amount of vertex
    private static int[][] graph; // adjacency matrix
    private static Scanner scannerInt;
    private static Scanner scannerStr;
    //The value is used to denote in the adjacency matrix that there is no edge between the vertices
    private static int INFINITY = Integer.MAX_VALUE / 2;

    public static void main(String[] args) {

        scannerInt = new Scanner(System.in);
        scannerStr = new Scanner(System.in);

        int numOfTests = scannerInt.nextInt();

        for (int t = 0; t < numOfTests; t++) {

            vertexNum = scannerInt.nextInt();
            graph = new int[vertexNum][vertexNum];
            cities = new HashMap<>();

            //read from the console information about the cities and the cost of the transfer between them,
            // and then fill the adjacency matrix
            fillGraph(vertexNum);

            int numberOfDirect = scannerInt.nextInt();

            //Read from the console direction for which we need to calculate the minimum cost of the transfer
            //and using the dijkstra algorithm to calculate it
            calcCostOfDirect(numberOfDirect);

            System.out.println(System.lineSeparator());
        }

    }

    private static int[] dijkstraAlgorithm(int start) {
        boolean[] usedVertex = new boolean[vertexNum]; // array of marked vertex
        int[] distances = new int[vertexNum]; // array of distances

        Arrays.fill(distances, INFINITY); // set the distance to all vertices INFINITY
        distances[start] = 0; //for the start vertex set 0

        for (; ; ) {
            int vertex = -1;
            for (int nVertex = 0; nVertex < vertexNum; nVertex++) { // sort out the vertices

                // select the closest untagged vertex
                if (!usedVertex[nVertex] && distances[nVertex] < INFINITY
                        && (vertex == -1 || distances[vertex] > distances[nVertex])) {
                    vertex = nVertex;
                }

            }

            if (vertex == -1) break; // nearest vertex not found
            usedVertex[vertex] = true; // marked it

            for (int nv = 0; nv < vertexNum; nv++) {
                if (!usedVertex[nv] && graph[vertex][nv] < INFINITY) {
                    // for all unmarked adjacent improve the estimation of distance
                    distances[nv] = min(distances[nv], distances[vertex] + graph[vertex][nv]);
                }
            }

        }

        return distances;
    }

    private static void fillGraph(int vertexNum) {

        //read from the console information about cities and their neighbors,
        // based on this information fill the adjacent matrix of the graph
        for (int i = 0; i < vertexNum; i++) {

            String cityName = scannerStr.nextLine();
            cities.put(cityName, i);

            int numOfNeighbors = scannerInt.nextInt();
            for (int j = 0; j < numOfNeighbors; j++) {
                String weight = scannerStr.nextLine();
                String[] cost = weight.split(" ");
                graph[i][Integer.parseInt(cost[0]) - 1] = Integer.parseInt(cost[1]);
            }

            //check all the values of matrix and all zeros, except those that are on diagonal, are replaced by infinity
            for (int j = 0; j < vertexNum; j++) {
                if (j == i) continue;

                if (graph[i][j] == 0) {
                    graph[i][j] = INFINITY;
                }

            }

        }
    }

    private static void calcCostOfDirect(int numberOfDirect) {

        //Read from console name of two cities, get from map their respective vertex numbers,
        // than the first city is passed as an argument to the method that implements the dijkstra algorithm,
        // after which we get an array with the minimum distance to all vertices of graph, output only necessary
        for (int i = 0; i < numberOfDirect; i++) {

            String direction = scannerStr.nextLine();
            String[] city = direction.split(" ");

            System.out.println(dijkstraAlgorithm(cities.get(city[0]))[cities.get(city[1])]);

        }

    }

}
