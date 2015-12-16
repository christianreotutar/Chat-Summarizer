package cs475;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class AccuracyEvaluator extends Evaluator {
	
	private static Map<Integer, String> _wordTable;
	private static Map<Integer, String> _labelTable;
	
	public AccuracyEvaluator() throws FileNotFoundException {
		super();
		
		this._wordTable = new HashMap<>();
		this._labelTable = new HashMap<>();
		this.importWordTable();
		this.importLabelTable();
	}

	@Override
	public double evaluate(List<Instance> instances, Predictor predictor) {
		
		int correct = 0;
		int count = 0;
		
		for (Instance i : instances) {
			Label predicted = predictor.predict(i);
			Label actual = i.getLabel();
			
			if (predicted.equals(actual)) {
				correct += 1;
			}
			
//			System.out.print("pred: [" + predicted.toString() + ":" + this._labelTable.get(Integer.parseInt(predicted.toString())) 
//			+ "] real: [" + actual + ":" + this._labelTable.get(Integer.parseInt(actual.toString()))
//			+ "] words: [");
//			
//			for (Entry<Integer, Integer> e : i.getFeatureVector().getNonZeroFeatures()) {
//				int word = e.getKey();
//				int freq = e.getValue();
//				System.out.print(this._wordTable.get(word) + "=" + freq + " ");
//			}
//			System.out.println("]");
//			count ++;
//			if (count == 5) {
//				System.exit(0);
//			}
		}
		
		int total = instances.size();
		double accuracy = (double)correct / total;		
		
		return accuracy;
	}
	
	public void importWordTable() throws FileNotFoundException {
		String filepath = "./src/data/twitter_big/twitter_big_unique_tweet_words.txt";
		Scanner scan = new Scanner(new BufferedInputStream(new FileInputStream(filepath)));
		
		int count = 0;
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (line.trim().length() == 0)
				   continue;
			
			this._wordTable.put(count, line.trim());
			count ++;
		}
		
		scan.close();
	}
	
	public void importLabelTable() throws FileNotFoundException {
		String filepath = "./src/data/twitter_big/twitter_big_unique_hashtags.txt";
		Scanner scan = new Scanner(new BufferedInputStream(new FileInputStream(filepath)));

		int count = 0;
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (line.trim().length() == 0)
				   continue;

			this._labelTable.put(count, line.trim());
			count ++;
		}
		
		scan.close();
		
	}
	
}
