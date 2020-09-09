package mile.com.week3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * N皇后问题
 * Created by zhaofengying on 2020/9/9.
 */
public class NQueens {
    List<List<String>> results = new ArrayList<>();
    Set<Integer> cols = new HashSet<>();
    Set<Integer> pie = new HashSet<>();
    Set<Integer> na = new HashSet<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] chs = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chs[i][j] = '.';
            }
        }

        backTracing(chs, 0, n);
        //统一将chs[][]转换为要打印的字符串
        return results;
    }

    /**
     * 深度优先+回溯为每一个皇后找可以放置的位置
     *
     * @param chs
     * @param row
     * @param n
     */
    private void backTracing(char[][] chs, int row, int n) {
        //如果第n行都已经找到了queen可以放的位置 说明当前1~n个皇后已全部放好 是一种解
        if (row == n) {
            results.add(charsArr2List(chs));
            return;
        }

        for (int col = 0; col < n; col++) {
            //当前位置(row,col)可以放置一个queen
            if (isValid(row, col)) {
                chs[row][col] = 'Q';
                cols.add(col);
                pie.add(row + col);
                na.add(row - col);

                //drill down 放置下一行
                backTracing(chs, row + 1, n);
                //两种情况:row+1~n行 全部放置好了queen或者在row+1~n行中的任意一行 无法放置任何一个queen
                //但不管是哪种情况 都需要将当前位置清空 前者的话:已经有一种解了(需要清空 从col+1位置继续放);目前还没有解 需要回溯
                //所以都需要将当前的摆放位置清空
                chs[row][col] = '.';
                cols.remove(col);
                pie.remove(row + col);
                na.remove(row - col);
            }
        }
    }

    /**
     * 判断当前(row,col)是否可以放置一个皇后
     * 当前所在位置(row,col)为空 当前列全部为空 当前pie(45度对角线) 当前na(135度对角线)
     * 全部为空 表示当前位置暂时是合法的(暂时:指的是到当前row行为止是合法的)
     *
     * @param row
     * @param col
     * @return
     */
    private boolean isValid(int row, int col) {
        if(this.cols.contains(col)
                || this.pie.contains(row + col)
                || this.na.contains(row - col)){
            return false;
        }

        return true;
    }


    /**
     * 将二维字符数组 转换为List<String>
     *
     * @param chs
     * @return
     */
    private List<String> charsArr2List(char[][] chs) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < chs.length; i++) {
            list.add(new String(chs[i]));
        }

        return list;
    }

    public static void main(String[] args){
        NQueens nQueens = new NQueens();
        List<List<String>> results = nQueens.solveNQueens(8);
        System.out.println("共有" + results.size() + "种棋盘放法");
        int i=0;
        for(List<String> result : results){
            System.out.println("第" + (++i) + "种棋盘:");
            for(String r : result){
                System.out.println(r);
            }
        }
    }
}
