JAVAC = javac
JAVA = java
ZIP = zip

DIR = "cs475/"

rwildcard=$(wildcard $1$2) $(foreach d,$(wildcard $1*),$(call rwildcard,$d/,$2))

SRC_FILES := $(wildcard cs475/*.java)
EXCEPTION_FILES1 = cs475/BernoulliLikelihoodTest.java
EXCEPTION_FILES2 = cs475/ClassifyTest.java
SRC_FILES := $(filter-out $(EXCEPTION_FILES1), $(SRC_FILES))
SRC_FILES := $(filter-out $(EXCEPTION_FILES2), $(SRC_FILES))
DATA_DIR=./data
DATA_FILES := $(call rwildcard,./data/,*)

MAIN_CLASS = Classify

default: $(MAIN_CLASS)

$(MAIN_CLASS): 
	$(JAVAC) $(SRC_FILES)

zip: 
	$(ZIP) library.zip $(SRC_FILES) $(DATA_FILES)
#	$(ZIP) library.zip $(SRC_FILES) ./Homework\ 1\ Data/* ./Homework\ 1\ Data/bio/* ./Homework\ 1\ Data/finance/* ./Homework\ 1\ Data/nlp/* ./Homework\ 1\ Data/speech/* ./Homework\ 1\ Data/synthetic/* ./Homework\ 1\ Data/vision/*

run:
	./run.sh

clean:
	rm -rf cs475/*.class
