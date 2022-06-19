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
public class DoublyNodeListG07<E> {
 private E content;
    private DoublyNodeListG07<E> next;
    private DoublyNodeListG07<E> previous;
   
    public DoublyNodeListG07(E content){
        this.content = content;
        next = null;

    }
    
    public DoublyNodeListG07(){
        content = null;
        next = null;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public DoublyNodeListG07<E> getNext() {
        return next;
    }

    public void setNext(DoublyNodeListG07<E> next) {
        this.next = next;
    }
    
     public DoublyNodeListG07<E> getPrevious() {
        return previous;
    }

    public void setPrevious(DoublyNodeListG07<E> previous) {
        this.previous = previous;
    }
  
    
       
}
