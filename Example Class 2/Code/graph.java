import java.util.Random;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class graph {

    private int V;
    private int E;
    private int[][] matrix;
    private ArrayList<ArrayList<edge>> list;

    int INF = Integer.MAX_VALUE;

    public graph(int v, int e) {
        this.V = v;
        this.E = e;
        this.matrix = new int[v][v];
        this.list = new ArrayList<ArrayList<edge>>();
    }

    public void create_adjMatrix() {

        // initialise matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                matrix[i][j] = INF;
            }
        }
        for (int i = 0; i < V; i++) {
            matrix[i][i] = 0;
        }

        int edge_count = 0;

        for (int i = 1; i < V; i++) {
            int j = new Random().nextInt(i - 0) + 0; // random node from 0 to i-1
            matrix[j][i] = new Random().nextInt(101 - 1) + 1; // random weight from 1 to 100
            edge_count++;
        }

        // generate random edge between 2 random nodes
        while (edge_count != E) {
            int a = new Random().nextInt(V - 0) + 0; // random node from 0 to V-1
            int b = new Random().nextInt(V - 0) + 0;
            int weight = new Random().nextInt(101 - 1) + 1;
            if (matrix[a][b] == INF) {
                matrix[a][b] = weight;
                edge_count++;
            }
        }
    }

    public void create_adjList() {

        // initialise outer arraylist
        for (int i = 0; i < V; i++) {
            ArrayList<edge> node = new ArrayList<edge>();
            list.add(node);
        }

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (matrix[i][j] != 0 && matrix[i][j] != INF) {
                    list.get(i).add(new edge(j, matrix[i][j]));
                }
            }
        }
    }

    public void dijkstra_array(int source) {

        int[] S = new int[V];
        int[] d = new int[V];
        Integer[] pi = new Integer[V];

        ArrayPriorityQueue arrayQueue = new ArrayPriorityQueue();

        // initialisation
        for (int i = 0; i < V; i++) {
            S[i] = 0;
            d[i] = INF;
            pi[i] = null;
        }

        d[source] = 0;

        // put all nodes in priority queue
        for (int i = 0; i < V; i++) {
            arrayQueue.insert(new edge(i, d[i]));
        }

        while (!arrayQueue.is_empty()) { // while queue is not empty

            int u = arrayQueue.get_priority().get_node();
            arrayQueue.dequeue();
            S[u] = 1;

            for (int j = 0; j < V; j++) {
                if (matrix[u][j] != 0 && matrix[u][j] != INF) {
                    int v = j;
                    if (S[v] != 1 && d[v] > d[u] + matrix[u][v]) {
                        d[v] = d[u] + matrix[u][v]; // update d[v]
                        pi[v] = u; // predecessor of v is u
                        arrayQueue.insert(new edge(v, d[v]));
                    }
                }
            }
        }
    }

    public void dijkstra_heap(int source) {

        int[] S = new int[V];
        int[] d = new int[V];
        Integer[] pi = new Integer[V];

        PriorityQueue<edge> minHeap = new PriorityQueue<edge>(Comparator.comparingInt(edge::get_weight));

        // initialisation
        for (int i = 0; i < V; i++) {
            S[i] = 0;
            d[i] = INF;
            pi[i] = null;
        }

        d[source] = 0;

        // put all nodes in priority queue
        for (int i = 0; i < V; i++) {
            minHeap.add(new edge(i, d[i]));
        }

        while (minHeap.size() != 0) { // while queue is not empty

            int u = minHeap.poll().get_node();
            S[u] = 1;

            for (int i = 0; i < list.get(u).size(); i++) {
                int v = list.get(u).get(i).get_node();
                if (S[v] != 1 && d[v] > d[u] + list.get(u).get(i).get_weight()) {
                    d[v] = d[u] + list.get(u).get(i).get_weight(); // update d[v]
                    pi[v] = u; // predecessor of v is u
                    minHeap.add(new edge(v, d[v]));
                }
            }
        }

    }

    public int get_V() {
        return this.V;
    }

    public int get_E() {
        return this.E;
    }

    public int[][] get_matrix() {
        return this.matrix;
    }

    public ArrayList<ArrayList<edge>> get_list() {
        return this.list;
    }

}
