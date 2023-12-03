package org.example.WUYONG;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 按两次 Shift 打开“随处搜索”对话框并输入 `show whitespaces`，
// 然后按 Enter 键。现在，您可以在代码中看到空格字符。
public class Main {
  /*  public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 排序是剪枝的前提
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        // 由于进入更深层的时候，小于 0 的部分被剪枝，因此递归终止条件值只判断等于 0 的情况
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 重点理解这里剪枝，前提是候选数组已经有序，
            if (target - candidates[i] < 0) {
                break;
            }

            path.addLast(candidates[i]);
            dfs(candidates, i, len, target - candidates[i], path, res);
            path.removeLast();
        }
    }

    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        StringBuilder[] strings = new StringBuilder[numRows];//在Java中，使用new关键字创建字符串数组时，所有元素将被初始化为null//数组 'strings' 的内容已读取，但从未被写入
        for (int i = 0; i < numRows; i++) {
            strings[i] = new StringBuilder();
        }
        boolean falg = true;
        int n = s.length();
        int j = 0;
        for (int i = 0; i < n; i++) {
            char ji = s.charAt(i);
            strings[j].append(ji);//null使用方法将会空指针异常
            if (falg) {
                j++;
            } else {
                j--;
            }
            if (j == numRows - 1) {
                falg = false;
            }
            if (j == 0) {
                falg = true;
            }
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            str.append(strings[i]);
        }
        return String.valueOf(str);
    }

    public int myAtoi(String s) {
        s = s.trim();
        boolean flag = false;
        Pattern regex = Pattern.compile("-?\\d+");
        Matcher matcher = regex.matcher(s);
        String s1 = null;
        while (matcher.find()) {//要使用必须现查找
            s1 = matcher.group();
        }
        if (s1 != null && s1.charAt(0) == '-') {
            flag = true;
        }
        int a = 0;
        if (s1 != null) {
            a = Integer.parseInt(s1);
        }
        if (flag)
            return a;
        else
            return -a;

    }

    public static List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> alist = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        backgroud();
        return alist;

    }

    private static void backgroud() {
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private HashMap<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        Node clonde = new Node(node.val, new ArrayList<>());
        visited.put(node, clonde);
        for (Node node1 : node.neighbors) {
            clonde.neighbors.add(cloneGraph(node1));
        }
        return clonde;

    }*/

    static class bian implements Iterable<node> {
        node from;
        List<node> to = new ArrayList<>();

        private  bian(node from, node to) {
            this.from = from;
            this.to.add(to);
        }

        @Override
        public Iterator<node> iterator() {
            return null;
        }
    }

    static class node {
        int val;

        public node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "node{" +
                    "val=" + val +
                    '}';
        }
    }

    private static HashMap<Integer, node> hashMap = new HashMap<>();
    private static HashMap<node, List<node>> nodenodeHashMap = new HashMap<>();


    //2 [0,1],[1,0]
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        for (int i = 0; i < numCourses ; i++) {
            hashMap.put(i, new node(i));
        }
        List<bian> bians = new ArrayList<>();

        for (int[] prerequisite : prerequisites) {
            System.out.println(prerequisite[0]+" "+prerequisite[1]);
            System.out.println(hashMap.get(prerequisite[0])+","+ hashMap.get(prerequisite[1]));

            bians.add(new bian(hashMap.get(prerequisite[0]), hashMap.get(prerequisite[1])));
        }

        for (Main.bian bian : bians) {
            HashSet<node> ndoe2 = new HashSet<>();//{1,0},{1,2},{0,1}
            nodenodeHashMap.put(bian.from, bian.to);//0,1  1,0
                node node = bian.from; //1 int[][] aa = new int[][]{{1, 4}, {2, 4},{3,1},{3,2}};
                ndoe2.add(node);//0
                while (node != null) {
                   List<node> listd = nodenodeHashMap.get(node);
                    for (Main.node node1: listd){
                        node =node1;
                    if (ndoe2.contains(node)) {
                        return false;
                    }
                }
            }

        }

        return true;
    }
    /*public boolean canFinish(int numCourses, int[][] prerequisites) {
        int flag = 0;
        for (int i = 0; i < numCourses - 1; i++) {
            hashMap.put(i,new node(i));
        }

        for (int[] prerequisite : prerequisites) {
            nodenodeHashMap.put(hashMap.get(prerequisite[0]), hashMap.get(prerequisite[1]));
        }

        for (int i = 0; i < prerequisites.length; i++) {
            node node = nodenodeHashMap.get(hashMap.get(prerequisites[0][0]));
            ndoe2.add(node);
            while (node != null){
                node = nodenodeHashMap.get(node);
                if (ndoe2.contains(node)){
                    return false;
                }
            }
        }


        return !(flag==numCourses);
    }*/

    public static void main(String[] args) {
     /*   var kk = new Main();
        String jj = kk.convert("PAYPALISHIRING", 3);

        System.out.println( kk.myAtoi("   -123wiadjo"));*/
      /*  int[] nums = new int[]{1, 2, 3};
        System.out.println(permute(nums));*/
        //[1,4],[2,4],[3,1],[3,2]
        int number = 2;
        int[][] aa = new int[][]{{1, 4}, {2, 4},{3,1},{3,2}};
        int[][] bb = new int[][]{{1,0},{1,2},{0,1}};

        boolean flag = canFinish(3, bb);
        System.out.println(flag);
    }
}