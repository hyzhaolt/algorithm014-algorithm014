package mile.com.week6;

/**
 * 不同路径问题
 * 即：给起点和终点 找两点之间所有可达的路径
 * 1.无障碍物
 * 2.有障碍物
 */
public class UniquePaths {
    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * <p>
     * 问总共有多少条不同的路径？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-paths
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[][] dp = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                //终点位置本身 初始化为0
                if (i == m - 1 && j == n - 1) dp[i][j] = 0;
                //与终点在同一行的节点-->终点 只能向右移动 所以只有一种走法
                //与终点在同一列的节点-->终点 只能向下移动 所以只有一种走法
                if (i == m - 1 || j == n - 1) {
                    dp[i][j] = 1;
                }
                //除了终点所在位置的边界之内的任何一个节点-->终点的走法都有两种选择 就转换为了相邻两个节点到达终点
                //所有的路径走法之和
                else {
                    dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
                }
            }
        }

        return dp[0][0];
    }

    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     * 网格中的障碍物和空位置分别用 1 和 0 来表示。
     * 说明：m 和 n 的值均不超过 100。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-paths-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (null == obstacleGrid || obstacleGrid.length <= 0 || obstacleGrid[0].length <= 0){
            return 0;
        }

        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = obstacleGrid.length - 1; i>=0; i--){
            for (int j = obstacleGrid[0].length - 1; j>=0; j--){
                //故障物的地方都是无法到达终点的 所以dp方程的值全部为0
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                    continue;
                }
                //终点站初始化 如果终点是障碍物 在第一步就被设置为0 不会走这一步
                if(i == obstacleGrid.length - 1 && j == obstacleGrid[0].length - 1){
                    dp[i][j] = 1;
                    continue;
                }
                //最下面一行 与终点在同一行 只能向右移动到达终点 所以都只有一种路径到达终点
                if(i == obstacleGrid.length - 1){
                    dp[i][j] = dp[i][j+1];
                }
                //最右面一列 与终点在同一列 只能向下移动到达终点 所以都只有一种路径到达终点
               else if(j == obstacleGrid[0].length - 1 ){
                    dp[i][j] = dp[i+1][j];
                }
                //除了终点所在位置的边界之内的任何一个节点-->终点的走法都有两种选择
                else{
                    dp[i][j] = dp[i][j+1] + dp[i+1][j];
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        int m = 7, n = 3;
        int uniquePathsCnt = uniquePaths.uniquePaths(m, n);
        System.out.println("m * n:" + (m) + "*" + (n) + "不同路径结果：" + uniquePathsCnt);

//        int[][] grid = {{0,0,0},{0,1,0},{0,0,0}};
        int[][] grid = {{0,0,0},{0,1,0},{0,0,0}};
        int uniquePaths2 = uniquePaths.uniquePathsWithObstacles(grid);
        System.out.println("uniquePaths2:" + uniquePaths2);

    }
}
