package org.paukov.combinatorics.composition.weak;
//TODO: weak composition iterator
//package org.paukov.combinatorics.composition.weak;
//
//import java.util.List;
//
//import org.paukov.combinatorics.CombinatoricsVector;
//import org.paukov.combinatorics.Iterator;
//import org.paukov.combinatorics.combination.simple.SimpleCombinationGenerator;
//
//public class WeakCompositionIterator extends
//		Iterator<CombinatoricsVector<Integer>> {
//
//	protected final static Integer ZERO = 0;
//	protected final static Integer ONE = 1;
//
//	/**
//	 * Generator
//	 */
//	protected final WeakCompositionGenerator _generator;
//
//	/**
//	 * Current weak composition
//	 */
//	protected CombinatoricsVector<Integer> _currentComposition = null;
//
//	/**
//	 * Current index of the weak composition
//	 */
//	protected long _currentIndex = 0;
//
//	protected final SimpleCombinationGenerator<Integer> _combinationGenerator;
//	protected final Iterator<CombinatoricsVector<Integer>> _combinationIterator;
//	protected CombinatoricsVector<Integer> _currentCombination = null;
//
//	public WeakCompositionIterator(WeakCompositionGenerator _generator) {
//		super();
//		this._generator = _generator;
//
//		CombinatoricsVector<Integer> coreSet = new CombinatoricsVector<Integer>();
//
//		for (int i = 0; i < this._generator._coreValue; i++)
//			coreSet.addValue(ONE);
//
//		for (int i = 0; i < this._generator._length - 1; i++)
//			coreSet.addValue(ZERO);
//
//		this._combinationGenerator = new SimpleCombinationGenerator<Integer>(
//				coreSet, this._generator._coreValue + this._generator._length - 1);
//
//		this._combinationIterator = this._combinationGenerator.createIterator();
//	}
//
//	@Override
//	public CombinatoricsVector<Integer> next() {
//		_currentIndex++;
//		_currentCombination = this._combinationIterator.next();
//		return getCurrentItem();
//	}
//
//	@Override
//	public boolean isDone() {
//		return !this._combinationIterator.hasNext();
//	}
//
//	@Override
//	public CombinatoricsVector<Integer> getCurrentItem() {
//
//		_currentComposition = new CombinatoricsVector<Integer>();
//
//		List<Integer> vector = _currentCombination.getVector();
//		java.util.Iterator<Integer> itr = vector.iterator();
//
//		int indexCompositionElement = 0;
//		int valueCompositionElement = 0;
//
//		while (itr.hasNext()) {
//			Integer currentCombinationElement = itr.next();
//			if (currentCombinationElement == ONE) {
//				valueCompositionElement++;
//			} else if (currentCombinationElement == ZERO) {
//				_currentComposition.setValue(indexCompositionElement,
//						valueCompositionElement);
//				indexCompositionElement++;
//				valueCompositionElement = 0;
//			} else {
//				// error
//			}
//		}
//		_currentComposition.setValue(indexCompositionElement,
//				valueCompositionElement);
//
//		return _currentComposition;
//	}
//	
//	/**
//	 * Returns partition as a string
//	 * 
//	 * @see java.lang.Object#toString()
//	 */
//	@Override
//	public String toString() {
//		return "WeakCompositionIterator=[#" + _currentIndex + ", "
//				+ _currentComposition + "]" + "comb=[" + _currentCombination +"]";
//	}
//
//}
