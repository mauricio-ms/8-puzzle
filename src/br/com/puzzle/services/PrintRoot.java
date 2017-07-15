package br.com.puzzle.services;

import br.com.puzzle.model.Node;

public final class PrintRoot {

	private PrintRoot() {
	}

	public static void printBy(Node node) {
		if (node.getParent() != null) {
			printBy(node.getParent());
			printNode(node);
			System.out.println();
		}
	}

	private static void printNode(final Node node) {
		final Integer[][] puzzle = node.getPuzzle();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.printf("%d ", puzzle[i][j]);
			}
			System.out.println();
		}
	}
}