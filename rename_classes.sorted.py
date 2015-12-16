bigwordlist = []
with open('classes.sorted.txt') as inputfile:
    for line in inputfile:
        bigwordlist.append(line.strip().split(' '))

smallwordlist = []
with open('unique_tweet_words.txt') as inputfile:
    for line in inputfile:
        smallwordlist.append(line.strip())

with open('new.classes.sorted.txt','w') as file:
    for word in bigwordlist:
        if str(word[0]) in smallwordlist:
            print>>file, str(smallwordlist.index(word[0])) + ' ' + word[1]
        else:
            print>>file, '-1' + ' ' + word[1]
