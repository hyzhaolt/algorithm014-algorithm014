package mile.com.week6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 实质：
 * 从一个只有一半的二维矩阵中的（0，0）节点出发 到达最后一层的最短路径之和
 * 每次移动的时候 只能向下或向右移动一个格子（即：一步）
 * dp方程：
 * dp[i][j]=Min(dp[i+1][j],dp[i+1][j+1]) + nums[i][j]
 * 即：当前位置（i,j)到达最后一层的最短路径就为它的下一层中的两个相邻节点到达底层的最短路径中的较小者再加上当前位置上的值
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TriangleMininumPathSum {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (null == triangle || triangle.isEmpty()
                || null == triangle.get(0) || triangle.get(0).isEmpty()) {
            return 0;
        }

        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> rows = triangle.get(i);
            for (int j = 0; j < triangle.get(i).size(); j++) {
                rows.set(j,
                        Math.min(triangle.get(i + 1).get(j),
                                triangle.get(i + 1).get(j + 1))
                                + rows.get(j));
            }
        }

        return triangle.get(0).get(0);
    }

    public static void main(String[] args) {
        TriangleMininumPathSum triangleMininumPathSum = new TriangleMininumPathSum();
        List<Integer> l0 = Arrays.asList(-1);
        List<Integer> l1 = Arrays.asList(2, 3);
        List<Integer> l2 = Arrays.asList(1, -1, -3);

        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(l0);
        triangle.add(l1);
        triangle.add(l2);
        System.out.println("原始三角形：");
        for (List<Integer> list : triangle) {
            System.out.println(list);
        }

        int sum = triangleMininumPathSum.minimumTotal(triangle);
        System.out.println("三角形最小路径之和：" + sum);
    }
}
