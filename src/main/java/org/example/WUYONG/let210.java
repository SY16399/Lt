package org.example.WUYONG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public  class let210 {
    static List<Node> nodes = new ArrayList<>();
    static HashMap<Integer,Node> nodeHashMap = new HashMap<>();
    static class Node{
        int val;
        List<edge> edges=new ArrayList<>();

        public Node(int val) {
            this.val = val;
        }

        private Node(int val, edge edge) {
            this.val = val;
            edges.add(edge);
        }

        public int getVal() {
            return val;
        }

        public List<edge> getEdges() {
            return edges;
        }
        /*static Node nodeFunction(int val,edge edge){
            if (nodeHashMap.containsKey(val)){
                Node node = nodeHashMap.get(val);
                node.edges.add(edge);
                return node;
            }else {
                return new Node(val,edge);
            }
        }*/
    }
    static class edge{
        Node from;
        Node to;

        public edge(Node from, Node to) {
            this.from = from;
            this.to = to;
        }
    }
/*    public int[] findOrder(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < numCourses; i++) {
            nodeHashMap.put(i,new Node(i));
        }
        for (int[] per:prerequisites){
            nodeHashMap.get(per[0]).getEdges().add(new edge(nodeHashMap.get(per[0]),nodeHashMap.get(per[1])));
        }
        return int[]{{1}}
    }*/
}
