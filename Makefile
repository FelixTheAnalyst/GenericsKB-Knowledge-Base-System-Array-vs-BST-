all:
	mkdir -p bin/
	javac -d bin/ src/*/*.java
	cat README.md 
doc: 
	mkdir -p doc/
	javadoc -d doc/ src/*/*.java 
clean:
	rm -rf bin/ doc/ src/*/*.class 
	rm -rf userdetails.txt *.log

run:
	java -cp bin app.GenericsKBApp