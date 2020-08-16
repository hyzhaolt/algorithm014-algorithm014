package mile.com.week1;

import javax.security.sasl.SaslServer;

/**
 * 柱状图中最大的矩形面积
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * Created by zhaofengying on 2020/8/15.
 */
public class LargestRectangleArea {
    /**
     * 面积=长(宽)*高
     * 所以任意两根柱子i和j之间的面积=(j -1 + 1) *min(i和j之间最小的高度) 其中:i<=j
     * 方法一:枚举宽度,即:任意两个柱子之间(包括:单个柱子也算) 再寻找其中最小的高度 就能计算出任意两个
     * 柱子之间的面积  然后再选择最大的面积
     *
     * 方法二:枚举任意一个高度 即:选任意一个柱子 向两边延伸 直至遇到第一个比当前高度小的柱子
     * 分别记录 left和right ,其中位置记作:i和j -->area = (j - i + 1) * min(height[i],height[j]
     * @param heights
     * @return
     */
    public int largestRectangleAreaFixedWeight(int[] heights) {
        if(null == heights || heights.length <= 0){
            return 0;
        }

        int maxArea = 0;
        for (int i=0; i<heights.length ; i++){
            for (int j=i; j<heights.length; j++){
                int minHeight = Integer.MAX_VALUE;
                for(int k=i; k<=j; k++){
                    minHeight = Math.min(minHeight,heights[k]);
                }
                int area = (j - i + 1) * minHeight;
                maxArea = Math.max(maxArea,area);
            }
        }

        return maxArea;
    }

    /**
     * 固定高度 向两侧延伸 寻找最大相邻的矩形面积
     * @param heights
     * @return
     */
    public int largetRectangleAreaFixedHeight(int[] heights){
        if(null == heights || heights.length <= 0){
            return 0;
        }

        int maxArea = 0;
        for (int i=0; i<heights.length; i++){
            int left = i;
            int right = i;

            while(left - 1 >=0 && heights[left - 1] >= heights[i]){
                left --;
            }

            while(right + 1 < heights.length && heights[right + 1] >= heights[i]){
                right ++;
            }

            int area = (right - left + 1) * heights[i];
            maxArea = Math.max(maxArea,area);
        }

        return maxArea;
    }

    /**
     * 使用栈计算最大面积 暂时还未完全弄明白是怎么回事
     * @param heights
     * @return
     */
    public int largestRectangleAreaWithStack(int[] heights){
        if(null == heights || heights.length <= 0){
            return 0;
        }

        int maxArea = 0;

        return maxArea;
    }


    public static void main(String[] args){
        LargestRectangleArea area = new LargestRectangleArea();
        int[] heights = {2,1,5,6,2,3};
        System.out.println(area.largestRectangleAreaFixedWeight(heights));
        System.out.println(area.largetRectangleAreaFixedHeight(heights));
    }
}
