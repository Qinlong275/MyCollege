
package com.qinlong.mapRouting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


public class DijkstraUndirectedSP {
	
    private double[] distTo;        
    private Edge[] edgeTo;            
    private IndexMinPQ<Double> pq;    
    private EdgeWeightedGraph mGraph;
    private int from;//开始的原点
    
    
    public DijkstraUndirectedSP(EdgeWeightedGraph G) {
    	mGraph=G;
        for (Edge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("权值为负数");
        }
        distTo = new double[G.V()];
        edgeTo = new Edge[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        pq = new IndexMinPQ<Double>(G.V());
    }

    //设置算法的起点
	public void setFrom(int from) {
		//开始新的一次查询后把上一次修改过的部分还原初始化
		initDijkstra();
		this.from = from;
		distTo[from] = 0.0;
		pq.insert(from, distTo[from]);
	}



    private void relax(Edge e, int v) {
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);//改变为relax后的小值
            else                pq.insert(w, distTo[w]);//通过最新的优先级最小节点可以到达的新节点
        }
    }

    
    public double distTo(int v) {
        return distTo[v];
    }

   
    
    //在这里才执行相关的路径初始化
    public boolean hasPathTo(int v) {
        while (!pq.isEmpty()) {
            int  x= pq.delMin();
            //找到目标节点就直接退出循环（并初始化已经被修改的值），不必找出所有的结点
            if (x==v) {
				return true;
			}
            for (Edge e : mGraph.adj(x))
                relax(e, x);
        }
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    
    //还原上一次查询被修改的部分
    public void initDijkstra() {
		for (int i = 0; i < mGraph.V(); i++) {
			if (pq.contains(i)) {
				pq.delete(i);
			}
			if (edgeTo[i]!=null) {
				edgeTo[i]=null;
			}
			if (!Double.isInfinite(distTo(i))) {
				distTo[i]=Double.POSITIVE_INFINITY;
			}
		}
	}

    
    public Iterable<Edge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Edge> path = new Stack<Edge>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }
}

