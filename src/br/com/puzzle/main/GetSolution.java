package br.com.puzzle.main;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import br.com.puzzle.factories.NodeFactory;
import br.com.puzzle.model.Directions;
import br.com.puzzle.model.Node;
import br.com.puzzle.model.Position;
import br.com.puzzle.services.PrintRoot;
import br.com.puzzle.services.ValidatorInput;

public final class GetSolution {

	private final Integer maxMovimentations;

	private final Integer source[][];

	private final Integer target[][];

	public GetSolution(final Integer maxMovimentations, final Integer[][] source) {
		this.maxMovimentations = maxMovimentations;
		this.source = source;
		this.target = new Integer[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
	}

	public void solve() {
		ValidatorInput.validate(source);
		Integer countAttempt = 1;
		final Comparator<Node> byLevel = (Node o1, Node o2) -> o1.getLevel().compareTo(o2.getLevel());
		final Comparator<Node> byCost = (Node o1, Node o2) -> o1.getCost().compareTo(o2.getCost());
		final PriorityQueue<Node> priorityQueue = new PriorityQueue<>(byLevel.thenComparing(byCost));
		final Node root = NodeFactory.createRoot(source, target);
		priorityQueue.add(root);

		while (!priorityQueue.isEmpty()) {

			final Node min = priorityQueue.poll();
			if (min.getLevel().equals(maxMovimentations)) {
				System.out.printf("Não foi possível encontrar uma solução em %s etapas\n", maxMovimentations);
				return;
			}

			if (Integer.valueOf(0).equals(min.getCost())) {
				PrintRoot.printBy(min);
				System.out.printf("Solução encontrada em %s etapas\n", min.getLevel());
				return;
			}

			for (int i = 0; i < 4; i++) {
				final Integer xPlusRowDirection = min.getPosition().getX() + Directions.ROWS[i];
				final Integer yPlusColDirection = min.getPosition().getY() + Directions.COLS[i];
				if (isSafe(xPlusRowDirection, yPlusColDirection)) {
					final Node child = NodeFactory.createFromNodeChangingPuzzleByPositionWithNewCost(min,
							Position.of(xPlusRowDirection, yPlusColDirection), target);
					priorityQueue.add(child);
					countAttempt++;
				}
			}
		}
	}

	private Boolean isSafe(final Integer x, final Integer y) {
		return x >= 0 && x < 3 && y >= 0 && y < 3;
	}

	public static void main(String[] args) {

		try (final Scanner keyboard = new Scanner(System.in)) {
			System.out.print("Digite o número máximo de tentativas para encontrar a solução: ");
			final Integer maxMovimentations = keyboard.nextInt();
			System.out.println("Insira a configuração inicial:");
			final Integer source[][] = new Integer[3][3];
			for (int i = 0; i < source.length; i++) {
				for (int j = 0; j < source.length; j++) {
					System.out.printf("[%d][%d]: ", i, j);
					source[i][j] = keyboard.nextInt();
				}
			}
			new GetSolution(maxMovimentations, source).solve();
		}
	}
}