package mile.com.week1;

/**
 * Created by zhaofengying on 2020/8/16.
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        if(null == nums || nums.length <= 1){
            return;
        }

        //因为k有可能会大于nums.length
        k = k % nums.length;
        //count:总共需要移动的次数
        int count = 0;
        for(int start = 0; count < nums.length ; start ++){
            int current = start;
            int preNum = nums[current];
            do{
                int next = (current + k ) % nums.length;
                int tmp = nums[next];
                nums[next] = preNum;

                preNum = tmp;
                current = next;
                count ++;
            }while(current != start);
        }
    }
}
