package mile.com.week4;

import java.util.*;

/**
 * 层序遍历二叉树的相关变种
 * 实质:都是广度优先遍历的形式
 * Created by zhaofengying on 2020/9/1.
 */
public class LayeredBinaryTree {
    /**
     * 层序遍历二叉树 使用队列的先进先出特性
     * @param root
     * @return
     */
    public List<Integer> layeredTraversal(TreeNode root){
        List<Integer> results = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode currNode = queue.poll();
            if(null == currNode){
                continue;
            }

            results.add(currNode.val);
            queue.add(currNode.left);
            queue.add(currNode.right);
        }

        return results;
    }

    /**
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/#/description
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     */
    public List<List<Integer>> levelOrderTraversal(TreeNode node){
        List<List<Integer>> results = new ArrayList<>();
        List<TreeNode> currNodeList = new ArrayList<>();
        currNodeList.add(node);

        while(null != currNodeList && !currNodeList.isEmpty()){
            List<Integer> currLevelList = new ArrayList<>();

            List<TreeNode> nextLevelList = new ArrayList<>();
            for(TreeNode currNode : currNodeList){
                if(null == currNode){
                    continue;
                }
                currLevelList.add(currNode.val);

                nextLevelList.add(currNode.left);
                nextLevelList.add(currNode.right);
            }

            if(!currLevelList.isEmpty()){
                results.add(currLevelList);
            }
            currNodeList = nextLevelList;
        }

        return results;
    }

    /**
     * 在二叉树的每一行中找到最大的值。
     * 实质:按层从左到右返回每一层的节点的变种
     * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/#/description
     * @param root
     * @return
     */
    public List<Integer> largestValuesOfEachLevel(TreeNode root){
        List<Integer> results = new ArrayList<>();
        if(null == root){
            return results;
        }

        List<TreeNode> currNodeList = new ArrayList<>();
        currNodeList.add(root);

        while(null != currNodeList && !currNodeList.isEmpty()){
            int currMaxVal = Integer.MIN_VALUE;

            List<TreeNode> nextLevelList = new ArrayList<>();
            for(TreeNode currNode : currNodeList){
                currMaxVal = currNode.val > currMaxVal ? currNode.val : currMaxVal;
                if(null != currNode.left){
                    nextLevelList.add(currNode.left);
                }

                if(null != currNode.right){
                    nextLevelList.add(currNode.right);
                }
            }

            if(!currNodeList.isEmpty()){
                results.add(currMaxVal);
            }
            currNodeList = nextLevelList;
        }

        return results;
    }

    /**
     * 根据顶点信息建立一棵二叉树
     * 数组中父节点 与 左右儿子节点的下标关系如下:
     * 父节点下标:i
     * 左儿子节点:2 * i + 1
     * 右儿子节点:2 * i + 2
     * 约束:如果儿子节点为空,则输入一个空值null 但依旧会占用一个位置
     * @param nodes
     * @return
     */
    public TreeNode buildABinaryTree(Integer[] nodes){
        if(null == nodes || nodes.length <= 0){
            return null;
        }

        TreeNode root = new TreeNode(nodes[0]);
        Map<Integer,TreeNode> nodeMap = new HashMap<>();
        nodeMap.put(0, root);

        for(int i=0; 2 * i + 1 <nodes.length; i++){
            TreeNode parentNode = nodeMap.get(i);
            if(null == parentNode){
                continue;
            }
            //i一定有左儿子 因为 2*i + 1 < nodes.length
            TreeNode leftNode = null;
            if(null != nodes[2 * i + 1]){
                leftNode = new TreeNode(nodes[2 * i + 1]);
                parentNode.left = leftNode;
            }
            nodeMap.put(2 * i + 1,leftNode);


            //i不一定有右儿子 所以需要判断一下数据是否超限
            if(2 * i + 2 < nodes.length){

                TreeNode rightNode = null;
                if(null != nodes[2 * i + 2]){
                    rightNode = new TreeNode(nodes[2 * i + 2]);
                    parentNode.right = rightNode;
                }

                nodeMap.put(2 * i + 2,rightNode);
            }
        }

        return root;
    }

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int val){
            this.val = val;
        }
    }

    public static void main(String[] args){
        LayeredBinaryTree tree = new LayeredBinaryTree();
        Integer[] nums = new Integer[]{0,1,2,3,4,5};
        TreeNode root = tree.buildABinaryTree(nums);

        System.out.println("层序遍历结果(非递归):" + tree.layeredTraversal(root));
        System.out.println("层序遍历按层返回从左至右所有的节点:" + tree.levelOrderTraversal(root));
        System.out.println("层序返回每层中最大节点的值:" + tree.largestValuesOfEachLevel(root));
    }
}
