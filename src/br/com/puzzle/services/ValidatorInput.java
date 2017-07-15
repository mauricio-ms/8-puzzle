package br.com.puzzle.services;

import java.util.ArrayList;
import java.util.List;

public final class ValidatorInput {

	private ValidatorInput() {
	}

	public static void validate(final Integer[][] source) {
		validateSingleValues(source);
		
		final Integer[] flatSource = matrizToArr(source);
		final Integer numberOfInversions = GetNumberInversionsOfPuzzle.of(flatSource).get();
		if( numberOfInversions % 2 != 0 ) {
			throw new RuntimeException(
					"A entrada não pode ser solucionada pois possui um número de inversões ímpar");
		}
	}

	private static void validateSingleValues(final Integer[][] source) {
		final List<Integer> readed = new ArrayList<>();
		for (int i = 0; i < source.length; i++) {
			for (int j = 0; j < source.length; j++) {
				final Integer value = source[i][j];
				if (readed.contains(value) || value < 0 || value > 8) {
					throw new RuntimeException(
							"Entrada de dados inválida, deve conter valores de 0 a 8 sem repetições");
				} else {
					readed.add(value);
				}
			}
		}
	}
	
	private static Integer[] matrizToArr(Integer[][] matrix) {
		Integer newArray[] = new Integer[matrix.length*matrix[0].length];
	    for(int i = 0; i < matrix.length; i++) {
	    	Integer[] row = matrix[i];
	        for(int j = 0; j < row.length; j++) {
	            int number = matrix[i][j];
	            newArray[i*row.length+j] = number;
	        }
	    }
	    return newArray;
	}
}