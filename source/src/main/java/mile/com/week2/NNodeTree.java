package mile.com.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * N叉树所有的操作
 * 1.广度优先遍历
 * 层序遍历
 * 2.深度优先遍历
 * (1)前序
 * (2)后序
 * Created by zhaofengying on 2020/8/23.
 */
public class NNodeTree {


    /**
     * 先遍历
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        if(null == root){
            return new ArrayList<>();
        }

        List<Integer> results = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node currNode = stack.pop();

            results.add(currNode.val);
            List<Node> children = currNode.children;
            if(null == children || children.isEmpty()){
                continue;
            }

            for(int i=children.size() - 1; i>=0; i--){
                stack.push(children.get(i));
            }
        }

        return results;
    }

    /**
     * 后续遍历
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        if(null == root){
            return new ArrayList<>();
        }

        List<Integer> results = new ArrayList<>();

        return results;
    }

    public static void main(String[] args){
        Node oneLayer1 = new Node(2,null);

        Node twoLayer1 = new Node(5,null);
        Node twoLayer2 = new Node(6,null);
        List<Node> children = new ArrayList<>();
        children.add(twoLayer1);
        children.add(twoLayer2);
        Node oneLayer2 = new Node(3,children);

        Node oneLayer3 = new Node(4,null);

        List<Node> oneLayerChildren = new ArrayList<>();
        oneLayerChildren.add(oneLayer1);
        oneLayerChildren.add(oneLayer2);
        oneLayerChildren.add(oneLayer3);

        Node root = new Node(1,oneLayerChildren);

        NNodeTree nNodeTree = new NNodeTree();
            System.out.println(nNodeTree.preorder(root));
    }

    static class Node {
        int val;
        List<Node> children;
        public Node(int val,List<Node> children){
            this.val = val;
            this.children = children;
        }
    }
}
