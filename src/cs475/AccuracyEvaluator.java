package cs475;

import java.util.List;

public class AccuracyEvaluator extends Evaluator {

	@Override
	public double evaluate(List<Instance> instances, Predictor predictor) {
		
		int correct = 0;
		
		for (Instance i : instances) {
			Label predicted = predictor.predict(i);
			Label actual = i.getLabel();
			
			if (predicted.equals(actual)) {
				correct += 1;
			}
		}
		
		int total = instances.size();
		double accuracy = (double)correct / total;		
		
		return accuracy;
	}

}
