//TODO: weak composition generator
///**
// * Weak composition of an integer n
// */
//package combinatorics.composition.weak;
//
//import combinatorics.CombinatoricsVector;
//import combinatorics.Generator;
//import combinatorics.Iterator;
//import combinatorics.util.Util;
//
///**
// * @author Dmytro.Paukov
// * 
// */
//public class WeakCompositionGenerator extends Generator<Integer> {
//
//	public static final int MAXN = 100;
//
//	protected final Integer _coreValue;
//	protected final Integer _length;
//
//	public WeakCompositionGenerator(Integer n, Integer length) {
//		super();
//		this._coreValue = n;
//		this._length = length;
//	}
//
//	/**
//	 * Returns value which is used to generate all partitions. This value
//	 * returned as a element of vector. Vector has length of 1
//	 */
//	@Override
//	public CombinatoricsVector<Integer> getCoreObject() {
//		return new CombinatoricsVector<Integer>(1, _coreValue);
//	}
//
//	@Override
//	public long getNumberOfGeneratedObjects() {
//		return Util.combination( _coreValue + _length - 1, _coreValue);
//	}
//
//	@Override
//	public Iterator<CombinatoricsVector<Integer>> createIterator() {
//		return new WeakCompositionIterator(this);
//	}
//
//}
