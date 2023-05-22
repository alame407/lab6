package com.alame.lab6.common.data;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Coordinates implements Comparable<Coordinates>, Serializable {
    private final Long x; //Поле не может быть null
    private final float y;
    public Coordinates(Long x, float y){
        this.x = x;
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public Long getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.y, y) == 0 && x.equals(that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "" + x +
                ", " + y +
                '}';
    }

    @Override
    public int compareTo(Coordinates o) {
        return Comparator.comparing(Coordinates::getX).thenComparing(Coordinates::getY).compare(this, o);
    }
}
