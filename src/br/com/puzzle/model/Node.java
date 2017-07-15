package br.com.puzzle.model;

public final class Node implements Comparable<Node> {
	
    private final Node parent;

    private final Integer puzzle[][];
 
    private final Position position;
 
    private final Integer cost;

    private final Integer level;

	public Node(Node parent, Integer[][] puzzle, Position position, Integer cost, Integer level) {
		this.parent = parent;
		this.puzzle = puzzle;
		this.position = position;
		this.cost = cost;
		this.level = level;
	}

	public Node getParent() {
		return parent;
	}
	
	public Integer[][] getPuzzle() {
		return puzzle;
	}

	public Position getPosition() {
		return position;
	}

	public Integer getCost() {
		return cost;
	}

	public Integer getLevel() {
		return level;
	}
	
	public Integer getSumOfCostAndLevel() {
		return cost + level;
	}

	@Override
	public int compareTo(Node other) {
		return this.getSumOfCostAndLevel().compareTo(other.getSumOfCostAndLevel());
	}

	@Override
	public String toString() {
		return "Node [cost=" + cost + ", level=" + level + "]";
	}
}