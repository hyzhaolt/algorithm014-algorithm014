package mile.com.week8;

import com.sun.media.sound.RIFFInvalidDataException;

/**
 * 十大经典排序算法汇总与分析
 * 默认所有的排序都是升序
 */
public class SortAlgSummary {
    /**
     * 交换排序：冒泡排序
     * 算法思想：
     * 1.每次交换相邻的两个元素 如果后面的元素小于前面相邻的元素 则交换 这样一次循环到最后位置时 将会找到当前最大的元素
     * 2.依次重复1的过程再找到次大的 直到进行到第0个元素
     *
     * @param nums
     */
    public void bubbleSort(int[] nums) {
        if (null == nums || nums.length <= 0) return;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length - i; j++) {
                //前面的元素比后面的大 进行两两交换
                if (nums[j - 1] > nums[j]) {
                    int tmp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = tmp;
                }
            }
        }
    }

    /**
     * 选择排序
     * 将原始数组分成两部分 有序部分和无序部分 每次都是从无序数组中选择当前最小的元素放到有序部分的最后
     * 1.初始状态：有序部分nums[0] 无序部分：1~n-1
     * 2.从1~n-1元素中查找比nums[0]还小的元素 并记录其位置为k 最终将nums[0]与nums[k]交换
     * 3.下次从nums[1]~nums[n-1]中选择最小的元素放到nums[1]位置上
     * 4.重复上述过程 直到从nums[n-2],nums[n-1]两个元素中再选择一个最小的 结束
     *
     * @param nums
     */
    public void simpleSelectSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int k = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[k]) {
                    k = j;
                }
            }
            //最终k记录的位置就是当前最小的元素位置 与i位置上的元素进行交换即可
            if (k != i) {
                int tmp = nums[i];
                nums[i] = nums[k];
                nums[k] = tmp;
            }
        }
    }

    /**
     * 插入排序
     * 对于每一个元素从其当前所在的位置向数组前端寻找其可以插入的位置
     * 初始状态：nums[0]认为是有序的 从nums[1]开始 为每一个元素在[0,i-1]位置上寻找其插入位置
     * 即：只要nums[1]<nums[0] 就一直向后移动元素 最后空出来的位置就是待插入位置
     *
     * @param nums
     */
    public void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            //为当前第i个元素在[0,i-1]位置上寻找合适的插入位置
            int currNum = nums[i];
            int j = i - 1;
            while (j >= 0 && currNum < nums[j]) {
                //将当前[0,i-1]位置上所有比nums[i]还大的元素后移 即：nums[j]-->nums[j+1]
                nums[j + 1] = nums[j];
                j--;
            }
            //j位置已经空出 直接插入当前元素
            nums[j + 1] = currNum;
        }
    }

    /**
     * 归并排序
     *
     * @param nums
     */
    public void mergeSort(int[] nums, int left, int right) {
        //递归结束条件 待比较的元素只有一个
        if (left >= right) return;
        //取中间位置
        int mid = left + (right - left) / 2;
        //将当前数组[left,right] 一分为二分别进行归并排序
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        //将两个有序数组进行合并[left,mid],[mid+1,right]
        mergeSortedNums(nums, left, mid, right);
    }

    /**
     * 合并两个有序数组nums[0,mid],nums[mid+1,right]
     * @param nums
     * @param left
     * @param mid
     * @param right
     */
    private void mergeSortedNums(int[] nums, int left, int mid, int right) {
        int[] sortedNums = new int[right - left + 1];
        System.out.println("合并：left:" + left
                        + ",mid:" + mid
                        + ",right:" + right
        );
        int k = 0, i = 0, j = mid + 1;
        //step1:两个有序数组均未结束的时候 将各个有序部分当前较小者插入到临时数组中
        while (i<=mid && j<= right){
            sortedNums[k++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }
        //step2:将剩余未结束的部分全部拷贝到临时数组中
        while(i<=mid){
            sortedNums[k++] = nums[i++];
        }

        while(j<=right){
            sortedNums[k++] = nums[j++];
        }
        //step3:将合并后的有序数组拷贝到nums中
        System.arraycopy(sortedNums,0,nums,0,sortedNums.length);
    }

    public static void main(String[] args) {
        //1.交换排序：冒泡排序
        int[] nums = {3, 5, 38, 47, 44, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        printArray("===原始数组==", nums);
        new SortAlgSummary().bubbleSort(nums);
        printArray("==冒泡排序结果==", nums);

        //2.选择排序
        int[] nums2 = {3, 5, 38, 47, 44, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        printArray("===选择排序原始数组==", nums2);
        new SortAlgSummary().simpleSelectSort(nums2);
        printArray("==选择排序结果==", nums2);

        //3.插入排序
        int[] nums3 = {3, 5, 38, 47, 44, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        printArray("===插入排序原始数组==", nums3);
        new SortAlgSummary().insertSort(nums3);
        printArray("==插入排序选择排序结果==", nums3);

        //4.归并排序
        int[] nums4 = {3, 5, 38, 47, 44, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        printArray("===归并排序原始数组==", nums4);
        new SortAlgSummary().mergeSort(nums4, 0, nums4.length - 1);
        printArray("==归并排序选择排序结果==", nums4);


    }

    private static void printArray(String title, int[] nums) {
        System.out.println(title);
        for (int num : nums) {
            System.out.print(num + ",");
        }
        System.out.println("");
    }
}
