import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue(){
        size = 0;
        items = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item item){
        if(item == null) throw new IllegalArgumentException();
        if(size == items.length) resize(2 * items.length);
        items[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException();
        int rand = StdRandom.uniform(size); //随机返回[0,N)之间的一个值
        Item remove = items[rand];
        // move last element to current empty position
        items[rand] = items[size-1];
        items[size-1] = null;
        size--;
        if(size > 0 && size == items.length/4) resize(items.length/2);
        return remove;
    }

    private void resize(int new_size){
        Item[] copy = (Item[]) new Object[new_size];
        for(int i = 0; i < items.length; i++){
            copy[i] = items[i];
        }
        items = copy;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty()) throw new NoSuchElementException();
        int rand = StdRandom.uniform(size);
        return items[rand];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    { return new RandomizedQueueIterator();}

    private class RandomizedQueueIterator implements Iterator <Item>{
        private int index = 0;
        private final int[] randomorder = StdRandom.permutation(size);
        // this was made final cuz it is initialized only in the declaration of constructor
        // StdRandom.permutation(N) 将[0,N)之间的整数打乱顺序排列,这一步相当于将数组下标排成乱序

        public boolean hasNext(){ return index < size; }

        public Item next(){
            if(!hasNext()) throw new NoSuchElementException();
            int n = randomorder[index++];//从乱序的下标中取下一个
            return items[n];//返回该下标对应的数组
        }
        public void remove(){ throw new UnsupportedOperationException();}

    }

    // unit testing (required)
    public static void main(String[] args){
        int[] random = StdRandom.permutation(10);
        for(int i = 0; i<10; i++){
            System.out.println(random[i]);
        }
    }

}