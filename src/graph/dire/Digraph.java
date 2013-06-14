package graph.dire;

import datastructure.Bag;
import datastructure.Stack;

public class Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    
   /**
     * Create an empty digraph with V vertices.
     */
    public Digraph(int V) {
        if (V < 0) throw new RuntimeException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    
    public Digraph(int V, int E) {
        this(V);
        if (E < 0) throw new RuntimeException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = (int) (Math.random() * V);
            int w = (int) (Math.random() * V);
            while(v == w){
            	v = (int) (Math.random() * V);
                w = (int) (Math.random() * V);
            }
            addEdge(v, w);
        }
    }
    
    /**
     * Copy constructor.
     */
    public Digraph(Digraph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }
        
   /**
     * Return the number of vertices in the digraph.
     */
    public int V() {
        return V;
    }

   /**
     * Return the number of edges in the digraph.
     */
    public int E() {
        return E;
    }

   /**
     * Add the directed edge v->w to the digraph.
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

   /**
     * Return the list of vertices pointed to from vertex v as an Iterable.
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

   /**
     * Return the reverse of the digraph.
     */
    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                R.addEdge(w, v);
            }
        }
        return R;
    }

   /**
     * Return a string representation of the digraph.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

   /**
     * Test client.
     */
    public static void main(String[] args) {
    	Digraph G = new Digraph(4,3);
        System.out.println(G);
        
        System.out.println();
        for (int v = 0; v < G.V(); v++)
            for (int w : G.adj(v))
            	System.out.println(v + "->" + w);
    }

}
