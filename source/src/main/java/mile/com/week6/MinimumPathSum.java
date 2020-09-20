package mile.com.week6;

/**
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * dp[i][j]=min(dp[i][j-1],dp[i-1][j]) + nums[i][j]
 * 其中nums[i][j]表示位置(i,j)上的值
 */
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if(null == grid || grid.length <=0 || grid[0].length <= 0){
            return 0;
        }

        for(int i=0; i<grid.length; i++){
            for(int j=0;j <grid[0].length; j++){
                if(i == 0 && j == 0) continue;
                //表示当前在第一行 那么只能是从其左边的位置移动过来的
                if(i == 0){
                    grid[i][j] = grid[i][j-1] + grid[i][j];
                }
                //表示当前在第一列 那么只能是从其上面的位置移动过来的
                else if(j == 0){
                    grid[i][j] = grid[i-1][j] + grid[i][j];
                }
                else {
                    grid[i][j] = Math.min(grid[i-1][j],grid[i][j-1]) + grid[i][j];
                }
            }
        }

        return grid[grid.length - 1][grid[0].length - 1];
    }

    public static void main(String[] args){
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        MinimumPathSum minimumPathSum = new MinimumPathSum();
        System.out.println("最小路径和：" + minimumPathSum.minPathSum(grid));;
    }
}
