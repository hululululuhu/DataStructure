public class LFUCache {
    private int capacity;
    public ListNodes head;
    private ListNodes tail;
    public Map<Integer, Object[]> maps; // {key={ListNodes(key), value, nums}} -- 地址、值、访问次数
    private int length; // 当前容量
    LFUCache(int capacity){
        maps = new HashMap<>();
        head = new ListNodes(-1);
        tail = new ListNodes(-1);
        head.next = tail;
        tail.parent = head;
        this.capacity = capacity;
    }

    public int get(int key){
        if(maps.containsKey(key)){
            int value = (Integer) maps.get(key)[1];
            ListNodes cur = (ListNodes) maps.get(key)[0];
            // 前后连接
            ListNodes p = cur.parent;
            ListNodes q = cur.next;
            p.next = q;
            q.parent = p;

            ListNodes tmp = cur.parent;
            int curNums = (int) maps.get(key)[2] + 1;
            System.out.println(tmp.val);
            // 1.找到插入位置
            while (tmp != head && (Integer)maps.get(tmp.val)[2] < curNums){
                System.out.println(tmp.val);
                tmp = tmp.parent;
            }
            System.out.println(tmp.val);
            // 2.插入
            if(tmp == head){//访问次数目前最高
                ListNodes second = head.next;
                head.next = cur;
                cur.next = second;
                second.parent = cur;
                cur.parent = head;

            }else{//访问次数在中间
                ListNodes front = tmp.parent;
                ListNodes behind = tmp.next;
                front.next = cur;
                cur.next = behind;
                behind.parent = cur;
                cur.parent = front;
            }
            maps.put(key, new Object[]{cur, value, curNums});
            return value;
        }
        return -1;
    }

    public void put(int key, int value){
        if(maps.containsKey(key)){
            //Todo: 更新，长度不变
            ListNodes cur = (ListNodes) maps.get(key)[0];
            ListNodes p = cur.parent;
            ListNodes q = cur.next;
            p.next = q;
            q.parent = p;
            ListNodes tmp = cur.parent;
            int curNums = (int) maps.get(key)[2] + 1;
            System.out.println(tmp.val);
            // 1.找到插入位置
            while (tmp != head && (Integer)maps.get(tmp.val)[2] <= curNums){
                System.out.println(tmp.val);
                tmp = tmp.parent;
            }
            System.out.println(tmp.val);
            // 2.插入
            if(tmp == head){//访问次数目前最高
                ListNodes second = head.next;
                head.next = cur;
                cur.next = second;
                second.parent = cur;
                cur.parent = head;

            }else{//访问次数在中间
                ListNodes front = tmp.parent;
                ListNodes behind = tmp.next;
                front.next = cur;
                cur.next = behind;
                behind.parent = cur;
                cur.parent = front;
            }
            maps.put(key, new Object[]{cur, value, curNums});
            return;
        }
        if(length == capacity){
            ListNodes front = tail.parent.parent;
            ListNodes del = tail.parent;
            front.next = tail;
            tail.parent = front;
            maps.remove(del.val);
            length--;
        }

        // 第一次访问，访问次数为1，放在末尾
        ListNodes cur = new ListNodes(key);
        ListNodes tmp = tail.parent;
        tmp.next = cur;
        cur.next = tail;
        tail.parent = cur;
        cur.parent = tmp;
        maps.put(key, new Object[]{cur, value, 1});
        length++;
        return;
    }
}
