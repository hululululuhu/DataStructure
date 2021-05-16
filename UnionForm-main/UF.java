import java.util.*;

class UF {
    public int[] pre;
    private int[] sz;
    public int count;

    public UF(int n){
        pre = new int[n+1];
        for(int i = 0; i <= n; i++){
            pre[i] = i;
        }
        sz = new int[n];
        Arrays.fill(sz, 1);
        count = n;
    }
    public int find(int x){
        while(x != pre[x]){
            x = pre[pre[x]];
            x = pre[x];
        }
        return x;
    }

    public void union(int fx, int fy){
        if(sz[fx] < sz[fy]){
            int tmp = fx;
            fx = fy;
            fy = tmp;
        }
        pre[fy] = pre[fx];
        sz[fx] = sz[fy];
        count--;
    }

    public boolean isConecttion(int x, int y){
        return find(x) == find(y);
    }

    public void display(){
        System.out.print("[");
        for(int i = 0; i < pre.length; i++){
            System.out.print(pre[i] + ",");
        }
        System.out.println("]");
    }
}

class TestUF{
    public static void main(String[] args) {
        List<int[]> nums = new ArrayList<>();
        nums.add(new int[]{1, 2, 3, 4});
        nums.add(new int[]{7, 9});
        nums.add(new int[]{2, 7, 5});
        nums.add(new int[]{12, 6});
        nums.add(new int[]{10, 12});

        Set<int[]> numSet = new HashSet<>();
        Map<Integer, Set<Integer>> graph = new TreeMap<>();
        int n = 0;
        for(int[] num:nums){
            graph.put(num[0], new TreeSet<>());
            n = Math.max(n, num[0]);
            for(int i = 1; i < num.length; i++){
                n = Math.max(n, num[i]);
                graph.get(num[0]).add(num[i]);
            }
        }
        System.out.println(graph);

        UF uf = new UF(n + 1);
        for(Integer x:graph.keySet()){
            int fx = uf.find(x);
            for(Integer y:graph.get(x)){
                int fy = uf.find(y);
                if(fx != fy){
                    uf.union(fx, fy);
                }
            }

        }
        uf.display();
        Map<Integer, Integer> map = new HashMap<>();
        // 不存在单个点的连通分量
        for(int i = 0; i <= n; i++){
            map.put(uf.pre[i], map.getOrDefault(uf.pre[i], 0) + 1);
        }
        System.out.println(map);
    }
}
