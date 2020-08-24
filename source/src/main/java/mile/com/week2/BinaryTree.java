package mile.com.week2;

import java.util.*;

/**
 * 二叉树相关的操作
 * 1.深度优先遍历DFS
 * (1)前序:非递归时 使用栈 访问出栈元素 压入右孩子节点  压入左孩子节点(下次遍历时 优先访问到)
 * (2)中序:非递归时 使用栈 当前元素为根元素 只要左孩子节点不为空 一直入栈 ;
 * 出栈当前节点:说明无左孩子节点 或左孩子节点已访问完;
 * 当前节点右孩子节点入栈
 * (3)后序
 * 2.广度优先遍历BFS
 * (1)层序:自上而下  先进先出  -->队列 根先入队 只要队列非空 则出队并访问该节点 同时将该节点的左右孩子依次入队
 * Created by zhaofengying on 2020/8/22.
 */
public class BinaryTree {
    /**
     * 递归地完成二叉树的前序遍历
     * @param root
     * @return
     */
    public List<Integer> prorderTraversalRec(TreeNode root){
        if(null == root){
            return new ArrayList<>();
        }

        List<Integer> results = new ArrayList<>();

        //先访问根
        results.add(root.val);

        //再访问左子树
        if(null != root.left){
            results.addAll(prorderTraversalRec(root.left));
        }

        //再访问右子树
        if(null != root.right){
            results.addAll(prorderTraversalRec(root.right));
        }

        return results;
    }

    /**
     * 递归地完成二叉树的前序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalRec(TreeNode root){
        if(null == root){
            return new ArrayList<>();
        }

        List<Integer> results = new ArrayList<>();

        //访问左子树
        if(null != root.left){
            results.addAll(inorderTraversalRec(root.left));
        }

        //访问根节点
        results.add(root.val);

        //访问右子树
        if(null != root.right){
            results.addAll(inorderTraversalRec(root.right));
        }

        return results;
    }


    /**
     * 二叉树中序遍历 左-->根-->右
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> results = new ArrayList<>();
        if(null == root){
            return results;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode currNode = root;

        //如果当前二叉树没有左子树 那么根节点出栈后 stack就是空的  但此currNode=右子树的根节点
        //所以还需要继续遍历右子树
        while(!stack.isEmpty() || null != currNode){
            //当前节点只要未出栈 则代表还未访问到 也说明其左子树还未访问完
            //沿着左儿子节点 一直走到最左
            while(null != currNode){
                stack.push(currNode);
                currNode = currNode.left;
            }

            //当前节点出栈 访问当前节点
            currNode = stack.pop();
            results.add(currNode.val);

            //开始遍历当前节点的右子树
            currNode = currNode.right;
        }

        return results;
    }

    /**
     * 二叉树前序遍历
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root){
        List<Integer> results = new ArrayList<>();
        if(null == root){
            return results;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode currNode = root;
        stack.push(currNode);

        while(!stack.isEmpty()){
            currNode = stack.pop();
            if(null == currNode){
                continue;
            }
            results.add(currNode.val);

            stack.push(currNode.right);
            stack.push(currNode.left);
        }

        return results;
    }

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

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode (int val){
            this.val = val;
        }
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
        nodeMap.put(0,root);

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

    public static void main(String[] args){
        BinaryTree tree = new BinaryTree();
        Integer[] nums = new Integer[]{0,1,2,3,4,5};
        TreeNode root = tree.buildABinaryTree(nums);

        System.out.println("层序遍历结果(非递归):" + tree.layeredTraversal(root));
        System.out.println("前序遍历结果(递归):" + tree.prorderTraversalRec(root));
        System.out.println("前序遍历结果(非递归):" + tree.prorderTraversalRec(root));
        System.out.println("中序遍历结果(递归):" + tree.inorderTraversal(root));
        System.out.println("中序遍历结果(非递归-栈):" + tree.inorderTraversal(root));
    }
}
