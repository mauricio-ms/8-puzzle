package br.com.puzzle.services;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public final class GetNumberInversionsOfPuzzle {

	private final Integer[] numbers;

	private GetNumberInversionsOfPuzzle(final Integer[] numbers) {
		this.numbers = numbers;
	}
	
	public static GetNumberInversionsOfPuzzle of(final Integer[] numbers) {
		return new GetNumberInversionsOfPuzzle(numbers);
	}

	public Integer get() {
		return IntStream.range(0, numbers.length).map(this::getBy).sum();
	}

	public Integer getBy(final Integer startIndex) {
		final IntPredicate filterGreaterZero = index -> numbers[index] > 0;
		final IntPredicate filterGreaterThanIndex = index -> numbers[startIndex] > numbers[index];
		final long numberInversions = IntStream.range(startIndex, numbers.length)
				.filter(filterGreaterZero.and(filterGreaterThanIndex)).count();
		return (int) numberInversions;
	}
}