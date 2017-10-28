import java.util.Iterator;
import java.util.ListIterator;



public class Deque<Item> implements Iterable<Item> {
    /** Use DLList for this one
     * created by Wen Sun
     **/
    private int size;
    private Node first;
    private static class Node<Item>{
        Item item;
        Node next;
        public Node(Item i,Node n){
            item=i;
            next=n;
        }
    }
    public Deque(){    // construct an empty deque
        size=0;
        first=null;
    }

    public boolean isEmpty(){ // is the deque empty?
        return size==0;

    }
    public int size(){// return the number of items on the deque
        return size;

    }
    public void addFirst(Item item)  { // add the item to the front
        if(item==null)
            throw new java.lang.IllegalArgumentException();
        else{
            size = size+1;
            first=new Node(item,first);
        }
    }
    public void addLast(Item item)    {// add the item to the end
        if(item==null)
            throw new java.lang.IllegalArgumentException();
        else{
            size = size+1;
            if (first==null){
                first=new Node(item,null);
            }
            else{
                Node p=first;
                while(p.next!=null)
                    p=p.next;
                p.next=new Node(item,null);
            }


        }

    }
    public Item removeFirst(){ // remove and return the item from the front
        if(isEmpty())
            throw new java.util.NoSuchElementException();
        else{
            size = size-1;
            Item temp=(Item)first.item;
            first=first.next;
            return temp;
        }
    }
    public Item removeLast(){// remove and return the item from the end
        if(isEmpty())
            throw new java.util.NoSuchElementException();
        else{
            size=size-1;
            if  (first.next==null){
               Item temp=(Item)first.item ;
               first=null;
               return temp;
            }
            else{
                Node p=first;
                while(p.next.next!=null)
                    p=p.next;
                Item temp=(Item)p.next.item;
                p.next=null;
                return temp;
            }

        }
    }
    public Iterator<Item> iterator(){ // return an iterator over items in order from front to end
        return new Deque.ListIterator() {};
    }
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public Item next(){
            if (current==null)
                throw new java.util.NoSuchElementException();
            else{
                Item i  =  (Item)current.item;
                current =current.next;
                return i;
            }
        }
        public boolean hasNext(){return current != null; }
        public void remove(){throw new java.lang.UnsupportedOperationException();}
    }


    public static void main(String[] args){ // unit testing (optional)
        Deque<Integer> d = new Deque();
        System.out.println(d.isEmpty());
        System.out.println(d.size());

        d.addLast(2);
        d.addFirst(1);
        d.addLast(3);
        d.addLast(10);
        System.out.println(d.removeLast());
        System.out.println(d.removeLast());

        System.out.println(d.size());
        Iterator<Integer> i = d.iterator();
        System.out.println(i.next());
        System.out.println(i.next());
        System.out.println(i.next());
        System.out.println(i.next());
        System.out.println(i.hasNext());

    }

}

