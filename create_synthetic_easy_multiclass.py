'''
Inputs:
    L, a learner (training algorithm for binary classifiers)
    samples X
    labels y where yi E {1,... K} is the label for the sample Xi

Output:
    a list of classifiers fk for k E {1, ..., K}

Procedure:
    For each k in {1, ..., K}:
    Construct a new label vector yi = 1 where yi = k, 0 (or -1) elsewhere
        Apply L to X, y to obtain fk
'''

#Transform data:
import random

def transformDataForClass(label):
    dataset = []
    for x in range(0, 1000):
        if(label == 0):
            randlabel = str(random.randint(0, 2))
            if randlabel == '0':
                rand5 = str(random.randint(5, 7))
                uselabel = '1'
            elif randlabel == '1':
                rand5 = str(random.randint(8, 10))
                uselabel = '0'
            else:
                rand5 = str(random.randint(11, 13))
                uselabel = '0'

        if(label == 1):
            randlabel = str(random.randint(0, 2))
            if randlabel == '1':
                rand5 = str(random.randint(5, 7))
                uselabel = '0'
            elif randlabel == '1':
                rand5 = str(random.randint(8, 10))
                uselabel = '1'
            else:
                rand5 = str(random.randint(11, 13))
                uselabel = '0'

        if(label == 2):
            randlabel = str(random.randint(0, 2))
            if randlabel == '1':
                rand5 = str(random.randint(5, 7))
                uselabel = '0'
            elif randlabel == '1':
                rand5 = str(random.randint(8, 10))
                uselabel = '0'
            else:
                rand5 = str(random.randint(11, 13))
                uselabel = '1'

        dataset.append( \
            uselabel + \
            ' 1:' + "%.6f" % random.random() + \
            ' 2:' + "%.6f" % random.random() + \
            ' 3:' + "%.6f" % random.random() + \
            ' 4:' + "%.6f" % random.random() + \
            ' ' + rand5 + ':1')

    with open('neweasy.train','w') as file:
        for item in dataset:
            print>>file, item

transformDataForClass(0)
