package ooadj.chessmp.domain.piece;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position implements Comparable<Position> {
    public static final int BEGIN_X = 0;
    public static final int BEGIN_Y = 0;
    public static final int END_X = 8;
    public static final int END_Y = 8;
    private static final Map<String, Position> POSITIONS = new HashMap<>();

    private final int x;
    private final int y;

    static {
        for (int y = BEGIN_Y; y < END_Y; y++) {
            for (int x = BEGIN_X; x < END_X; x++) {
                POSITIONS.put(key(x, y), new Position(x, y));
            }
        }
    }

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(int x, int y) {
        return from(key(x, y));
    }

    public static Position from(String key) {
        Position position = POSITIONS.get(key.toLowerCase());
        if (position == null) {
            throw new IllegalArgumentException("Position out of range.");
        }
        return position;
    }

    public static String key(int x, int y) {
        return (char)('a' + x) + String.valueOf(1 + y);
    }

    public static Collection<Position> values() {
        return Collections.unmodifiableCollection(POSITIONS.values());
    }

    public Position add(int x, int y) {
        return of(this.x + x, this.y + y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equalsX(int x) {
        return this.x == x;
    }

    public boolean equalsY(int y) {
        return this.y == y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position)o;
        return x == position.x &&
            y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Position position) {
        if (y > position.y) {
            return -1;
        }
        if (y < position.y) {
            return 1;
        }
        return x - position.x;
    }
}
