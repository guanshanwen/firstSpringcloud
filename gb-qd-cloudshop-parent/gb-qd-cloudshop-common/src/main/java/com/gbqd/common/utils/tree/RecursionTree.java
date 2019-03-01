package com.gbqd.common.utils.tree;

import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class RecursionTree<T> {
    private Set<Node<T>> setnode;
    /**
     * 设置node集合
     * @param setnode
     */
    public Set<Node<T>>  getTreeNode(Set<Node<T>> setnode){
        this.setnode= setnode;
        return  constructSet(setnode);
    }
    public  Set<Node<T>> constructSet( Set<Node<T>> setnode){
        Set<Node<T>> newSetNode= new LinkedHashSet<>();
        Iterator<Node<T>> iterator = setnode.iterator();
        while (iterator.hasNext()){
            Node<T> nn = iterator.next();
            if(StringUtils.isBlank(nn.pid)){
                newSetNode.add(nn);
                constructSetDigui(nn);
            }
        }
        return newSetNode;
    }
    public void constructSetDigui(Node<T> pNode){
        Iterator<Node<T>> iterator = setnode.iterator();
        while (iterator.hasNext()){
            Node<T> nn= iterator.next();
            if(pNode.id.equals(nn.pid)){
                pNode.childSet.add(nn);
                constructSetDigui(nn);
            }
        }
    }
}
