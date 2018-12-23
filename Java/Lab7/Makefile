#---------------------
#   Samer Baslan
#   sbaslan
#   CMPS 12B
#   08/17/2017
#   Makefile for Lab 7
#---------------------

JAVAC		= javac
MAINCLASS       = DictionaryClient
JAVASRC		= $(wildcard *.java)
SOURCES		= $(JAVASRC) Makefile README
CLASSES		= $(patsubst %.java, %.class, $(JAVASRC))
JARCLASSES	= $(patsubst %.class, %*.class, $(CLASSES))
JARFILE		= $(MAINCLASS)


all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(JARCLASSES)
	chmod +x $(JARFILE)
	rm Manifest

%.class: %.java
	$(JAVAC) $<

clean:
	rm -f *.class $(JARFILE)
