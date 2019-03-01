package com.gbqd.common.utils.tree;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.*;

/**
 * node树形类1
 */
public class Node<T> {
    public  String id;
    public  String pid;
    public  String name;
    public  Set<Node> childSet =new LinkedHashSet<>();
    public  T obj;
    public static void main(String[] args) {
        Set<Node> setnode= new LinkedHashSet<>();

        Node<Map<String,String>> n1= new Node<>();
        n1.id="1";
        Map map = new HashMap<String,String>();
        map.put("f","123");
        //n1.obj=map;
        Node n1_1= new Node();
        n1_1.pid="1";
        n1_1.id="1.1";
        Node n1_2= new Node();
        n1_2.pid="1";
        n1_2.id="1.2";
        Node n1_1_1= new Node();
        n1_1_1.pid="1.1";
        n1_1_1.id="1.1.1";


        Node n2= new Node();
        n2.id="2";
        Node n2_1= new Node();
        n2_1.pid="2";
        n2_1.id="2.1";
        Node n2_2= new Node();
        n2_2.pid="2";
        n2_2.id="2.2";

        Node n2_2_2= new Node();
        n2_2_2.pid="2.2";
        n2_2_2.id="2.2.1";


        setnode.add(n1);
        setnode.add(n1_1);
        setnode.add(n1_2);
        setnode.add(n1_1_1);
        setnode.add(n2);
        setnode.add(n2_1);
        setnode.add(n2_2);
        setnode.add(n2_2_2);


        RecursionTree rt= new RecursionTree();


        System.out.println(JSON.toJSONString( rt.getTreeNode(setnode), SerializerFeature.WriteMapNullValue));
    }
}
