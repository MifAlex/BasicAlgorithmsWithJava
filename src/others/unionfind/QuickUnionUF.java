package others.unionfind;

public class QuickUnionUF {
    private int[] id;    // id[i] = parent of i
    private int count;   // number of components

    // instantiate N isolated components 0 through N-1
    public QuickUnionUF(int N) {
        id = new int[N];
        count = N;
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    // return number of connected components
    public int count() {
        return count;
    }

    // return root of component corresponding to element p
    public int find(int p) {
        while (p != id[p])
            p = id[p];
        return p;
    }

    // are elements p and q in the same component?
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // merge components containing p and q
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        id[i] = j; 
        count--;
    }

    public static void main(String[] args) {
    	int N = 10;
    	QuickUnionUF uf = new QuickUnionUF(N);
        
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
