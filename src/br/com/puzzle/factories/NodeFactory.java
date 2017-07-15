package br.com.puzzle.factories;

import br.com.puzzle.model.Node;
import br.com.puzzle.model.Position;
import br.com.puzzle.services.GetCost;

public final class NodeFactory {

	private NodeFactory() {
	}

	public static Node createRoot(final Integer source[][], final Integer target[][]) {
		return new Node(null, source, PositionFactory.getBlank(source).orElse(null), GetCost.of(source, target).get(),
				0);
	}

	public static Node createFromNodeChangingPuzzleByPositionWithNewCost(final Node node, final Position positionToChange, final Integer[][] target) {
		final Integer[][] newPuzzle = copyPuzzle(node.getPuzzle());
		final Position position = node.getPosition();
		final Integer tempValue = newPuzzle[position.getX()][position.getY()];
		newPuzzle[position.getX()][position.getY()] = newPuzzle[positionToChange.getX()][positionToChange.getY()];
		newPuzzle[positionToChange.getX()][positionToChange.getY()] = tempValue;
		return new Node(node, newPuzzle, positionToChange, GetCost.of(newPuzzle, target).get(), node.getLevel() + 1);
	}

	private static Integer[][] copyPuzzle(final Integer[][] puzzle) {
		final Integer[][] newPuzzle = new Integer[puzzle.length][puzzle.length];
		for (int i = 0; i < puzzle.length; i++) {
			for (int j = 0; j < puzzle.length; j++) {
				newPuzzle[i][j] = puzzle[i][j];
			}
		}
		return newPuzzle;
	}
}