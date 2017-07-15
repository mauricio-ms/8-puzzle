package br.com.puzzle.factories;

import java.util.Optional;

import br.com.puzzle.model.Position;

public final class PositionFactory {

	private PositionFactory() {
	}
	
	public static Optional<Position> getBlank(final Integer[][] config) {
		for (int i = 0; i < config.length; i++) {
			for (int j = 0; j < config.length; j++) {
				if( config[i][j].equals(0) ) {
					return Optional.of(Position.of(i, j));
				}
			}
		}
		return Optional.empty();
	}
}