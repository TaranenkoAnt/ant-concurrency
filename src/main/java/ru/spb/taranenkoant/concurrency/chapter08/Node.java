package ru.spb.taranenkoant.concurrency.chapter08;


import java.util.LinkedList;
import java.util.List;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 06.11.2025
 */
public class Node<P, M> {
    final P pos;
    final M move;
    final Node<P, M> prev;
    public Node<P, M> next;
    public Object key;
    public Object value;

    public Node(P pos, M move, Node<P, M> prev) {
        this.pos = pos;
        this.move = move;
        this.prev = prev;
    }
    

    public Node(P pos, M move, Node<P, M> prev, Node<P, M> next) {
        this.pos = pos;
        this.move = move;
        this.prev = prev;
        this.next = next;
    }

    List<M> asMoveList() {
        List<M> solution = new LinkedList<>();
        for (Node<P, M> n = this; n.move != null; n = n.prev) {
            solution.add(0, n.move);
        }
        return solution;
    }
}
