public class edge {

    private int node;
    private int weight;

    public edge(int v, int w) {
        this.node = v;
        this.weight = w;
    }

    public int get_node() {
        return this.node;
    }

    public int get_weight() {
        return this.weight;
    }

}
