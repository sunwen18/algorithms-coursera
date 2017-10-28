import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

/** Use AList for this one
 * created by Wen Sun
 **/
public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] items;
    public RandomizedQueue(){ // construct an empty randomized queue
        size=0;
        items= (Item []) new Object[2];
    }
    public boolean isEmpty()  {// is the randomized queue empty?
        return size==0;
    }
    public int size(){// return the number of items on the randomized queue
        return size;
    }

    public void enqueue(Item item){ // add the item
        if(item==null)
            throw new java.lang.IllegalArgumentException();
        else{
            this.resizing();
            items[size]=item;
            size=size+1;
        }
    }
    public Item dequeue(){ // remove and return a random item
        if(size==0)
            throw new java.util.NoSuchElementException();
        else{
            StdRandom.shuffle(items,0, size);
            Item temp=items[size-1];
            items[size-1]=null;
            size=size-1;
            return temp;
        }
    }
    public Item sample(){// return a random item (but do not remove it)

        if(size==0)
            throw new java.util.NoSuchElementException();
        else{
            int temp=StdRandom.uniform(size);
            return items[temp];
        }

    }
    private void resizing(){
        if (size==items.length){
            Item [] newitems= (Item []) new Object[size*2];
            System.arraycopy(items,0,newitems,0,size);
            items=newitems;
        }
    }
    public Iterator<Item> iterator() {// return an independent iterator over items in random order
        return new RandomizedQueue.ArrayIterator(){};
    }
    private class ArrayIterator implements Iterator<Item>{
        private int i=size;
        private int[] order= StdRandom.permutation(i);
        public boolean hasNext(){ return i>0 ;}
        public void remove(){throw new java.lang.UnsupportedOperationException();}
        public Item next(){
            if (i==0)
                throw new java.util.NoSuchElementException();
            else{
                i=i-1;
                return items[order[i]];
            }
        }
    }
    public static void main(String[] args){// unit testing (optional)
        RandomizedQueue<String> r = new RandomizedQueue();
        System.out.println(r.isEmpty());
        System.out.println(r.size());
        r.sample();
        r.dequeue();
        r.enqueue("you");
        r.enqueue("are");
        r.enqueue("beautiful");
        System.out.println(r.dequeue());

        System.out.println(r.size());
        Iterator<String> i = r.iterator();
        System.out.println(i.next());
        System.out.println(i.next());
        System.out.println(i.next());
        System.out.println(i.hasNext());
    }
}
