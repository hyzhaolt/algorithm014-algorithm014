package mile.com.week2;

import java.util.*;

/**
 * N叉树所有的操作
 * 1.广度优先遍历
 * 层序遍历:队列
 * 2.深度优先遍历
 * (1)前序:栈
 * (2)后序:栈
 * 3.递归方式:@todo
 * Created by zhaofengying on 2020/8/23.
 */
public class NNodeTree {

    /**
     * 递归的dfs
     * @param root
     * @return
     */
    public List<Integer> dfs(Node root){
        List<Integer> results = new ArrayList<>();

        if(null == root){
            return results;
        }

        results.add(root.val);

        List<Node> children = root.children;
        if(null != children && !children.isEmpty()){
            for(Node child : children){
                results.addAll(dfs(child));
            }
        }

        return results;
    }
    /**
     * 深度优先:先序遍历
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

            //压入栈的顺序为 最右边的孩子节点先入栈 保证在访问的时候 后访问
            for(int i=children.size() - 1; i>=0; i--){
                stack.push(children.get(i));
            }
        }

        return results;
    }

    /**
     * 深度优先:后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        if(null == root){
            return new ArrayList<>();
        }

        List<Integer> results = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        LinkedList<Integer> queue = new LinkedList<>();
        while(!stack.isEmpty()){
            Node currNode = stack.pop();
            //队尾插入
            queue.addFirst(currNode.val);
            if(null == currNode.children){
                continue;
            }
            for(Node child : currNode.children){
                stack.push(child);
            }
        }

        //队头先出  正好完成遍历结果的反转
        while(!queue.isEmpty()){
            results.add(queue.pollFirst());
        }

        return results;
    }

    /**
     * 层序遍历
     * @param root
     * @return
     */
    public List<Integer> layeredorder(Node root){
        if(null == root){
            return new ArrayList<>();
        }
        List<Integer> results = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            Node currNode = queue.poll();
            results.add(currNode.val);

            if(null != currNode.children){
                queue.addAll(currNode.children);
            }
        }

        return results;
    }

    /**
     * 基于层序遍历  返回每一层的节点 即:每一层的节点单独放在一个sub_list中单独返回
     * 算法思想:还是基于层序遍历 只是在层序遍历时 需要将当前层内所有的节点单独放到一个独立的sub_list中
     * 访问完一层就完整地添加到全局返回参数results中
     * @param root
     * @return
     */
    public List<List<Integer>> layeredOrderNodeReturn(Node root){
        List<List<Integer>> results = new ArrayList<>();
        if(null == root){
            return results;
        }

        //当前层
        List<Node> currentLayer = new ArrayList<>();
        currentLayer.add(root);

        while(!currentLayer.isEmpty()){
            //当前层的下一层所有节点
            List<Node> nextLayerList = new ArrayList<>();

            List<Integer> subList = new ArrayList<>();
            for(Node preNode : currentLayer){
                //当前层节点全部放在subList中 以便分层保存各节点的值
                subList.add(preNode.val);
                //将当前层每个节点的孩子节点全部放入到nextLayerList
                if(null != preNode.children){
                    nextLayerList.addAll(preNode.children);
                }
            }

            results.add(subList);
            currentLayer = nextLayerList;
        }

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
        //前序遍历结果
        System.out.println("非递归前序遍历结果:" + nNodeTree.preorder(root));
        System.out.println("递归前序遍历结果:" + nNodeTree.dfs(root));

        //层序遍历结果
        System.out.println("层序遍历结果:" + nNodeTree.layeredorder(root));

        //按层返回每一层的节点
        System.out.println("按层返回各层节点:" + nNodeTree.layeredOrderNodeReturn(root));

        //后序遍历结果
        System.out.println("后序遍历结果:" + nNodeTree.postorder(root));
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
