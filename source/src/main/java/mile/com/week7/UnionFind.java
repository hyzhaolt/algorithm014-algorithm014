package mile.com.week7;

/**
 * 实现一个并查集
 */
public class UnionFind {
    private int count = 0;
    private int[] parent;
    public UnionFind(int n){
        this.count = n;
        parent = new int[n];
        //初始时 每个节点 都是独立的节点 所以count=n 后面只要加入一个元素 则当前孤立点便减少1
        for(int i=0; i<n ; i++){
            parent[i] = i;
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int[] getParent() {
        return parent;
    }

    public void setParent(int[] parent) {
        this.parent = parent;
    }

    /**
     *
     * @param p
     * @return
     */
    public int find(int p){
        while(p != parent[p]){
            parent[p] = parent[parent[p]];
            p = parent[p];
        }

        return p;
    }

    /**
     * 将两个元素合并
     * @param p
     * @param q
     */
    public void union(int p,int q){
        int rootP = find(p);
        int rootQ = find(q);

        if(rootP == rootQ) return;

        parent[rootP] = rootQ;
        count --;
    }
}
