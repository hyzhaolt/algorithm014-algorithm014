package mile.com.week3;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * Created by zhaofengying on 2020/8/31.
 */
public class Sublist {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if(null == nums){
            return results;
        }

        dfs(results,nums,new ArrayList<>(),0);
        return results;
    }

    /**
     *
     * @param results
     * @param nums
     * @param list
     * @param index
     * @return
     */
    private void dfs(List<List<Integer>> results,int[] nums,List<Integer> list,int index){
        //走到最后一层 就可以确定结果了 可以将现在的结果添加到最终结果results中
        if(nums.length == index){
            results.add(new ArrayList<>(list));
            return ;
        }

        //nums[index]不选此数字
        dfs(results,nums,list,index + 1);
        //nums[index]选此数字
        list.add(nums[index]);
        dfs(results,nums,list,index + 1);

        //
        list.remove(list.size() - 1);
    }

    public static void main(String[] args){
        Sublist sublist = new Sublist();
        int[] nums = {1,2,3};
        System.out.println(sublist.subsets(nums));
    }
}
