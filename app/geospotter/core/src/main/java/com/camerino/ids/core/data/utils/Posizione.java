package com.camerino.ids.core.data.utils;

import jakarta.persistence.Entity;

public class Posizione {
    double x;
    double y;

    public Posizione(){}

    public Posizione(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
