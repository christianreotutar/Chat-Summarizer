package cs475;

import java.io.Serializable;

public class RegressionLabel extends Label implements Serializable {

	double label;
	public RegressionLabel(double label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return this.label + "";
	}

}
