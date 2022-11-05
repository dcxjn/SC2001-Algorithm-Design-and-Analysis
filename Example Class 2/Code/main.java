// generate random graph
// implement Dijkstra's algo
// get runtime

public class main {
    public static void main(String args[]) {

        long startTime = System.nanoTime();
        graph G = new graph(300, 89700); // max e = v*(v-1)
        G.create_adjMatrix(); // adj matrix form
        G.create_adjList(); // adj list form
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.printf("No. of vertices: " + G.get_V() + ", No. of edges: " + G.get_E() + "\n");
        System.out.printf("Runtime of graph creation: " + totalTime * 0.000000001 + " s\n");

        // runtime for matrix with queue
        long startTime1 = System.nanoTime();
        G.dijkstra_array(0);
        long endTime1 = System.nanoTime();
        long totalTime1 = endTime1 - startTime1;
        System.out.printf("Runtime of (1): " + totalTime1 * 0.000000001 + " s\n");

        // runtime for list with heap
        long startTime2 = System.nanoTime();
        G.dijkstra_heap(0);
        long endTime2 = System.nanoTime();
        long totalTime2 = endTime2 - startTime2;
        System.out.printf("Runtime of (2): " + totalTime2 * 0.000000001 + " s\n");

    }

}