package mile.com.week4;

/**
 * https://leetcode-cn.com/problems/number-of-islands/
 * 输入:
 * [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出: 1
 * <p>
 * 输入:
 * [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
 * 此链接列出来岛屿相关的所有问题
 * <p>
 * Created by zhaofengying on 2020/9/1.
 */
public class NumberOfIslands {
    /**
     * https://leetcode-cn.com/problems/making-a-large-island/
     * 解题思路:
     * 1.一个岛屿是一个连通图
     * 2.一个岛屿的面积=陆地个数 因为每一个陆地的面积都是1
     * 3.所以在进行dfs遍历一个连通图时计算当前连通图的面积 最终获取最大的即可
     * @param grid
     * @return
     */
    public int largestIsland(char[][] grid) {
        if(null == grid
                || grid.length <=0
                || grid[0].length <=0 ){
            return 0;
        }

        //连通子图的个数 即:岛屿的个数
        int largetIsland = 0;

        for(int row = 0; row < grid.length; row ++){
            for(int col = 0; col < grid[0].length; col ++){
                if(this.checkIsRowAndColumnValid(row,col,grid)
                        && grid[row][col] == '1'){
                    Integer currentIslandArea = dfsArea(row, col, grid);
                    System.out.println("find a new island from [" + row + "][" + col + "] area:" + currentIslandArea);

                    if(largetIsland < currentIslandArea){
                        largetIsland = currentIslandArea;
                    }
                }
            }
        }

        return largetIsland;
    }

    /**
     * 网格dfs遍历计算岛屿页面
     * 计算以(row,col)为顶点 且当前尚未访问过的网格组成岛屿的面积
     * @param row
     * @param col
     * @param grid
     */
    private int dfsArea(int row,int col,char[][] grid){
        //terminitor
        if(!this.checkIsRowAndColumnValid(row,col,grid)
                || grid[row][col] != '1'){
            return 0;
        }

        //curretn level logic proccess
        int area = 0;
        grid[row][col] = '2';

        //drill down up down left right
        //上
        area = 1
                + this.dfsArea(row - 1 , col , grid)
        //下
               + this.dfsArea(row + 1 , col , grid)
        //左
               + this.dfsArea(row , col - 1 , grid)
        //右
               + this.dfsArea(row, col + 1, grid);

        return area;
    }

    /**
     * 求一个岛屿的周长
     * https://leetcode-cn.com/problems/island-perimeter/
     * 解题思路:根据岛屿的定义
     * 1.一个岛屿一定是一个连通的图
     * 2.连通图中每一块陆地(四个边边长全为1)的四条边边界会有三种情况
     * (1)网格的边界:周长+1
     * (2)海水:周长+1
     * (3)相邻的陆地:因为相邻 所以不算在周长内
     * 注:要求得四条边的边界 需要当前陆地的上 下 左 右 四个格子的类型来确定
     * 3.前提:
     * grid[][]网格中定义的岛屿有且只有一个
     * @param grid
     * @return
     */
    public int calPerimeter(char[][] grid){
        if(null == grid
                || grid.length <=0
                || grid[0].length <=0 ){
            return 0;
        }

        for(int row=0; row < grid.length ; row ++){
            for(int col=0; col < grid[0].length ; col ++){
                if(grid[row][col] == '1') {
                    return dfsPerimeter(row,col,grid);
                }
            }
        }

        return 0;
    }

    private int dfsPerimeter(int row,int col,char[][] grid){
        //边界是海水 grid[row][col]=0
        if(this.checkIsRowAndColumnValid(row,col,grid)
                && grid[row][col] == '0'){
            return 1;
        }
        //边界是网格边界row,col不在有效范围内
        if(!this.checkIsRowAndColumnValid(row,col,grid)){
            return 1;
        }

        //如果是已经遍历过的岛屿中的一个格子 则略过 grid[row][col]=2
        if(grid[row][col] == '2'){
            return 0;
        }

        //当前正在被访问的节点还未被访问过 设置为 已被访问过
        grid[row][col] = '2';

        //当前节点递归地进行dfs计算周长
        Integer perimeter = dfsPerimeter(row - 1, col, grid)
                            + dfsPerimeter(row + 1, col, grid)
                            + dfsPerimeter(row, col - 1, grid)
                            + dfsPerimeter(row, col + 1, grid)
                ;

        return perimeter;
    }

    /**
     * 广度优先遍历:求一个网格中岛屿的数量
     * 岛屿:只能在水平或竖起方向上的陆地连接形成更大的岛屿
     * @param grid
     * @return
     */
    public int numIslandsBFS(char[][] grid) {
        if(null == grid
                || grid.length <=0
                || grid[0].length <=0 ){
            return 0;
        }

        //连通子图的个数 即:岛屿的个数
        int count = 0;

        for(int row = 0; row < grid.length; row ++){
            for(int col = 0; col < grid[0].length; col ++){
                if(this.checkIsRowAndColumnValid(row,col,grid)
                        && grid[row][col] == '1'){
                    System.out.println("find a new island from [" + row + "][" + col + "]");
                    dfs(row, col, grid);
                    count ++;
                }
            }
        }

        return count;
    }

    /**
     * 网格dfs遍历
     * @param row
     * @param col
     * @param grid
     */
    private void dfs(int row,int col,char[][] grid){
        //terminitor
        if(!this.checkIsRowAndColumnValid(row,col,grid)
                || grid[row][col] != '1'){
            return;
        }

        //curretn level logic proccess
        grid[row][col] = '2';

        //drill down up down left right
        //上
        this.dfs(row - 1 , col , grid);
        //下
        this.dfs(row + 1 , col , grid);
        //左
        this.dfs(row , col - 1 , grid);
        //右
        this.dfs(row, col + 1, grid);
    }

    /**
     * 判断一个岛屿(四网格)中的任意一个节点是否越界
     * @param row
     * @param col
     * @param grid
     * @return
     */
    private boolean checkIsRowAndColumnValid(int row,int col,char[][] grid){
        if(row >=0 && row < grid.length
                && col >=0 && col < grid[0].length){
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '1'},
                {'0', '0', '1', '0', '1'}
        };

        NumberOfIslands numberOfIslands = new NumberOfIslands();
//        int islandCnt = numberOfIslands.numIslandsBFS(grid);
//        System.out.println("1.island cnt:" + islandCnt);

//        char[][] onlyOneIslandGrid = {
//                {'0','1','0','0'},
//                {'1','1','1','0'},
//                {'0','1','0','0'},
//                {'1','1','0','0'}
//        };
        char[][] onlyOneIslandGrid = {
                {'0','1','0','0'},
                {'1','1','1','1'},
                {'0','1','0','0'},
                {'1','1','0','0'}
        };

//        System.out.println("2.island's perimeter:" + numberOfIslands.calPerimeter(onlyOneIslandGrid));


        System.out.println("3.max island area:" + numberOfIslands.largestIsland(grid));
    }
}
