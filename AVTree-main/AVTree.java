class AVTree{

    public static TreeNodes insert(TreeNodes root, int val){
        if(root == null){
            root = new TreeNodes(val, null, 0);
            return root;
        }
        TreeNodes cur = root;
        TreeNodes parent = null;

        // step1：找到插入点
        while(cur != null){
            parent = cur;
            if(val > cur.val){
                cur = cur.right;
            }
            else if(val < cur.val){
                cur = cur.left;
            }
            else {
                return root;
            }
        }
        cur = new TreeNodes(val, parent, 0);
        // step2：插入
        if(parent.val > cur.val){
            parent.left = cur;
        }
        else {
            parent.right = cur;
        }
        cur.parent = parent;
        if (val == 6){
            System.out.println(parent.right.val + ", " + cur.val);
        }

        // step3：更新平衡因子
        while (parent != null){
            if(parent.left != null && cur.val == parent.left.val){
                parent.bf--;
            }
            else {
                parent.bf++;
            }

            if(parent.bf == 0){ // 平衡
                break;
            }else if(parent.bf == -1 || parent.bf == 1){ // 高度改变，向上调整。。。。
                cur = parent;
                parent = parent.parent;
            }
            else {
                if(parent.bf == 2 && cur.bf == 1){
                    // Todo:左单旋
                    System.out.println("左单旋" + parent.val + ", " + cur.val);
                    return rotateL(parent, root);

                }
                else if(parent.bf == 2 && cur.bf == -1){
                    // Todo:右左双旋
                    System.out.println("右左双旋" + parent.val + ", " + cur.val);
                    return rotateRL(parent, root);
                }
                else if(parent.bf == -2 && cur.bf == 1){
                    // Todo:左右双旋
                    System.out.println("右左双旋" + parent.val + ", " + cur.val);
                    return rotateLR(parent, root);
                }
                else if(parent.bf == -2 && cur.bf == -1){
                    // Todo:右单旋
                    System.out.println("右单旋" + parent.val + ", " + cur.val);
                    return rotateR(parent, root);
                }
                break;
            }
        }
        return root;
    }

    // 右单旋
    private static TreeNodes rotateR(TreeNodes cur, TreeNodes root) {
        TreeNodes childL = cur.left;
        TreeNodes childLR = childL.right;
        TreeNodes parent = cur.parent;

        cur.left = childLR;

        if(childLR != null){
            childLR.parent = cur;
        }
        // 当前节点作为子节点的右孩子
        childL.right = cur;
        cur.parent = childL;
        if(cur == root){
            root = cur;
            cur.parent = null;
        }else {
            if(parent.right == cur){
                parent.right = childL;
            }else {
                parent.left = childL;
            }
            childL.parent = parent;
        }
        cur.bf = childL.bf = 0;
        return root;
    }

    // 左单旋
    private static TreeNodes rotateL(TreeNodes cur, TreeNodes root) {
        TreeNodes childR = cur.right;
        TreeNodes childRL = childR.left;
        TreeNodes parent = cur.parent;

        cur.right = childRL;

        if(childRL != null){
            childRL.parent = cur;
        }
        childR.left = cur;
        cur.parent = childR;
        if(cur == root){
            root = childR;
            root.parent = null;
        }else {
            if(parent.left == childR){
                parent.left = childR;
            }else {
                parent.right = childR;
            }
            childR.parent = parent;
        }
        cur.bf = childR.bf = 0;
        return root;
    }

    // 先右后左
    public static TreeNodes rotateRL(TreeNodes cur, TreeNodes root){
        TreeNodes childR = cur.right;
        TreeNodes childRL = childR.right;
        int bf = childRL.bf;
        root = rotateR(cur.right, root);
        root = rotateL(cur, root);
        if(bf == 0){
            childRL.bf = childR.bf = cur.bf = 0;
        }else if(bf == 1){
            cur.bf = 0;
            childR.bf = -1;
            childRL.bf = 0;
        }else if(bf == -1){
            cur.bf = 0;
            childR.bf = 1;
            childRL.bf = 0;
        }
        return root;
    }

    // 先左后右
    private static TreeNodes rotateLR(TreeNodes cur, TreeNodes root) {
        TreeNodes childL = cur.right;
        TreeNodes chidLR = childL.right;
        int bf = chidLR.bf;
        root = rotateL(cur.left, root);
        root = rotateR(cur, root);
        if(bf == 0){
            chidLR.bf = childL.bf = cur.bf = 0;
        }else if(bf == 1){
            cur.bf = 0;
            childL.bf = -1;
            chidLR.bf = 0;
        }else if(bf == -1){
            cur.bf = 0;
            childL.bf = 1;
            chidLR.bf = 0;
        }
        return root;
    }

    public static List<List<Integer>> lefvelTrvel(TreeNodes root){
        Queue<TreeNodes> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> ans = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNodes cur = queue.poll();
            if(cur == null){
                ans.add(Arrays.asList(null, 0));
                continue;
            }else {
                ans.add(Arrays.asList(cur.val, cur.bf));
            }

            queue.offer(cur.left == null ? null : cur.left);

            queue.offer(cur.right == null ? null : cur.right);

        }
        return ans;
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<Integer>(){{
            add(20);
            add(10);
            add(50);
            add(40);
            add(60);
        }};
        TreeNodes root = new TreeNodes(nums.get(0), 0);
        for(int i = 1; i < nums.size(); i++){
            root = insert(root, nums.get(i));
        }
        System.out.println(lefvelTrvel(root));
        root = insert(root, 30);
        System.out.println(root.val);

    }
}
