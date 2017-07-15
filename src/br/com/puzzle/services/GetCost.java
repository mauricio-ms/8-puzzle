package br.com.puzzle.services;

public class GetCost {

	private final Integer source[][];

	private final Integer target[][];

	private GetCost(final Integer[][] source, final Integer[][] target) {
		this.source = source;
		this.target = target;
	}
	
	public static GetCost of(final Integer[][] source, final Integer[][] target) {
		return new GetCost(source, target);
	}

	public Integer get() {
		Integer count = 0;
		for (int i = 0; i < source.length; i++) {
			for (int j = 0; j < source.length; j++) {
				if (source[i][j] != null && source[i][j] != target[i][j]) {
					count++;
				}
			}
		}
		return count;
	}
}