class TreeNodes{
    public int val;
    public TreeNodes left;
    public TreeNodes right;
    TreeNodes(){};
    TreeNodes(int val){
        this.val = val;
    }
    TreeNodes(int val, TreeNodes left, TreeNodes right){
        this.val = val;
        this.left = left;
        this.right = right;
    }


}


class BSTree{//二叉查找树
    /*
        如果比当前节点值大：插入到右子树中
        如果比当前节点值小：插入到左子树中
        如果节点为空：此处插入
     */
    public static TreeNodes insert(TreeNodes root, int val){
        if(root == null){
            return new TreeNodes(val);
        }
        if(val > root.val){
            root.right = insert(root.right, val);
        }
        else if(val < root.val){
            root.left = insert(root.left, val);
        }
        return root;
    }

    public static List<Integer> levelsort(TreeNodes root){
        Queue<TreeNodes> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> ret = new ArrayList<>();
        while(!queue.isEmpty()){
            TreeNodes cur = queue.poll();
            if(cur == null){
                ret.add(null);
                continue;
            }
            ret.add(cur.val);
            if(cur.left != null){
                queue.offer(cur.left);
            }
            else {
                queue.offer(null);
            }
            if(cur.right != null){
                queue.offer(cur.right);
            }
            else {
                queue.offer(null);
            }
        }
        return ret;
    }

    /*
        出度为0：叶子节点，直接删除
        出度为1：删除，孩子节点顶上
        出度为2：更换待删除值为左子树的最大值， 并将此值替换到欲删除节点
     */
    public static TreeNodes delete(TreeNodes root, int val){
        if(root == null){
            return null;
        }
        if(val > root.val){
            root.right = delete(root.right, val);
        }
        else if(val < root.val){
            root.left = delete(root.left, val);
        }
        else {
            if(root.left != null && root.right != null){
                // 出度2:
                TreeNodes target = root.left;
                while(target.right != null){
                    target = target.right;
                }
                root = delete(root, target.val);
                root.val = target.val;
            }
            else {
                if(root.left != null){
                    root = root.left;
                }
                else {
                    root = root.right;
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[] nodes = new int[]{4, 2, 6, 1, 3, 5, 7};

        TreeNodes root = new TreeNodes(4);
        for(int i = 1; i < nodes.length; i++){
            insert(root, nodes[i]);
        }
        System.out.println(levelsort(root));
        delete(root, 2);
        System.out.println(levelsort(root));
    }

}
