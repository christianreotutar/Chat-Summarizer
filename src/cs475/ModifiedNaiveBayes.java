package cs475;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class ModifiedNaiveBayes extends NaiveBayes {
	
	private Map<Integer, Set<Integer>> _clusterWords;
	private Map<Integer, Integer> _wordClusters;

	public ModifiedNaiveBayes() throws FileNotFoundException {
		super();
		
		this._clusterWords = new HashMap<>();
		this._wordClusters = new HashMap<>();
		this.importWordClusters();
		
		for (Entry<Integer, Set<Integer>> e : this._clusterWords.entrySet()) {
			System.out.println(e.getValue().size());
		}
	}
	
	private void importWordClusters() throws FileNotFoundException {
		Scanner scan = new Scanner(new BufferedInputStream(new FileInputStream("./word2vec/new.classes.sorted.txt")));
		
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			if (line.trim().length() == 0)
				   continue;
			
			String[] split_line = line.split(" ");

			int word = Integer.parseInt(split_line[0]);
			if (word == -1) {
				continue;
			}
			
			int cluster = Integer.parseInt(split_line[1]);
			
			Set<Integer> words;
			if (this._clusterWords.containsKey(cluster)) {
				words = this._clusterWords.get(cluster);
			} else {
				words = new HashSet<Integer>();
			}
			
			words.add(word);
			this._clusterWords.put(cluster, words);
			this._wordClusters.put(word, cluster);
		}
		
		scan.close();
	}
	
	@Override
	public int getCloseWordCount(String label, int in_word) {
		if (!this._wordClusters.containsKey(in_word)) {
			return 0;
		}
		
		int count = 0;
		int cluster = this._wordClusters.get(in_word);
		for (Integer w : this._clusterWords.get(cluster)) {
			count += this._vocabCount.get(label).get(w);
		}
		
		return count;
	}
	
}
