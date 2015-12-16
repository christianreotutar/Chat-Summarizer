package cs475;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class SparseVector<K extends Serializable> implements Serializable, Iterable<Entry<K, Double>> {
	
	private HashMap<K, Double> data = new HashMap<>();
	private double denom = 1;

	/**
	 * Adds the feature tuple <index, value> to the list of features
	 * @param index The index of the feature
	 * @param value The value of the feature
	 */
	public void put(K index, double value) {
		
		if (value != 0) {
			data.put(index, value);
		}
		
	}
	
	/**
	 * Returns the feature value at specified index
	 * @param index Feature index to get
	 * @return Feature value of index
	 */
	public double get(K index) {
		
		if (data.containsKey(index)) {
			return (double) data.get(index);
		}
		
		return 0.0;
	}
	
	public Set<Entry<K, Double>> getNonZeroFeatures() {
		return this.data.entrySet();
	}
	
	public String toString() {
		return this.data.toString();
	}
	
	public boolean containsKey(K key) {
		return this.data.containsKey(key);
	}

	@Override
	public Iterator<Entry<K, Double>> iterator() {
		return this.data.entrySet().iterator();
	}
	
	public int size() {
		return this.data.size();
	}
	
	public double sizeOfValues() {
		double sum = 0;
		for (Entry<K, Double> e: this.getNonZeroFeatures()) {
			sum += e.getValue(); 
		}
		return sum;
	}
	
	/*
	 * Very hacky, I know
	 */
	public double getDenom() {
		return this.denom;
	}
	
	public void setDenom(double in_denom) {
		this.denom = in_denom;
	}

}
