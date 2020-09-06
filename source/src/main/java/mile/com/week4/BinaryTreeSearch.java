package mile.com.week4;

/**
 * 所有二分查找相关的题目全部放在此类的实现中
 * 一 使用二分查找的前提
 * 单调性
 * 存在上下界
 * 能通过下标/索引访问
 * 二 代码
 * (1)二分查找的模板
 * (2)求解平方根:二分查找 牛顿迭代法
 * (3)部分有序如何进行二分查找:极简Solution ; 先还原 再进行二分查找
 * Created by zhaofengying on 2020/9/2.
 */
public class BinaryTreeSearch {

    /**
     * 二分查找模板
     * 在有序递增数组nums中查找target 若查找的到则返回该元素的下标 若不存在 则返回-1
     * @return
     */
    public int binarySearchTemplate(int[] nums,int target){
        if(null == nums || nums.length <= 0){
            return -1;
        }

        int left = 0,right = nums.length - 1,mid = -1;

        while(left <= right){
            mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] < target){
                left = mid + 1;
            }
            else if(nums[mid] > target){
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。

     ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

     请找出其中最小的元素。

     你可以假设数组中不存在重复元素。

     示例 1:

     输入: [3,4,5,1,2]
     输出: 1
     示例 2:

     输入: [4,5,6,7,0,1,2]
     输出: 0

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 一个有序递增数组 在一个未知的点被反转了 那么需要找到这个反转点
     * @param nums
     * @return
     */
    public int searchMinElementInRoatedSortedArray(int[] nums){
        if(null == nums || nums.length <= 0){
            return -1;
        }

        int left = 0,right = nums.length - 1;
        while(left < right){
            int mid = (left + right) / 2;

            //nums[mid] < nums[right]时 muns[mid]有可能成为最小值 所以right不能略过mid下标
            if(nums[mid] < nums[right]){
                right = mid;
            }
            //nums[mid] > nums[right]时 nums[mid]不可能成为最小值 因为至少要比它还小的nums[right]
            else{
                left = mid + 1;
            }
        }

        //循环跳出条件 left == right
        return nums[left];
    }

    /**
     * 找出最小值当前的位置
     * @param nums
     * @return
     */
    public int searchRotatedPointInSortedArray(int[] nums){
        if(null == nums || nums.length <= 0){
            return -1;
        }

        int left = 0,right = nums.length - 1;
        while(left < right){
            int mid = (left + right) / 2;

            //nums[mid] < nums[right]时 muns[mid]有可能成为最小值 所以right不能略过mid下标
            if(nums[mid] < nums[right]){
                right = mid;
            }
            //nums[mid] > nums[right]时 nums[mid]不可能成为最小值 因为至少要比它还小的nums[right]
            else{
                left = mid + 1;
            }
        }

        //循环跳出条件 left == right
        return left;
    }


    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。

     ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

     搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

     你可以假设数组中不存在重复的元素。

     你的算法时间复杂度必须是 O(log n) 级别。

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     */
    public int searchInRotatedSortedArray(int[] nums,int target){
        if(null == nums || nums.length <=0 ){
            return -1;
        }

        int[] originNums = new int[nums.length];
        int mid = this.searchRotatedPointInSortedArray(nums);
        for(int i=mid; i<mid + nums.length; i++){
            int index = i % nums.length;
            originNums[i - mid] = nums[index];
        }

        int originIndex = this.binarySearchTemplate(originNums,target);

        return originIndex == -1 ? -1 : (originIndex + mid) % nums.length;
    }

    /**
    编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

    每行中的整数从左到右按升序排列。
    每行的第一个整数大于前一行的最后一个整数。
    示例 1:

    输入:
    matrix = [
            [1,   3,  5,  7],
            [10, 11, 16, 20],
            [23, 30, 34, 50]
            ]
    target = 3
    输出: true

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/search-a-2d-matrix

     解题思路:
     (1)整体上:使用二分查找
     (2)关键:如何将二维数组的下标映射为一维数组的下标
     (3)时间复杂度:O(log(m*n))
    **/
    public boolean searchMatrix(int[][] matrix, int target) {
        if(null == matrix || matrix.length <= 0
                || null == matrix[0] || matrix[0].length <= 0){
            return false;
        }

        int left = 0,right = matrix.length * matrix[0].length - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            int i = mid / matrix[0].length;
            int j = mid % matrix[0].length;

            if(target == matrix[i][j]){
                return true;
            }

            if(target < matrix[i][j]){
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }

        return false;
    }

    /**
     * 将一个长度为m*n的一维数组 生成一个m行 n列的二维数组
     * m >= 1 n>=1 nums非空且nums.length = m,nums[0].length = n
     * 时间复杂度:O(m*n)
     * @param m
     * @param n
     * @param nums
     * @return
     */
    public int[][] generateMatrix(int m,int n,int[] nums){
        //合法性判断 非法时 返回空的二维数组
        if(m <= 0 || n <= 0 || null == nums || nums.length < m *n){
            return new int[][]{};
        }

        //正常情况下返回m*n的二维数组
        int[][] martix = new int[m][n];
        for(int i=0; i<nums.length; i++){
            int x = i / n;
            int y = i % n;

            martix[x][y] = nums[i];
        }

        return martix;
    }

    /**
     * 判断一个数是否是完全平方数
     * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。

     说明：不要使用任何内置的库函数，如  sqrt。

     示例 1：

     输入：16
     输出：True
     示例 2：

     输入：14
     输出：False

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/valid-perfect-square
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

     思路:二分查找
     在[1,num]中进行二分查找 target=num[mid] * num[mid]
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        if(num == 1){
            return true;
        }

        if(num > 1 && num <= 3){
            return false;
        }

        int left = 1;
        int right = num / 2;

        while(left <= right){
            int mid = left + (right - left) / 2;
            int target = mid * mid;
            if(target == num){
                return true;
            }

            if(target < num){
                left = left + 1;
            }

            if(target > num){
                right = mid - 1;
            }
        }

        return false;
    }

    /**
     * 实现 int sqrt(int x) 函数。

     计算并返回 x 的平方根，其中 x 是非负整数。

     由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/sqrtx
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

     思路:从[1,x] 进行二分查找 寻找一个Xi * Xi最接近于x的数
     * @param x
     * @return
     */
    public int mySqrt(int x){
        if(x == 0){
            return 0;
        }

        int left = 1,right = x,results = -1;
        while(left <= right){
            int mid = left + (right - left) / 2;

            if((long) mid * mid <= x){
                results = mid;
                left = mid + 1;
            }

            else{
                right = mid - 1;
            }
        }

        return results;
    }


    public static void main(String[] args){
        int[] nums = {1,2,3,4,5,6,7,8,9,10};
        int target = 8;

        BinaryTreeSearch binaryTreeSearch = new BinaryTreeSearch();
        int searchRet = binaryTreeSearch.binarySearchTemplate(nums,target);
        System.out.println("1.标准的二分查找,target:" + target
                        + ",search result:" + searchRet
        );

        int[] rotatedNums = {3,1};
        int target2 = 3;
        System.out.println("2.在旋转过了的有序数组中进行二分查找:" + binaryTreeSearch.searchInRotatedSortedArray(rotatedNums,target2));

        int[] arr = {1,3,5,7,10,11,16,20,23,30,34,50};
        int m=3,n=4;
        System.out.println("3.测试一维数组生成二维数组");
        int[][] matrix = binaryTreeSearch.generateMatrix(3,4,arr);
        for(int i=0; i<m; i++){
            String line = "";
            for(int j=0; j<n; j++){
                line = line + matrix[i][j] + ",";
            }
            System.out.println("matrix " + (i + 1) + " 行结果:" + line);
        }

        int matrixTarget = 23;
        System.out.println("4.测试二维数组的二分查找======");
        System.out.println("target: " + matrixTarget + " 查找结果:" + binaryTreeSearch.searchMatrix(matrix,matrixTarget));
        System.out.println("target:1 查询结果:" + binaryTreeSearch.searchMatrix(matrix,1));
        System.out.println("target:50 查询结果:" + binaryTreeSearch.searchMatrix(matrix,50));
        System.out.println("target:7 查询结果:" + binaryTreeSearch.searchMatrix(matrix,7));
        System.out.println("target:10 查询结果:" + binaryTreeSearch.searchMatrix(matrix,10));
        System.out.println("target:25 查询结果:" + binaryTreeSearch.searchMatrix(matrix,25));
        System.out.println("target:51 查询结果:" + binaryTreeSearch.searchMatrix(matrix,51));
        System.out.println("target:4 查询结果:" + binaryTreeSearch.searchMatrix(matrix,4));


        System.out.println("=======5.判断一个数是否是完全平方数=======");
        System.out.println("1 is perfect square:" + binaryTreeSearch.isPerfectSquare(1));
        System.out.println("14 is perfect square:" + binaryTreeSearch.isPerfectSquare(14));
        System.out.println("18 is perfect square:" + binaryTreeSearch.isPerfectSquare(18));
        System.out.println("16 is perfect square:" + binaryTreeSearch.isPerfectSquare(16));
        System.out.println("64 is perfect square:" + binaryTreeSearch.isPerfectSquare(64));
        System.out.println("2147483647 is perfect square:" + binaryTreeSearch.isPerfectSquare(2147483647));

        System.out.println("=======6.给一个非负整数开平方根=======");
        System.out.println("16 mySqrt:" + binaryTreeSearch.mySqrt(16));
        System.out.println("8 mySqrt:" + binaryTreeSearch.mySqrt(8));
        System.out.println("2147483647 mySqrt:" + binaryTreeSearch.mySqrt(2147483647));
    }
}
