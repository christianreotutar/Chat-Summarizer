from sklearn import svm
import numpy as np
import json, operator, re, string
# input_var = input('Enter filename in quotes (example - "2015_11_18_21_12_30"): ')
# print ("Parsing file " + input_var)

tweets = []
hashtags = []
regex = re.compile('[%s]' % re.escape(string.punctuation))
count = 0;
with open('2015_11_18_21_12_30', "r") as data_file:
    # Go through each line
    for tweet in data_file:
        count = count + 1
        if count % 10000 == 0:
            print count
        # Parse it as json
        data = json.loads(tweet)
        # Filter out deleted tweets and retweets
        if "text" in data.keys() and "retweeted_status" not in data.keys():
            # Filter out non-english tweets and non-hashtagged tweets
            if data["lang"] == "en" and data['entities']['hashtags']:
                word = data['text']
                word = word.lower()
                #Remove urls
                word = re.sub('((www\.[^\s]+)|(https?://[^\s]+))','',word)
                #Remove @usernames
                word = re.sub('@[^\s]+','',word)
                #Remove hashtags (from body)
                word = re.sub(r'#([^\s]+)', '', word)
                #Remove punctuation
                word = regex.sub('', word)
                #Remove additional white spaces
                word = re.sub('[\s]+', ' ', word)
                #Remove white space at end or beginning
                word = word.strip()
                # For each hashtag
                for entity in data["entities"]["hashtags"]:
                    hashtag = entity['text']
                    hashtag = hashtag.lower()
                    hashtags.append(hashtag)
                    tweets.append(word)

'''
We now have two arrays, tweets and hashtags
Tweets goes from 1 to x, each value is the body of a tweet (a sentence)
Hashtags goes from 1 to x, each value is the hashtag string
x is the total number of hashtags in all of the tweets
'''

print "done adding tweets to lists"

# Array of unique hashtags as the labels
unique_hashtags = set(hashtags)
unique_hashtags = list(unique_hashtags)

# Array of all words used in the body of the tweets
all_words = []
for tweet in tweets:
    for word in tweet.split():
        all_words.append(word)

# Array of unique words as the features
unique_tweet_words = set(all_words)
unique_tweet_words = list(unique_tweet_words)

# Create the dataset
# Data looks like this: label feature:value feature:value ... feature:value
#   Label = index of hashtag in the unique_hashtags array
#   Feature = index of word in the unique_tweet_words array
#   Value = number of the word's occurences in the tweet
dataset = []

# Add index of hashtag to the tweet
for hashtag in hashtags:
    dataset.append(str(unique_hashtags.index(hashtag)) + ' ')

print "building the input file"

# Add index of unique word onto the data
for x in range(0, len(tweets)):
    index_to_value = {}
    for word in tweets[x].split():
        index = str(unique_tweet_words.index(word))
        if not index in index_to_value:
            index_to_value[index] = 1
        else:
            index_to_value[index] += 1
    for k, v in index_to_value.items():
        dataset[x] += str(k) + ':' + str(v) + ' '

print "adding data to text files"

with open('file.txt','w') as file:
    for item in dataset:
        print>>file, item

with open('unique_hashtags.txt', 'w') as file:
    for item in unique_hashtags:
        print>>file, item.encode('utf-8')

with open('unique_tweet_words.txt', 'w') as file:
    for item in unique_tweet_words:
        print>>file, item.encode('utf-8')
