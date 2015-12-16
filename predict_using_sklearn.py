from sklearn import svm
import numpy as np

X = np.load('x.npy')
y = np.load('y.npy')

clf = svm.SVC(gamma=0.001, C=100)
clf.fit(X, y)

for z in range(len(hashtags)):
    answer = clf.predict([X[z][:]])
    print "Prediction: " + unique_hashtags[answer] + "\t\t  Actual hashtag: " + hashtags[z]
