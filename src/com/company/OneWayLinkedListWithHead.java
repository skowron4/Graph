package com.company;

import java.util.AbstractList;
import java.util.Iterator;

public class OneWayLinkedListWithHead<E> extends AbstractList<E> {
    private class Element{
        private E value;
        private Element next;

        public E getValue(){
            return value;
        }

        public void setValue(E value){
            this.value = value;
        }

        public Element getNext(){
            return next;
        }

        public void setNext(Element next){
            this.next = next;
        }

        Element(E data){
            this.value = data;
        }
    }

    Element head = null;

    public OneWayLinkedListWithHead(){}

    public boolean isEmpty(){
        return head == null;
    }

    public void clear(){
        head = null;
    }

    public int size(){
        int pos = 0;
        Element actElem = head;

        while (actElem != null){
            pos++;
            actElem = actElem.getNext();
        }
        return pos;
    }

    private Element getElement(int index){
        if (index < 0) throw new IndexOutOfBoundsException();
        Element actElem = head;

        while (index > 0 && actElem != null){
            index--;
            actElem = actElem.getNext();
        }
        if(actElem == null){
            throw new IndexOutOfBoundsException();
        }
        return actElem;
    }

    public boolean add(E e){
        Element newElem = new Element(e);
        if (head == null){
            head = newElem;
            return true;
        }
        Element tail = head;
        while (tail.getNext() != null){
            tail = tail.getNext();
        }
        tail.setNext(newElem);
        return true;
    }


    public void add(int index, E data){
        if (index < 0) throw new IndexOutOfBoundsException();
        Element newElem = new Element(data);
        if(index == 0){
            newElem.setNext(head);
            head = newElem;
        }
        if (index > 0) {
            Element actElem = getElement(index - 1);
            newElem.setNext(actElem.getNext());
            actElem.setNext(newElem);
        }
    }


    public int indexOf(Object data){
        int pos = 0;
        Element actElem = head;
        while (actElem != null){
            if (actElem.getValue().equals(data)){
                return pos;
            }
            pos++;
            actElem = actElem.getNext();
        }
        return -1;
    }

    public boolean contains(Object data){
        return indexOf(data) >= 0;
    }

    public E get(int index){
        Element actElem = getElement(index);
        return actElem.getValue();
    }

    public E set(int index, E data){
        Element actElem = getElement(index);
        E elemData = actElem.getValue();
        actElem.setValue(data);
        return elemData;
    }

    public E remove(int index){
        if(index < 0 || head == null){
            throw new IndexOutOfBoundsException();
        }
        if (index == 0){
            E retValue = head.getValue();
            head = head.getNext();
            return retValue;
        }
        Element actElem = getElement(index-1);
        if(actElem.getNext() == null){
            throw new IndexOutOfBoundsException();
        }

        E retValue = actElem.getNext().getValue();
        actElem.setNext(actElem.getNext().getNext());
        return retValue;
    }

    public boolean remove(Object value){
        if (head == null){
            return false;
        }
        if (head.getValue().equals(value)){
            head = head.getNext();
            return true;
        }
        Element actElem = head;
        while (actElem.getNext() != null && !actElem.getNext().getValue().equals(value)){
            actElem = actElem.getNext();
        }
        if(actElem.getNext() == null){
            return false;
        }
        actElem.setNext(actElem.getNext().getNext());
        return true;
    }

    private class InnerIterator implements Iterator<E>{
        Element actElem;
        public InnerIterator(){
            actElem = head;
        }

        public boolean hasNext(){
            return actElem != null;
        }

        public E next(){
            E value = actElem.getValue();
            actElem = actElem.getNext();
            return value;
        }
    }

    public Iterator<E> iterator(){
        return new InnerIterator();
    }


}
