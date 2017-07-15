package br.com.puzzle.model;

import java.util.Objects;

public final class Position {

	private final Integer x;
	
	private final Integer y;

	private Position(final Integer x, final Integer y) {
		this.x = x;
		this.y = y;
	}
	
	public static Position of(final Integer x, final Integer y) {
		Objects.requireNonNull(x, "x doesn't should be null");
		Objects.requireNonNull(y, "y doesn't should be null");
		return new Position(x, y);
	}
	
	public Integer getX() {
		return x;
	}
	
	public Integer getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}
}