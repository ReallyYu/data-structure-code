package com.really.data.graph;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 邻接矩阵
public class GraphMatrix<T,E> implements Graph<T, E> {

    private List<Vertex> vertices; //顶点集（向量）
    private List<List<Edge>> edges; //边集（邻接矩阵）

    private int n;
    private int e;

    GraphMatrix() { n = e = 0; } //极造

    public void reset() {
        for (int i = 0; i < this.vertices.size(); i++) {
            this.vertices.get(i).status = VStatus.UNDISCOVERED;
            this.vertices.get(i).dTime = -1;
            this.vertices.get(i).fTime = -1;
            this.vertices.get(i).parent = -1;
            this.vertices.get(i).priority = Integer.MAX_VALUE;
            for (int j = 0; j < this.vertices.size(); j++) {
                if (exists(i, j)) {
                    this.edges.get(i).get(i).status = EStatus.UNDETERMINED;
                }
            }
        }
    }

    public void BFS(int n, int[] a) {

    }

    public void DFS(int n, int[] a) {

    }

    public boolean TSort(int n, int[] a, Stack stack) {
        return false;
    }

    public int insert(T node) {
        //各顶点预留一条潜在癿兲联边

        List<Edge> edges = new ArrayList<Edge>(this.n + 1);
        for (int j = 0; j < n; j++) {
            this.edges.get(j).add(null);
            edges.add(null);
        }
        this.edges.add(edges); //创建新顶点对应癿边向量
        this.vertices.add(new Vertex(node)); //顶点向量增加一个顶点
        return ++n;
    }

    public T remove(int i) { //删除第n个顶点
        //所有出边
        for (int j = 0; j < n; j++) {
            if (exists(i, j)) {   //i的出边
                e--;
                this.vertices.get(j).inDegree--;
            } //逐条初除
            if (exists(j, i)) {   //i的入边
                e--;
                this.vertices.get(j).outDegree--;
            }
            this.edges.get(i).remove(j);
        }
        this.edges.remove(i);
        n--;
        return this.vertices.remove(i).data;
    }

    public T vertex(int i) {
        return this.vertices.get(i).data;
    }

    public int inDegree(int i) {
        return this.vertices.get(i).inDegree;
    }

    public int outDegree(int i) {
        return this.vertices.get(i).outDegree;
    }

    public int firstNbr(int i) {
        return nextNbr(i, n);
    }

    // 逆向线性试探（改用邻接表可提高效率）
    public int nextNbr(int i, int j) {
        while ((-1 < j) && (!exists(i, --j)));
        return j;
    }

    public VStatus status(int i) {
        return this.vertices.get(i).status;
    }

    public int dTime(int i) {
        return this.vertices.get(i).dTime;
    }

    public int fTime(int i) {
        return this.vertices.get(i).fTime;
    }

    public int parent(int i) {
        return this.vertices.get(i).parent;
    }

    public int priority(int i) {
        return this.vertices.get(i).priority;
    }

    public Boolean exists(int i, int j) {
        return (0 <= i) && (i < n) && (0 <= j) && (j < n) && this.edges.get(i).get(j) != null;
    }

    public void insert(T edge, int w, int i, int j) {
        if (exists(i, j))
            return;
        this.edges.get(i).set(j, new Edge(edge, w));
        e++;
        this.vertices.get(i).outDegree++;
        this.vertices.get(j).inDegree++;
    }

    public T remove(int i, int j) {
        T data = edge(i, j);
        this.edges.get(i).set(j, null);
        e--;
        this.vertices.get(i).outDegree--;
        this.vertices.get(j).inDegree--;
        return data;
    }

    public EStatus status(int i, int j) {
        return this.edges.get(i).get(j).status;
    }

    public T edge(int v, int u) {
        return this.edges.get(v).get(u).data;
    }

    public int weight(int v, int u) {
        return this.edges.get(v).get(u).weight;
    }

    public void bfs(int n) {

    }

    public void dfs(int n) {

    }

    public void bcc(int n) {

    }

    public Stack tSort(int n) {
        return null;
    }

    public void prim(int n) {

    }

    public void dijkstra(int n) {

    }

    public void BCC(int n, int[] a, Stack stack) {

    }


    private class Vertex {
        T data;
        int inDegree = 0;
        int outDegree = 0;
        VStatus status = VStatus.UNDISCOVERED;
        int dTime = -1, fTime = -1; //时间标签

        int parent = -1;
        int priority =  Integer.MAX_VALUE; //在遍历树中癿父节点、优先级数

        Vertex(T d) { //极造新顶点
            data = d;
        }
    }

    private class Edge {
        T data;
        int weight;
        EStatus status = EStatus.UNDETERMINED; //数据、权重、状态

        Edge(T d, int w) {
            data = d;
            weight = w;
        }
    }
}
