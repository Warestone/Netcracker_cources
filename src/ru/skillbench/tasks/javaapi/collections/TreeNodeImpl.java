package ru.skillbench.tasks.javaapi.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeNodeImpl implements TreeNode{
    private static TreeNode parent;
    private List<TreeNode> children = new ArrayList<>();
    private boolean expandedTree=false;
    private Object data = "";

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public void setParent(TreeNode p) {
        parent = p;
    }

    @Override
    public TreeNode getRoot() {
        if (parent==null)return null;
        TreeNode root = parent;
        boolean hasNext = true;
        while (hasNext)
        {
            hasNext = false;
            if (root.getParent()!=null){
                hasNext=true;
                root=root.getParent();
            }
        }
        return root;
    }

    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public Iterator<TreeNode> getChildrenIterator() {
        return children.iterator();
    }

    @Override
    public void addChild(TreeNode child) {
        child.setParent(this);
        children.add(child);
    }

    @Override
    public boolean removeChild(TreeNode child) {
        Iterator<TreeNode> iterator = children.iterator();
        while (iterator.hasNext())
        {
            if (iterator.next().equals(child))
            {
                iterator.remove();
                child.setParent(null);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isExpanded() { return expandedTree; }

    @Override
    public void setExpanded(boolean expanded) {
        expandedTree = expanded;
        for (TreeNode child:children)child.setExpanded(expanded);
    }

    @Override
    public Object getData() { return data; }

    @Override
    public void setData(Object d) {
        data=d;
    }

    @Override
    public String getTreePath() {
        if (parent==null)return getData().toString();
        StringBuilder path = new StringBuilder();
        if (getData()==null)path.insert(0,"empty");
        else path.insert(0,getData().toString());
        TreeNode root = parent;
        boolean hasNext = true;
        while (hasNext)
        {
            hasNext = false;
            if (root.getData()==null)path.insert(0, "empty->");
            else path.insert(0, root.getData().toString() + "->");
            if (root.getParent()!=null){
                hasNext=true;
                root=root.getParent();
            }
        }
        return path.toString();
    }

    @Override
    public TreeNode findParent(Object data) {
        if (parent==null)return null;
        TreeNode root = parent;
        boolean hasNext = true;
        while (hasNext)
        {
            if (data!=null && root.getData()!=null)
            {
                if (root.getData().equals(data))return root;
            }
            if (data == null && root.getData()==null)return root;
            hasNext = false;
            if (root.getParent()!=null){
                hasNext=true;
                root=root.getParent();
            }
        }
        return null;
    }

    @Override
    public TreeNode findChild(Object d) {
        for (TreeNode child:children)
        {
            if (d==null)if (child.getData()==null)return child;
            if (child.getData()!=null)if (child.getData().equals(d)) return child;
            return child.findChild(d);
        }
        return findChild(d);
    }
}
