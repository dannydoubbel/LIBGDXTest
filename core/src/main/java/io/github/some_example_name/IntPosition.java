package io.github.some_example_name;


import java.util.Objects;

public class IntPosition {
    private int x;
    private int y;

    public IntPosition() {
        x = 0;
        y = 0;
    }

    public IntPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setPosition(IntPosition newPosition) {
        this.x = newPosition.getX();
        this.y = newPosition.getY();
    }

    public void addX(int deltaX) {
        this.x += deltaX;
    }

    public void addY(int deltaY) {
        this.y += deltaY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntPosition that = (IntPosition) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
