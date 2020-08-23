学习笔记
1.字母异位词及分组
（1）概念：每个单词中所包括的每一个字母出现的频次相同，只是出现的次序不同
（2）使用HashMap统计各个单词中每一个字母出现的频次，最后在比较在两个单词中相同字母出现的频次是否相同时，
	刚刚开始使用的是== 后来发现明明是异位词 但结果总输出false 最后改为equals()比较两个Integer值的大小
	同时还得到以下结论：
	第一、==
		1）先比较类型 类型不同 直接返回false
		2）类型相同 装箱成同类型 比较的是地址 除了[-128,127]之间使用缓存 其它范围全部重新申请新地址，如下 
			[-128,127]之间的Integer有一个IntegerCache缓存 
			Integer num1 = 59;
			Integer nums2 = new Integer(59);
			nums1 == nums2 ? true
			
			Integer nums3 = 200;
			Integer nums4 = new Integer(200);
			nums3 == nums4 ? false;
（3）一些常用的数组操作
	1）String类型字符串可以直接转换为char[],调用String对象的toCharArray();
	2）Arrays工具类可以直接比较两个基本数据类型数组是否相等，例如：Arrays.equals(arr1,arr2);
	3）直接将Map中每个value值转换为数组：new ArrayList<>(keysMap.values());

2.二叉树
（1）DFS：深度优先遍历
	即：可以通过 前序、中序、后序遍历
（2）BFS：广度优先遍历
	即：使用队列按层序遍历树
3.N叉树
（1）前序遍历：done 非递归方式 
（2）后序遍历：todo
（3）层序遍历：done
（4）基于层序遍历的返回每一层的节点：todo

4.topK问题
（1）使用堆排序：获取最大/小的k个元素 即：从0号元素开始 建立一个大小为K的大/小顶堆
	时间复杂度：O(NlongK)	
（2）Java中自带的堆排序类：PriorityQueue
	1）默认是小顶堆
	2）若要得到一个大顶堆 需要重写Comparable接口
（3）堆的练习题：todo
