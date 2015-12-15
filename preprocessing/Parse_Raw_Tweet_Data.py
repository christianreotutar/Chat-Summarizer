import json
import operator

input_var = input('Enter filename in quotes (example - "2015_11_21_23_06_18"): ')
print ("Parsing file " + input_var)

with open(input_var, "r") as data_file:
    x = 0
    hashtagcount={}
    for tweet in data_file:
        data = json.loads(tweet)
        if "text" in data.keys() and "retweeted_status" not in data.keys():
            if data["lang"] == "en" :
                for entity in data["entities"]["hashtags"]:
                    x += 1
                    # print data["text"]
                    hashtag = entity["text"]
                    if hashtag not in hashtagcount:
                        hashtagcount[hashtag] = 1
                    else:
                        hashtagcount[hashtag] += 1

print x
#sorted_x = sorted(hashtagcount.items(), key=operator.itemgetter(1))
#print sorted_x
