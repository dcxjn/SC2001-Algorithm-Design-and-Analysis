import java.util.ArrayList;

public class ArrayPriorityQueue {

    private ArrayList<edge> queue = new ArrayList<edge>();

    public ArrayPriorityQueue() {

    }

    public boolean is_empty() {
        if (queue.size() == 0) {
            return true;
        } else
            return false;
    }

    public void insert(edge p) {
        queue.add(p);
        int weight = p.get_weight();
        for (int i = queue.size() - 2; i >= 0; i--) {
            if (weight < queue.get(i).get_weight()) {
                queue.set(i + 1, queue.get(i));
            } else {
                queue.set(i + 1, p);
            }
        }
    }

    public void dequeue() {
        queue.remove(0);
    }

    public edge get_priority() {
        return queue.get(0);
    }
}
