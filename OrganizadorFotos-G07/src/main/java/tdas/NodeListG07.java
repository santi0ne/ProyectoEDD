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
public class NodeListG07<E> {
      private E content;
    private NodeListG07<E> next; // se puede omitir pero se pondra como Object por lo que no es recomendable
    
    public NodeListG07(E content){
        this.content = content;
        this.next = null;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public NodeListG07<E> getNext() {
        return next;
    }

    public void setNext(NodeListG07<E> next) {
        this.next = next;
    }
    
}
