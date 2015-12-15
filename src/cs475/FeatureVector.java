package cs475;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class FeatureVector implements Serializable {
	
	HashMap<Integer, Integer> data = new HashMap<>();

	/**
	 * Adds the feature tuple <index, value> to the list of features
	 * @param index The index of the feature
	 * @param value The value of the feature
	 */
	public void add(int index, int value) {
		
		if (value != 0) {
			data.put(index, value);
		}
		
	}
	
	/**
	 * Returns the feature value at specified index
	 * @param index Feature index to get
	 * @return Feature value of index
	 */
	public int get(int index) {
		
		if (data.containsKey(index)) {
			return data.get(index);
		}
		
		return 0;
	}
	
	public Set<Entry<Integer, Integer>> getNonZeroFeatures() {
		return this.data.entrySet();
	}

}
