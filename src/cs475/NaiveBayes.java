package cs475;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class NaiveBayes extends Predictor {
	
	private int _numInstances;
	
	private Map<String, Integer> _labelsCount;
	private Set<String> _uniqueLabels;
	private int _numUniqueLabels;
	
	protected Map<String, SparseVector<Integer>> _vocabCount;
	protected Set<Integer> _uniqueVocab;
	private int _numUniqueVocab;
	
	private Map<String, Double> _probLabel;
	private Map<String, SparseVector<Integer>> _condProbWords;

	private int _smoothing;
	
	public NaiveBayes() {
		
		this._numInstances = 0;

		this._labelsCount = new HashMap<>();
		this._uniqueLabels = new HashSet<>();
		this._numUniqueLabels = 0;
		
		this._vocabCount = new HashMap<>();
		this._numUniqueVocab = 0;
		
		this._probLabel = new HashMap<String, Double>();
		this._condProbWords = new HashMap<>();
		
		this._smoothing = 0;
	}

	@Override
	public void train(List<Instance> instances) {
		
		this.preprocess(instances);
		
		// find probLabel
		for (String label: this._uniqueLabels) {
			double prob = (double) this._labelsCount.get(label) / (double) this._numInstances;
			this._probLabel.put(label, prob);
		}
		
		// find conditional probabilities
		for (String label: this._uniqueLabels) {
			SparseVector<Integer> sv = new SparseVector<>();
			double totalWordsForLabel = this._vocabCount.get(label).sizeOfValues();
			double denom = totalWordsForLabel + this._numUniqueVocab;
			//for (Entry<Integer, Double> e : this._vocabCount.get(label)) {
			for (Integer word : this._uniqueVocab) {	
				double wordCount = this._vocabCount.get(label).get(word);
				double closeWordCount = this.getCloseWordCount(label, word);
				double num = wordCount + this._smoothing;
				double prob = (double) (num / denom);
				sv.put(word, prob);
			}
			this._condProbWords.put(label, sv);
		}
	}

	@Override
	public Label predict(Instance instance) {
		
		double maxProb = -1;
		String maxLabel = "";
		
		// find prob for all labels
		for (String label : this._uniqueLabels) {
			double prob = this._probLabel.get(label);
			for (Entry<Integer, Integer> e : instance.getFeatureVector().getNonZeroFeatures()) {
				int word = e.getKey();
				int freq = e.getValue();
				double wordProb = this._condProbWords.get(label).get(word);
				prob *= wordProb;
				
			}
			
			if (prob > maxProb) {
				maxProb = prob;
				maxLabel = label;
			}
		}
		
		//System.out.println("actual: " + instance._label.toString() + "guess: " + maxLabel);
		return new ClassificationLabel(Integer.parseInt(maxLabel));
	}
	
	/**
	 * Count number of unique labels
	 */
	public void preprocess(List<Instance> instances) {

		this._numInstances = instances.size();
		
		this._vocabCount = this.countVocab(instances);
		this._uniqueLabels = new HashSet(this._vocabCount.keySet());
		this._numUniqueLabels = this._vocabCount.size();
	}

	/**
	 * Count number of unique vocabulary. NOTE: affects _numVocab variable, _labelsCount
	 */
	public Map<String, SparseVector<Integer>> countVocab(List<Instance> instances) {

		Set<Integer> uniqueVocab = new HashSet<>();
		Map<String, Integer> labelCount = new HashMap<String, Integer>();
		Map<String, SparseVector<Integer>> histogramVocab = new HashMap<>();
		for (Instance i : instances) {
			
			String label = i.getLabel().toString();
			SparseVector<Integer> histogramFreq;
			
			// set histogram freq
			if (histogramVocab.containsKey(label)) {
				histogramFreq = histogramVocab.get(label);
			} else {
				histogramFreq = new SparseVector<>();
			}

			// updatelabelCount 
			if (labelCount.containsKey(label)) {
				labelCount.put(label, labelCount.get(label) + 1);
			} else {
				labelCount.put(label, 1);
			}
			
			FeatureVector fv = i.getFeatureVector();
			for (Entry<Integer, Integer> e : fv.getNonZeroFeatures()) {
				int word = e.getKey();
				int freq = e.getValue();
				
				// update unique Vocab
				if (!uniqueVocab.contains(word)) {
					uniqueVocab.add(word);
				}
				
				// update frequency of words for label 
				if (histogramFreq.containsKey(word)) {
					histogramFreq.put(word, histogramFreq.get(word) + freq);
				} else {
					histogramFreq.put(word, freq);
				}	
			}
			
			histogramVocab.put(label, histogramFreq);
		}
		
		// update _numVocab
		this._uniqueVocab = uniqueVocab;
		this._numUniqueVocab = uniqueVocab.size();
		
		// update _labelCount
		this._labelsCount = labelCount;
		
		return histogramVocab;
	}
	
	public int getCloseWordCount(String label, int word) {
		return 0;
	}
	
}