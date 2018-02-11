
package com.qinlong.algsTest1;

//º”»®quick-unionÀ„∑®
public class WeightedQuickUnionUF {
    private int[] parent;  
    private int[] size;     
    private int count;     

   
    public WeightedQuickUnionUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

   
    public int count() {
        return count;
    }
  
    
    public int find(int p) {
        while (p != parent[p])
            p = parent[p];
        return p;
    }

   
    
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }


}

