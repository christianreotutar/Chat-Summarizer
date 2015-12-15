package cs475;

import java.io.Serializable;

public class ClassificationLabel extends Label implements Serializable {

	int label;
	public ClassificationLabel(int label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return this.label + "";
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.label;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ClassificationLabel)) {
			return false;
		}
		
		if (obj == this) {
			return true;
		}
		
		ClassificationLabel other = (ClassificationLabel) obj;
		if (this.label == other.label) {
			return true;
		}
		
		return false;
	}

}