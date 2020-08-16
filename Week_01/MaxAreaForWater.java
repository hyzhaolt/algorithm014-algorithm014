package mile.com.week1;

/**
 * Created by zhaofengying on 2020/8/13.
 */
public class MaxAreaForWater {
    public int maxArea(int[] height) {
        if(null == height || height.length < 2){
            return 0;
        }

        int maxArea = 0;
        int i=0;
        int j=height.length - 1;
        while(i < j){
            int area = (j - i) * (height[i] <= height[j] ? height[i] : height[j]);
            if(maxArea < area){
                maxArea = area;
            }

            if(height[i] <= height[j]){
                i ++;
            }
            else{
                j --;
            }
        }

        return maxArea;
    }

    public static void main(String[] args){
        int[] nums = {1,8,6,2,5,4,8,3,7};
        System.out.println(new MaxAreaForWater().maxArea(nums));;
    }
}
