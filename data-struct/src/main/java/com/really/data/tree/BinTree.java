package com.really.data.tree;

public class BinTree<T> extends BinNode<T> {

    private int size; //觃模
    private BinNode root; //根节点

    public BinTree() {
        super();
    }

    public BinTree(T data) {
        this();
        this.data = data;
        this.root = this;
    }

    private BinTree(T data, BinNode parent){
        this(data);
        this.parent = parent;
    }

    //更新节点x的高度
    private int updateHeight(BinNode x) {

        x.height = 1 + Math.max(getHeight(x.lChild), getHeight(x.rChild));
        return x.height;
    }

    private int getHeight(BinNode node) {
        return node == null ? 0 : node.height;
    }

    //更新节点x及其祖先的高度
    private void updateHeightAbove(BinNode x){
        while (x != null) {
            updateHeight(x);
            x = x.parent;
        }
    }

    public int size() {
        return 0;
    }

    public Boolean empty () {
        return root == null;
    }

    public BinNode root() {
        return root;
    }

    public BinNode insertAsRoot(T t){
        return new BinTree(t);
    }
    public BinNode insertAsLC(BinNode x, T t){
        return x.lChild = new BinTree(t, x);
    }

    public BinNode insertAsLC(T t) {
        return this.lChild = new BinTree(t, this);
    }

    public BinNode insertAsRC(T t) {
        return this.rChild = new BinTree(t, this);
    }

    public BinNode succ() {
        return null;
    }

    public T[] travLevel() {
        return null;
    }

    public T[] travPre() {
        return null;
    }

    public T[] travPre1() {
        return null;
    }

    public T[] travPre2() {
        return null;
    }

    public T[] travIn() {
        return null;
    }

    public T[] travIn1() {
        return null;
    }

    public T[] travIn2() {
        return null;
    }

    public T[] travPost() {
        return null;
    }


}
