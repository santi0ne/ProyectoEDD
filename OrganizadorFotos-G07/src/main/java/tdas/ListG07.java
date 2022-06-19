/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdas;

/**
 *
 * @author jeras
 * @param <E>
 */
public interface ListG07<E> {
    // Intenta insertar el elemento al inicio y retorna verdadero si lo logró hacer, falso sino
    boolean addFirst(E e);
    // Intenta insertar el elemento al final y retorna verdadero si lo logró hacer, falso sino
    boolean addLast(E e);
    // Retorna el primer elemento. Lanza una excepción si la lista está vacía.
    E getFirst();
    // Retorna el último elemento. Lanza una excepción si la lista está vacía.
    E getLast();
    // Retorna la posición de E si se encuentra en la Lista. Retorna -1 si no lo encuentra.
    int indexOf(E e);
    // Retorna la cantidad de elementos en la lista.
    int size();
    // Remueve el último elemento de la lista y retorna verdadero. Retorna falso si la lista estaba vacía antes de la remoción.
    boolean removeLast();
    // Remueve el primer elemento de la lista y retorna verdadero. Retorna falso si la lista estaba vacía antes de la remoción.
    boolean removeFirst();
    // Inserta el elemento en la posición indicada por el índice y retorna verdadero si lo logra realizar.
    //  Retorna falso si el elemento es null
    //  Lanza una excepción si el indice es invalido (fuera del rango)
    boolean insert(int index, E e);
    // Modifica el elemento en la posición indicada por el índice y retorna verdadero si lo logra realizar.
    //  Retorna falso si el elemento es null
    //  Lanza una excepción si el indice es invalido (fuera del rango)
    boolean set(int index, E e);
    // Retorna verdero si la lista está vacía y falso si contiene nodos.
    boolean isEmpty();
    // Recorre la lista para retornar el elemento en el índice indicado.
    //  Lanza una excepción si el indice es invalido (fuera del rango)
    E get(int index);
    // Retorna verdadero si el elemento se encuentra en la Lista y falso sino.
    //  También retorna falso si el elemento es null.
    boolean contains(E e);
    // Remueve el elemento en la posición indicada por el índice y retorna verdadero si lo logra realizar.
    //  Retorna falso si el elemento es null
    //  Lanza una excepción si el indice es invalido (fuera del rango)  
    boolean remove(int index);

    
}
