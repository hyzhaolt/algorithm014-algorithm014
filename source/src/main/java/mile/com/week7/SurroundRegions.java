package mile.com.week7;

/**
 * https://leetcode-cn.com/problems/surrounded-regions/
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 */
public class SurroundRegions {
    public void solve(char[][] board) {
        //1.将所有边界上的O找到 然后递归地 上 下 左 右找与其相邻的点 标记为B
        //2.再将网格中所有的O--》X
        //3.再将所有的B变为0
    }
}
