public class LRUCache {
    public Map<Integer, Object[]> maps;
    public ListNodes head;
    public ListNodes tail;
    private int capacity;
    public int length;
    public LRUCache(int capacity) {
        head = new ListNodes(-1);
        tail = new ListNodes(-1);
        maps = new HashMap<>();
        this.capacity = capacity;
        head.next = tail;
        tail.parent = head;
    }

    public int get(int key) {
        if(maps.containsKey(key)){
            ListNodes cur = (ListNodes) maps.get(key)[0];
            ListNodes parent = cur.parent;
            ListNodes next = cur.next;
            parent.next = next;
            next.parent = parent;
            ListNodes tmp = head.next;
            head.next = cur;
            cur.next = tmp;
            tmp.parent = cur;
            cur.parent = head;
            return (Integer) maps.get(key)[1];
        }
        return -1;
    }

    public void put(int key, int value) {
        if(maps.containsKey(key)){
            ListNodes cur = (ListNodes) maps.get(key)[0];
            ListNodes parent = cur.parent;
            ListNodes next = cur.next;
            parent.next = next;
            next.parent = parent;
            ListNodes tmp = head.next;
            head.next = cur;
            cur.next = tmp;
            tmp.parent = cur;
            cur.parent = head;
            maps.put(key, new Object[]{cur, value});
            return;
        }
        if(length == capacity){
            ListNodes tmp = tail.parent.parent;
            ListNodes del = tail.parent;
            tmp.next = tail;
            tail.parent = tmp;
            maps.remove(del.val);
            length--;
        }
        ListNodes cur = new ListNodes(key);
        ListNodes tmp = head.next;
        head.next = cur;
        cur.next = tmp;
        tmp.parent = cur;
        cur.parent = head;
        maps.put(key, new Object[]{cur, value});
        length++;
        return;
    }
}
