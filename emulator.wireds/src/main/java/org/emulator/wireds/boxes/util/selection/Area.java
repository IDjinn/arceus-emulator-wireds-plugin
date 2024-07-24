package org.emulator.wireds.boxes.util.selection;

import utils.pathfinder.Position;

import java.util.Objects;

public class Area {
    private final Position pointA;
    private final Position pointB;
    private boolean invert;

    public Area(final Position pointA, final Position pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }
    
    public Area(final Position pointA, final Position pointB, final boolean invert) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.invert = invert;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.pointA.hashCode(), this.pointB.hashCode());
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof final Area other) {
            return this.pointA.equals(other.pointA) && this.pointB.equals(other.pointB);
        }
        return false;
    }

    public Position getPointA() {
        return this.pointA;
    }
    
    public Position getPointB() {
        return this.pointB;
    }
    
    public boolean isInverted() {
        return this.invert;
    }
    
    public void setInverted(final boolean invert) {
        this.invert = invert;
    }

    public boolean isItInside(final Position position) {
        final var insideX =
                position.getX() >= this.getPointA().getX() && position.getX() <= this.getPointB().getX();
        final var insideY = position.getY() >= this.getPointA().getY() && position.getY() <= this.getPointB().getY();

        return this.isInverted() != (insideX && insideY);
    }
}
