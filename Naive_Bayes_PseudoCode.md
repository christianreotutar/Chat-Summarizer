# Part 1

	ArrayList<Instance> instances := almost instances
	
	Set<String> hashtags := set of hashtags seen in /all/ tweets
	Set<String> words := set of words seen in /all/ tweets
	
	
	
	int countTags = 0
	int countWords = 0
	
	// tweet to pseudo training data
	for line L in training:
		let H = hashtag
		let W = list of words
	
		Tuples<Integer, List[Integer]> i;
	
		Hlabel = -1
		if ( H in hashtags ):
			Hlabel = hashtags.get(H)
		else:
			Hlabel = countTags
			H.put(Hlabel, countTags)
			countTags++
	
		numberedWords = []			
		for (w in W):
			wlabel = -1
			if (w in words):
				wlabel = words.get(w)
			else:
				wlabel = countWords
				words.put(wlabel, countWords)
				countWords++
			numberedWord.append(wlabel)
	
		// label
		i[0] = Hlabel
		i[1] = numberedWords

# Part 2

    StringBuilder sb := formatted training data
    
    // it is possible to merge this with part I but i wanted to make it more readable, up 2 u
    for (instance in instances):
      HashMap<Integer, Integer> histogram := map from wlabel to countOfwLabel
      words = instance[1]
      for (words in words):
      	if word in histogram: histogram[word] += 1
      	else histogram[word] = 1
    
      sb.append(instance[0]) // add label
      for (int i = 0; i < countWords (meaning totalWords); i++) {
      	sb.append(" " + histogram[i]); // if not in histogram, set to 0
      }
      sb.append("\n")
    sb.toString();
    write sb to file
