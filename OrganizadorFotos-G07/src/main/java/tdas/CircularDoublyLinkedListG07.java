/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 *
 * @author jeras
 * @param <E>
 */
public class CircularDoublyLinkedListG07<E> implements ListG07<E>,Iterable<E> {
    private Node<E> tail = null;
    private int size = 0;

    public CircularDoublyLinkedListG07() {
    }

    @Override
    public ListIterator<E> iterator() {
        
        ListIterator<E> it=new ListIterator<E>(){
            
            private Node<E> p=tail.getNext();
            private int puntero=0;
           
            @Override
            public boolean hasNext() {
               return p != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {throw new NoSuchElementException();}
                
                puntero++;
                p=p.next;
                return p.element;
            }

            @Override
            public boolean hasPrevious() {
                return p!=null;
            }

            @Override
            public E previous() {
                E tmp=p.getElement();
                p=p.getPrevious();
                return tmp;            
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void set(E e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
          
                
       return it;
    }

/////////////////////////////////  N  O  D  E  /////////////////////////////////
    private static class Node<E> {

        private E element;
        private Node<E> previous;
        private Node<E> next;

        public Node(E e, Node p, Node n) {
            this.element = e;
            this.previous = p;
            this.next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
    
    
    //Métodos interfaz Lista

    @Override
    public E getFirst() {
        if (isEmpty()) {
            return null;
        }
        return tail.getNext().getElement();
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    @Override
    public boolean insert(int index, E element) {
        if (element == null || index < 0 || index > size) {
            return false;
        }
        if (index == 0) {
            addFirst(element);
            return true;
        }
        if (index == size) {
            addLast(element);
            return true;
        }
        int mitad = size / 2;

        Node<E> newest = new Node<>(element, null, null);
        /*  
            index >= mitad
               
            Significa que se insertará el elemento en el lado más 
            cercano al tail por izquierda -> recorrer con los previous.
        
            Caso contrario el índice estaría más próximo por la derecha 
            del tail -> recorrer usando los next.
         */
        if (index >= mitad) {
            int finIndex = size - 1;

            for (Node<E> t = tail; finIndex >= mitad; t = t.getPrevious()) {
                if (finIndex == index) {
                    newest.setNext(t);
                    newest.setPrevious(t.getPrevious());
                    t.setPrevious(newest);
                    newest.getPrevious().setNext(newest);
                    size++;
                    return true;
                }
                finIndex--;
            }

        } else {
            int finIndex = 1;
            for (Node<E> t = tail.getNext(); finIndex < mitad; t = t.getNext()) {
                if (finIndex == index) {
                    newest.setNext(t.next);
                    newest.setPrevious(t.getNext().getPrevious());
                    t.setNext(newest);
                    t.getNext().getNext().setPrevious(newest);
                    size++;
                    return true;
                }
                finIndex++;
            }
        }
        return false;
    }

    @Override
    public boolean set(int index, E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    @Override
    public int size() {
        return size;
    }

//////////////////////////////// CONOCER ESTADO ////////////////////////////////
    public boolean isEmpty() {
        return size == 0;
    }

/////////////////////////////// AÑADIR Y REMOVER ///////////////////////////////
    @Override
    public boolean addFirst(E e) {
        if (isEmpty()) {
            Node<E> first = new Node(e, null, null);
            first.setPrevious(first);
            first.setNext(first);
            tail = first;
        } else {
            Node<E> first = new Node(e, tail, tail.getNext());
            first.setPrevious(tail);
            first.setNext(tail.getNext());
            tail.getNext().setPrevious(first);
            tail.setNext(first);
        }
        size++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if (isEmpty()) {
            Node<E> last = new Node(e, null, null);
            last.setPrevious(last);
            last.setNext(last);
            tail = last;
        } else {
            Node<E> last = new Node(e, tail, tail.getNext());
            tail.getNext().setPrevious(last);
            tail.setNext(last);
            tail = last;
        }
        size++;
        return true;
    }

    

    public boolean removeFirst() {
        if (isEmpty()) {
            return false;
        }
        E e;
        if (size == 1) {
            e = tail.getElement();
            tail = null;
        } else {
            e = tail.getNext().getElement();
            tail.getNext().setPrevious(null);
            tail.getNext().getNext().setPrevious(tail);
            Node<E> temp = tail.getNext().getNext();
            tail.getNext().setNext(null);
            tail.setNext(temp);
        }
        size--;
        return true;
    }

    @Override
    public boolean removeLast() {
        if (isEmpty()) {
            return false ;
        }
        E e;
        if (size == 1) {
            e = tail.getElement();
            tail = null;
        } else {
            e = tail.getElement();
            tail.getPrevious().setNext(tail.getNext());
            tail.getNext().setPrevious(tail.getPrevious());
            tail = tail.getPrevious();
        }
        size--;
        return true;
    }

    @Override
    public boolean remove(int i) {
        if (i < 0 || i >= size) {
            return false;
        }
        if (i == 0) {
            return removeFirst();
        }
        if (i == size - 1) {
            return removeLast();
        }
        E e = null;
        int mitad = size / 2;
        if (i >= mitad) {
            int finIndex = size - 1;
            for (Node<E> t = tail; finIndex >= mitad; t = t.getPrevious()) {
                if (finIndex == i) {
                    e = t.getElement();
                    t.getPrevious().setNext(t.getNext());
                    t.getNext().setPrevious(t.getPrevious());
                    t.setNext(null);
                    t.setPrevious(null);
                    size--;
                    break;
                }
                finIndex--;
            }
        } else {
            int finIndex = 0;
            for (Node<E> t = tail.getNext(); finIndex < mitad; t = t.getNext()) {
                if (finIndex == i) {
                    e = t.getElement();
                    t.getPrevious().setNext(t.getNext());
                    t.getNext().setPrevious(t.getPrevious());
                    t.setNext(null);
                    t.setPrevious(null);
                    size--;
                    break;
                }
                finIndex++;
            }
        }
        return true;
    }

    /////////////////////////////////// BÚSQUEDA ///////////////////////////////////
    @Override
    public int indexOf(E e) {
        Node<E> temp = tail.getNext();
        int c = 0;
        while (c < size) {
            if (temp.getElement().equals(e)) {
                return c;
            }
            temp = temp.getNext();
            c++;
        }
        return -1;
    }

    public boolean contains(E e) {
        return !(indexOf(e) == -1);
    }

    @Override
    public E get(int i) {
        if (i < 0 || i >= size) {
            return null;
        }
        if (i == 0) {
            return getFirst();
        }
        if (i == size - 1) {
            return getLast();
        }
        E e = null;
        int mitad = size / 2;
        if (i >= mitad) {
            int finIndex = size - 1;
            for (Node<E> t = tail; finIndex >= mitad; t = t.getPrevious()) {
                if (finIndex == i) {
                    e = t.getElement();
                    return e;
                }
                finIndex--;
            }
        } else {
            int finIndex = 0;
            for (Node<E> t = tail.getNext(); finIndex < mitad; t = t.getNext()) {
                if (finIndex == i) {
                    e = t.getElement();
                    return e;
                }
                finIndex++;
            }
        }
        return e;
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < size - 1; i++) {
            result += get(i).toString() + ", ";
        }
        result += get(size - 1).toString() + "]";
        return result;
    }

    public boolean empujarElementos(int n) {
        if (n <= 0) {
            return false;
        }
        if (this.isEmpty()) {
            return false;
        }
        Node<E> current = tail;
         for(int i = 1; i <= n; i++){
             tail=tail.next;
         }   
        return true;
    }
}
