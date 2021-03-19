import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size = 0;

    private class Node {
        Item item;
        Node prev;
        Node next;
    }

    private void initialize(Item item){
        first = new Node();
        first.prev = null;
        first.next = null;
        first.item = item;
        last = first;
        size++;
    }

    // construct an empty deque
    public Deque(){size = 0;}

    // is the deque empty?
    public boolean isEmpty(){return size == 0;}

    // return the number of items on the deque
    public int size(){return size;}

    // add the item to the front
    public void addFirst(Item item){
        if (item == null) throw new IllegalArgumentException();
        if(isEmpty()) initialize(item);
        else{
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.prev = null;
            first.next = oldfirst;
            oldfirst.prev = first;
            size++;
        }
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null) throw new IllegalArgumentException();
        //****************************
        if(isEmpty()) initialize(item);
        else{
            Node oldlast = last;
            last = new Node();
            last.item = item;
            //*****************
            last.next = null;
            last.prev = oldlast;
            oldlast.next = last;
            //*****
            size++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException();
        Item remove = first.item;
        //***********************************
        if(size == 1) {
            first = null;
            last = null;
        }
        else{
            first = first.next;
            first.prev = null;
        }
        size--;
        return remove;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException();
        Item remove = last.item;
        //***********************************
        if(size == 1){
            first = null;
            last = null;
        }
        else{
            last = last.prev;
            last.next = null;
        }
        size--;
        return remove;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {return new DequeueIterator();}

    private class DequeueIterator implements Iterator <Item>{

        private Node current = first;

        // 判断集合中是否有元素，如果有元素可以迭代，就返回true
        public boolean hasNext()
        {return current != null;}

        //返回现在的数组，使指针指向下一个
        public Item next(){
            if(!hasNext()) throw new NoSuchElementException();
            Item ret = current.item;
            current = current.next;
            return ret;
        }
        public void remove()
        {throw new UnsupportedOperationException();}

    }
    // unit testing (required)
    public static void main(String[] args){

    }

}