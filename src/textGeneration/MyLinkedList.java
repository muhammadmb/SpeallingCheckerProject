package textGeneration;

import java.util.AbstractCollection;
import java.util.AbstractList;

public class MyLinkedList <E> extends AbstractList <E> {

    
    Node<E> head;
    Node<E> tail;
    int size = 0;

    public MyLinkedList() {
    
        head = new Node<E>(null);
        tail = new Node<>(null);
        head.setNext(tail);
        tail.setNext(head);
        head.setPrevious(tail);
        tail.setNext(head);
        
    }
    
    public boolean add(E element ){
    
        if(element == null)
            throw new NullPointerException("cannot be null");
    
        new Node <E>(tail, element);
        size++;
        return true;
    }
    
    @Override
    public E get(int index) {
        
        return getNode(index).data;
    }
    
    public Node<E> getNode(int index){
        
        Node cur = head;
        for (int i = -1; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }
    
    public E set(int index, E element){
    
        Node <E> val = getNode(index);
        Node <E> newNode = new Node<E>(element);
        val.previous.setNext(newNode);
        val.next.setPrevious(newNode);
        
        return val.data;
    } 
    
    public E remove(int index){
        Node <E> re = getNode(index);
        if (re != null) {
            re.previous.setNext(re.next);
            re.next.setPrevious(re.previous);
            re.setNext(null);
            re.setPrevious(null);
        }
        return re.data;
    }

    @Override
    public int size() {
        return size;
    }
    public void add(int index, E element ){
        Node <E> cur = getNode(index);
        new Node <E> (cur,element);
        if(cur != null){
        size ++;}
    }
            
}
    class Node <E>{
        Node <E> next;
        Node <E> previous;
        E data;

    public Node( E data) {
        this.next = null;
        this.previous = null;
        this.data = data;
    }

    public Node(Node<E> next, E data) {
        this(data);
        this.setPrevious(next.previous);
        this.setNext(next);
        next.previous.setPrevious(this);
        next.setPrevious(this);
        
    }
    
    public void setNext(Node<E> next) {
        this.next = next;
    }

    public void setPrevious(Node<E> previous) {
        this.previous = previous;
    }
    
    
        
    }
