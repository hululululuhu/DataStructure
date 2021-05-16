import java.util.Map;
import java.util.TreeMap;


class TrieNode{
    int count; //表示以该处节点构成的串的个数
    int preCount; //表示以该处节点构成的前缀的字串的个数
    TrieNode[] children;

    TrieNode() {

        children = new TrieNode[256];
        count = 0;
        preCount = 0;
    }
}
class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {

        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            if (node.children[word.charAt(i) - '0'] == null)
                node.children[word.charAt(i) - '0'] = new TrieNode();
            node = node.children[word.charAt(i) - '0'];
            node.preCount++;
        }

        node.count++;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (node.children[prefix.charAt(i) - '0'] == null)
                return false;
            node = node.children[prefix.charAt(i) - '0'];
        }
        return node.preCount > 0;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,10,5,25,2,8};
        Trie trie = new Trie();

        for(int num:nums){
            StringBuffer bits = new StringBuffer();
            for(int i = 31; i >= 0; i--){
                if((num & (1 << i)) != 0){
                    bits.append(1);
                }
                else {
                    bits.append(0);
                }
            }
            trie.insert(bits.toString());
        }
        System.out.println(trie.root.children[0] == null);
        System.out.println(trie.root.children[1] == null);
        for(int num:nums){
            StringBuffer bits = new StringBuffer();
            for(int i = 31; i >= 0; i--){
                if((num & (1 << i)) != 0){
                    bits.append(1);
                }
                else {
                    bits.append(0);
                }
            }
            String bits1 = bits.toString();
            System.out.println(bits1);
            TrieNode cur = trie.root;
            int tmp = 0;
            for(int i = 0 ; i <= 31; i++){

                if(cur.children['1' - bits1.charAt(i)] != null){
                    System.out.println('1' - bits1.charAt(i));
                    cur = cur.children['1' - bits1.charAt(i)];
                    tmp += 1<<(31-i);
                }
                else {
                    cur = cur.children[bits1.charAt(i) - '0'];
                }
            }

        }
    }
}
