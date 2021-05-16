package com.wsz.leetcode;

import java.util.Arrays;

public class Heap {
    int size = 0;
    public int[] queue;

    /*
        以数组的形式实现堆
        为了方便计算，对顶索引设置为1
     */

    public Heap(int initialCapacity) {
        if (initialCapacity < 1)
            throw new IllegalArgumentException();
        queue = new int[initialCapacity];
        Arrays.fill(queue, 0);
        initHeap();
    }

    public Heap(int[] arr) {
        size = arr.length;
        queue = new int[arr.length + 1];
        int i = 1;
        for (int val : arr) {
            queue[i++] = val;
        }
        initHeap();
    }

    private void initHeap(){
        for(int i = size/2; i > 0; i--){
            shiftDown(i);
        }
    }

    // 堆是否为空
    public boolean isEmpty(){
        return size <= 0;
    }

    // 返回堆顶元素
    public int peek(){
        if(this.isEmpty()){
            throw new NullPointerException();
        }
        return queue[1];
    }

    // 弹出堆顶元素并返回
    public int pop(){
        if(this.isEmpty()){
            throw new NullPointerException();
        }
        int ret = queue[1];
        queue[1] = queue[size--];
        shiftDown(1);

        // 压缩空间
        if(size < queue.length/2){
            queue = Arrays.copyOf(queue, queue.length/2);
        }
        return ret;
    }

    // 添加元素并保持有序
    public void push(int val){
        // 扩容
        if(size == queue.length - 1){
            queue = Arrays.copyOf(queue, Math.max(1, size)*2 + 1);
        }
        System.out.println("*****");
        this.show();
        queue[++size] = val;
        shiftUp(size);
    }


    // 下沉
    private void shiftDown(int i){
        int temp = queue[i];
        while(i*2 <= size){
            int child = i * 2; // 左儿子
            // 和更小的儿子节点交换
            if(child != size && queue[child + 1] < queue[child]){
                child++;  // 右儿子
            }
            if(temp > queue[child]){
                queue[i] = queue[child];
                i = child;
            }
            else {
                break;
            }
        }
        queue[i] = temp;
    }

    // 上浮
    private void shiftUp(int i){
        int temp = queue[i];
        while(i / 2 > 0){
            if(temp < queue[i / 2]){
                queue[i] = queue[i / 2];
                i = i / 2;
            }
            else {
                break;
            }
        }
        queue[i] = temp;
    }
    public void show(){
        for(int i = 1; i <= size; i++){
            System.out.print(queue[i] + ",");
        }
        System.out.println();
    }
}

class TestHeap{
    public static void main(String[] args) {
        Heap heap = new Heap(new int[]{10, 7, 2, 5, 1, 16});
        System.out.println(heap.size);
        heap.show();
        while (!heap.isEmpty()){
            System.out.println(heap.pop());
        }
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
        for(int num: nums){
            heap.push(num);
        }
        heap.show();
    }
}
