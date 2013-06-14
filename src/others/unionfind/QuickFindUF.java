package others.unionfind;

public class QuickFindUF {
    private int[] id;
    private int count;

    // instantiate N isolated components 0 through N-1
    public QuickFindUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }

    // return number of connected components
    public int count() {
        return count;
    }

    // Return component identifier for component containing p
    public int find(int p) {
        return id[p];
    }

    // are elements p and q in the same component?
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    // merge components containing p and q
    public void union(int p, int q) {
        if (connected(p, q)) return;
        int pid = id[p];
        for (int i = 0; i < id.length; i++)
            if (id[i] == pid) id[i] = id[q]; 
        count--;
    }

    public static void main(String[] args) {
    	int N = 10;
    	QuickFindUF uf = new QuickFindUF(N);
        
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
