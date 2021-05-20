
### 平衡二叉搜索树（AVLTree）
因为二叉搜索树最坏可能会退化成一个链表，于是有了AVL树
性质：
1. 左右子树高度差最大不能超过1
2. 左子树节点值比父节点小，右子树节点值比父节点大

定义：
```java
class TreeNodes{
    public int val;
    public TreeNodes left;
    public TreeNodes right;
    public TreeNodes parent;
    public int bf; // 平衡因子：0：平衡； < 0,  左边大；> 0，右边大
}
```

### 插入
每次插入分为三步：
1. 找到插入位置
2. 插入
3. 更新平衡因子，保持平衡

这里着重解释第三步，如何保持平衡
每次插入，有四种方式保持平衡
#### 右旋
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210519212710830.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwMTI0MjQx,size_16,color_FFFFFF,t_70)
#### 左旋
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210519212848917.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwMTI0MjQx,size_16,color_FFFFFF,t_70)
#### 先右后左
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210519215415168.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwMTI0MjQx,size_16,color_FFFFFF,t_70)
#### 先左后右
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210519222211310.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzMwMTI0MjQx,size_16,color_FFFFFF,t_70)
