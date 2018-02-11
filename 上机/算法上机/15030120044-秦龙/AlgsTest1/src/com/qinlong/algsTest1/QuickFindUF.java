
package com.qinlong.algsTest1;

//quick-findËã·¨
public class QuickFindUF {
    private int[] id;    
    private int count;  

   
    public QuickFindUF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    
    public int count() {
        return count;
    }
  
  
    public int find(int p) {

        return id[p];
    }


    
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }
  
   
    public void union(int p, int q) {
        int pID = id[p];   
        int qID = id[q];   

        
        if (pID == qID) return;

        for (int i = 0; i < id.length; i++)
            if (id[i] == pID) id[i] = qID;
        count--;
    }


}


