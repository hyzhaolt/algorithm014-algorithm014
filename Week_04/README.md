#学习笔记
本周学习知识点：
1.二分查找及各种应用【done】
（1）二分查找的模板
	注意：求mid值的越界问题
	mid = left + (right - left ) / 2; //不会越界
	mid = (left + right) / 2;//会越界
（2）在旋转了的半有序数组中进行二分查找
	1）使用二分法找到分界点--》在两个分别有序的数组中进行二分查找
（3）判断一个数是否是完全平方数
（4）求一个整数的整数平方根

2.二叉树层序遍历及其各种应用【done】
（1）层序遍历
（2）按层返回每一层的节点
（3）按层统计每一层的最大值节点

3.岛屿的数量及其应用【done】
实质：基于网格的DFS实现及变种
联想：二叉树：只有left,right两个叉；N叉树：用[] children数组来表示其孩子 在遍历时向下drill down时
使用children进行到底；岛屿：其实是一个四叉的图 且用二维矩阵来表示
岛屿通用数据模型：
grid[][]：1--表示陆地 0--表示海洋 2--表示访问过了
当前节点：(row,col) 分别对其上(row-1,col) 下(row+1,col) 左(row,col-1) 右(row,col+1)四个节点进行dfs遍历即可
（1）岛屿的数量
 最大连通子图的个数，使用DFS一直向下递归，直至走到了网格的边界再返回
（2）岛屿的周长
 在（1）的基础上判断其邻接点的类型 如果是边界连长+1 如果是海洋连长加1 
（3）岛屿的最大面积
 在（1）的基础上每遍历到一个新的未访问过的节点面积就+1 然后递归地统计其邻接节点
（4）填海造陆
 在（1）的基础上 统计每一个岛屿的 并以（当前发现的是第几个岛屿，当前岛屿的面积）
 在（1）的基础上 dfs每一个海洋 并枚举若将此空格填海造陆的话后会形成多大面积的岛屿

4.DFS的应用：基因变换 单词变换
有的是求最短路径，有的是找出来所有可能变幻的序列/路径
关键：以什么的规则及数据结构 构建一个有向/无向图 然后其于此图结构就容易解决了【具体编码：todo】

5.贪心算法：todo
