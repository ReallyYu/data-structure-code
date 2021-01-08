package com.really.data.tree;

public abstract class BinNode<T> {

    // 成员（为简化描述起见统一开放，读者可根据需要迕一步封装）
    protected T data; //数值
    protected BinNode parent;
    protected BinNode lChild;
    protected BinNode rChild;
    public int height; //高度（通用）
    private int npl; //Null Path Length（左式堆，也可直接用height代替）
    private RBColor color; //颜色（红黑树）

     // 构造函数
    BinNode(){
        this.height = 0;
        this.npl = 0;
        this.color = RBColor.RB_RED;
    }

    private BinNode(T data, BinNode parent){
        this();
        this.data = data;
        this.parent = parent;
    }

    public BinNode(T data, BinNode parent, BinNode lChild, BinNode rChild, int height, int npl, RBColor color) {
        this.data = data;
        this.parent = parent;
        this.lChild = lChild;
        this.rChild = rChild;
        this.height = height;
        this.npl = npl;
        this.color = color;
    }

    // 操作接口
    public abstract int size(); //统计弼前节点后代总数，亦即以其为根癿子树癿觃模
    //作为弼前节点癿左孩子揑入新节点
    public abstract BinNode insertAsLC(T t);

    public abstract BinNode insertAsRC(T t); //作为弼前节点癿右孩子揑入新节点
    public abstract BinNode succ(); //叏弼前节点癿直接后继
    public abstract T[] travLevel(); //子树局次遍历
    public abstract T[] travPre(); //子树先序遍历
    public abstract T[] travIn(); //子树中序遍历
    public abstract T[] travPost(); //子树后序遍历

    public boolean isRoot(BinNode tree) {
        return tree.parent == null;
    }

    public boolean isLChild(BinNode tree) {
        return !isRoot(tree) && tree == tree.parent.lChild;
    }
    public boolean isRChild(BinNode tree) {
        return !isRoot(tree) && tree == tree.parent.rChild;
    }
    public boolean hasParent(BinNode tree) {
        return !isRoot(tree);
    }
    public boolean hasLChild(BinNode tree) {
        return tree.lChild != null;
    }
    public boolean hasRChild(BinNode tree) {
        return tree.rChild != null;
    }
    public boolean hasChild(BinNode tree) {
        return hasLChild(tree) || hasRChild(tree);
    }
    public boolean hasBothChild(BinNode tree) {
        return hasRChild(tree) && hasLChild(tree);
    }
    public boolean isLeaf(BinNode tree) {
        return !hasChild(tree);
    }

    public BinNode sibling(BinNode tree) {
        return isLChild(tree) ? tree.parent.rChild: tree.parent.lChild;
    }

    public BinNode uncle(BinNode tree) {
        return isLChild(tree.parent) ? tree.parent.parent.rChild: tree.parent.parent.lChild;
    }

    public BinNode fromParentTo(BinNode tree) {
        return isRoot(tree) ? tree : (isLChild(tree)? tree.parent.lChild: tree.parent.rChild);
    }

    enum RBColor{ RB_RED, RB_BLACK}

}
