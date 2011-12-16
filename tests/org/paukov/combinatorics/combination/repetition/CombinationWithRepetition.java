package org.paukov.combinatorics.combination.repetition;

import org.junit.Test;

public class CombinationWithRepetition {

	@Test
	public void simpleCombinationWithRepetitionTest() {

		int N = 4; // Types to choose from
		int K = 3; // Number chosen

		int[] V = new int[K];

		for (int i = 0; i < V.length; i++)
			V[i] = 0;

		int k = V.length - 1;

		int count = 0;

		boolean end = false;

		while (end == false) {

			// print V;
			for (int i = 0; i < K; i++)
				System.out.print(V[i] + " ");
			System.out.println();

			V[k]++;
			if (V[k] > N - 1) {
				int index = -1;
				for (int i = 1; i <= V.length; i++) {
					if (k - i >= 0) {
						if (V[k - i] < N - 1) {
							index = k - i;
							break;
						}
					}
				}

				if (index != -1) {
					V[index]++;

					for (int j = 1; j < V.length - index; j++) {
						V[index + j] = V[index];
					}

				} else {
					end = true;
				}

			}

			count++;
		}

		System.out.println("count=" + count);
	}

}
