/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdas;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author jeras
 * @param <E>
 */
public class ArrayListG07<E> implements ListG07<E>,Iterable<E> {
    private E[] arreglo;
	private int capacidad;
	private int tam; // me indica la cantidad de elementos que está ocupando el arreglo

    public ArrayListG07() {
        capacidad = 10;
        arreglo = (E[]) new Object[capacidad];
        tam = 0;
    }
    
    @Override
    public Iterator<E> iterator() {
        
        Iterator<E> it=new Iterator<E>(){
            
            int i=0;
            E puntero= arreglo[i];
            
            @Override
            public boolean hasNext() {
                return i < arreglo.length && arreglo[i] != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {throw new NoSuchElementException();}
                
                return arreglo[i++];
 
            }
        
    };
        
        return it;
    }
        
        
    public boolean addFirst(E e){
        
        if(e==null){
            return false;
        }
        
        //la lista está vacia
        if (isEmpty()){
            arreglo[0] = e;
            tam = 1;
               return true;
        }

        //si el arreglo esta lleno o a capacidad
        if(tam+1==capacidad){
            crecerArreglo();
        }
        
        //la insercion debe desplazar elementos
        for(int i=tam; i>0;i--){
            arreglo[i] = arreglo[i-1];
        }
        arreglo[0]=e;
        return true;
        
    }
    
    private void crecerArreglo(){
        capacidad = capacidad*2;    
        E[] arreglo2 = (E[]) new Object[capacidad];
        
        for(int i=0; i<=tam;i++){
            arreglo2[i] = arreglo[i];
        }
        
        arreglo = arreglo2;
            
        }
    
    public boolean addLast(E e){
                if (e == null) {
            return false;
        } else if (isEmpty()) {
            arreglo[tam++] = e;
            return true;
        } else if (capacidad == tam) {
            crecerArreglo();
        }
        int index = tam;
        arreglo[index] = e;
        tam++;
        return true;
        
    }

    @Override
    public E getFirst() {
        if(isEmpty()){
            return null;
        }
        
        return arreglo[0];
    }

    @Override
    public E getLast() {
        if(isEmpty()){
            return null;
        }
        return arreglo[tam-1];
    }

    @Override
    public int indexOf(E e) {
        if(isEmpty()){
            return -1; // esta vcacio
        }
        
        for(int i=0 ; i<tam;i++){
            if(e.equals(arreglo[i])){
                return i;
            }        
        }
        return 0; // no existe el elemento

    }

    @Override
    // devuelve el valor de casillas ocupadas
    public int size() {
        return tam;
    }

    @Override
    public boolean removeLast() {
        if(isEmpty()){
            return false; // arreglo esta vacio
        }
        
        remove(tam-1);
        return true;
        
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()){
            return false; // arreglo esta vacio
        }
        
        remove(0);
        return true;
    }

    @Override
    // inserta el elemento en cualquier posicion del arreglo
    public boolean insert(int index, E e) {
        
        if(e==null){
            return false;
        }
        if(index<1 || index>capacidad){
            return false;
        }
        if(index>1 && index<(tam-1)){
            if (tam + 1 == capacidad) {
                crecerArreglo();
            }
            
            if (arreglo[index - 1] != null) {
                for (int i = tam; i >= index; i--) {
                    arreglo[i] = arreglo[i-1];
                }
                arreglo[index-1]=e;
                return true;
            }else if (arreglo[index - 1]== null){
                arreglo[index - 1] = e;
                return true;
            }

        }else if(index==1){
            addFirst(e);
            return true;
        }else if(index==(tam+1)){
            addLast(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean set(int index, E e){
        if(e==null){
            return false;
        }
         try{
             if (index >= 1 && index <= capacidad) {
                 arreglo[index - 1] = e;
                 return true;
              }
         }catch(ArrayIndexOutOfBoundsException exc){
             System.out.println("Índice fuera de los límites!");
         } 
        return false;
        
    }

    @Override
    public boolean isEmpty() {
        return tam==0;
    }

    
    
    @Override
    public E get(int index) {
        if ((index < 0) || (index > tam-1)) {
            throw new ArrayIndexOutOfBoundsException();
        }else{
            return this.arreglo[index];
        }

    }

    @Override
    public boolean contains(E e) {
        if(e==null){
            return false;
        }
        for(E tmp:arreglo){
            if(e.equals(tmp)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        if ((index < 0) || (index > tam - 1)) {
            return false;
        } else {
            if(arreglo[index]==null){
                return false;
            }
            for(int i=index;i<tam;i++){
                arreglo[i]=arreglo[i+1];
                
            }
            return true;
        }
        
        

    }
    

        @Override
    public String toString(){
         return arreglo.toString();
    }

    
        

}
