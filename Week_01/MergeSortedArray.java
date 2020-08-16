package mile.com.week1;

/**
 * Created by zhaofengying on 2020/8/16.
 */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //如果未指定数组或num2数组的长度为0 则没必要做任何合并操作
        if(null == nums1 || null == nums2 || n <=0 ){
            return;
        }

        int i=0;
        int j=0;
        //i和j分别指向两个数组当前要比较的两个元素
        while(i < m && j < n){
            //nums1表中的元素都不大于nums2[j] i直接后移
            if(nums1[i] <= nums2[j]){
                i++;
            }
            //当前nums2[j]插入到nums1[i]位置上  [i,m-1]之间的元素全部后移 插入nums[j]到i位置 m++ m：始终代表nums1数组的当前长度
            else{
                for(int k=m; k>i ; k--){
                    nums1[k] = nums1[k - 1];
                }
                nums1[i] = nums2[j];
                m++;
                j++;
            }
        }

        //跳出循环只有两种可能 第一：num1数组遍历结束 i>=m 第二：nums2遍历结束
        //如果nums2遍历结束 表示nums2中的元素已经全部插入到nums1中 所以不必做任何额外的操作
        //如果nums1遍历结束 但nums2可能还没有遍历完 此时需要把剩余的元素全部copy到nums1中
        //nums2数组中还有剩余未插入的元素 直接添加到nums1的后面
        if(j < n){
            while(j < n){
                nums1[i++] = nums2[j++];
                m++;
            }
        }
    }
}
