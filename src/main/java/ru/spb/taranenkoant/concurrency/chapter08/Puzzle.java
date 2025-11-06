package ru.spb.taranenkoant.concurrency.chapter08;


import java.util.Set;

/**
 * {@code @author:} TaranenkoAnt
 * {@code @createDate:} 06.11.2025
 */
public interface Puzzle<P, M> {
    P initialPosition();
    boolean isGoal(P position);
    Set<M> legalMoves(P position);
    P move(P position, M move);
}
