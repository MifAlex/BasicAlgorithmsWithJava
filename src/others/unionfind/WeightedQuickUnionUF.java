package others.unionfind;

public class WeightedQuickUnionUF {
    private int[] id;    // id[i] = parent of i
    private int[] sz;    // sz[i] = number of objects in subtree rooted at i
    private int count;   // number of components

    // Create an empty union find data structure with N isolated sets.
    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    // Return the number of disjoint sets.
    public int count() {
        return count;
    }

    // Return component identifier for component containing p
    public int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }

   // Are objects p and q in the same set?
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

  
   // Replace sets containing p and q with their union.
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;

        // make smaller root point to larger one
        if   (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else                 { id[j] = i; sz[i] += sz[j]; }
        count--;
    }


    public static void main(String[] args) {
    	int N = 10;
    	WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        
        for(int i=0; i<10; i++){
            int p = (int)(Math.random()*N);
            int q = (int)(Math.random()*N);
            if (uf.connected(p, q)){
            	System.out.println(p + " " + q + " has connected.");
            	continue;
            }
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println("# components: " + uf.count());
    }
}

