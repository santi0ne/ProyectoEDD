/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdas;

/**
 *
 * @author jeras
 */
public class SimpleLinkedListG07<E> implements ListG07<E>{
    
   private NodeListG07<E> header;
    private NodeListG07<E> last;

    public NodeListG07 getHeader() {
        return header;
    }

    public void setHeader(NodeListG07 header) {
        this.header = header;
    }

    public E getLast() {
        return last.getContent();
    }

    public void setLast(NodeListG07 last) {
        this.last = last;
    }
    
    
    @Override
    public int size(){
        int size = 0;
        NodeListG07<E> n;
        for(n = header; n!= null; n = n.getNext())
            size++;
        return size;  
    }
    
    @Override
    public boolean addFirst(E e){     
        if (e != null){
            NodeListG07<E> newNode = new NodeListG07<>(e);
            newNode.setNext(header);
            setHeader(newNode);
            return true;
        }
        else{
            return false;
        }
    }
    
    
    public NodeListG07<E> getPrevious (NodeListG07<E> node){
        NodeListG07<E> previous = null;
        NodeListG07<E> n;
        for(n = header; n!=node ; n = n.getNext()){
            previous = n;
        }
        return previous; 
    }
    


    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    public void clear() {
        
    }

    @Override
    public boolean addLast(E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E getFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int indexOf(E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(int index, E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean set(int index, E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(E e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
