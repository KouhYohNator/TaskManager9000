package com.insta.taskmanager9000.tools;

import java.io.Serializable;

/**
 * Une paire d'objets
 * (classe prise sur le site http://stackoverflow.com/)
 * (topic: What is the equivalent of the C++ Pair<L,R> in Java?)
 * @author utilisateur 'arturh'
 * @param <A> premier objet de la paire
 * @param <B> second objet de la paire
 */
public class Pair<A, B> implements Serializable{

	private static final long serialVersionUID = -8057909109093105283L;
	private A first;
    private B second;

    public Pair() {
        this.first = null;
        this.second = null;
}
    
    public Pair(A first, B second) {
            this.first = first;
            this.second = second;
    }

    public int hashCode() {
            int hashFirst = first != null ? first.hashCode() : 0;
            int hashSecond = second != null ? second.hashCode() : 0;

            return (hashFirst + hashSecond) * hashSecond + hashFirst;
    }

    public boolean equals(Object other) {
            if (other instanceof Pair) {
                    Pair<?, ?> otherPair = (Pair<?, ?>) other;
                    return 
                    ((  this.first == otherPair.first ||
                            ( this.first != null && otherPair.first != null &&
                              this.first.equals(otherPair.first))) &&
                     (        this.second == otherPair.second ||
                            ( this.second != null && otherPair.second != null &&
                              this.second.equals(otherPair.second))) );
            }

            return false;
    }

    public String toString()
    { 
           return "(" + first + ", " + second + ")"; 
    }

    public A getFirst() {
            return first;
    }

    public void setFirst(A first) {
            this.first = first;
    }

    public B getSecond() {
            return second;
    }

    public void setSecond(B second) {
            this.second = second;
    }
}