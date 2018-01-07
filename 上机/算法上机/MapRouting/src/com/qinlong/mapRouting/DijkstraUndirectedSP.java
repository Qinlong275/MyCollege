
package com.qinlong.mapRouting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


public class DijkstraUndirectedSP {
	
	
    private double[] distTo;        
    private Edge[] edgeTo;            
    private IndexMinPQ<Double> pq;    
    private EdgeWeightedGraph mGraph;
    private int from;//��ʼ��ԭ��
    
    
    public DijkstraUndirectedSP(EdgeWeightedGraph G) {
    	mGraph=G;
        for (Edge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        distTo = new double[G.V()];
        edgeTo = new Edge[G.V()];

        
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        

        pq = new IndexMinPQ<Double>(G.V());
       
      
    }

    //�����㷨�����
	public void setFrom(int from) {
		//��ʼ�µ�һ�β�ѯ�����һ���޸Ĺ��Ĳ��ֻ�ԭ��ʼ��
		initDijkstra();
		this.from = from;
		validateVertex(from);
		distTo[from] = 0.0;
		pq.insert(from, distTo[from]);
		assert check(mGraph, from);
	}



	// relax edge e and update pq if changed
    private void relax(Edge e, int v) {
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);//�ı�Ϊrelax���Сֵ
            else                pq.insert(w, distTo[w]);//ͨ�����µ����ȼ���С�ڵ���Ե�����½ڵ�
        }
    }

    
    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

   
    
    //�������ִ����ص�·����ʼ��
    public boolean hasPathTo(int v) {
        validateVertex(v);
        while (!pq.isEmpty()) {
            int  x= pq.delMin();
            //�ҵ�Ŀ��ڵ��ֱ���˳�ѭ��������ʼ���Ѿ����޸ĵ�ֵ���������ҳ����еĽ��
            if (x==v) {
				return true;
			}
            for (Edge e : mGraph.adj(x))
                relax(e, x);
        }
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    
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
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Edge> path = new Stack<Edge>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }

    private boolean check(EdgeWeightedGraph G, int s) {

      
        for (Edge e : G.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

   
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < G.V(); v++) {
            if (v == s) continue;
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        for (int v = 0; v < G.V(); v++) {
            for (Edge e : G.adj(v)) {
                int w = e.other(v);
                if (distTo[v] + e.weight() < distTo[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

    
        for (int w = 0; w < G.V(); w++) {
            if (edgeTo[w] == null) continue;
            Edge e = edgeTo[w];
            if (w != e.either() && w != e.other(e.either())) return false;
            int v = e.other(w);
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }

    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }


}

