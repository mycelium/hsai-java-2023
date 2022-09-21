#!/bin/bash
javac -d ./bin ./src/com/hsai/*.java ./src/com/hsai/SortAlgs/*.java
cd bin
jar -cfe ../SortApp.jar lesson1/homework/src/com/hsai/main ./lesson1/homework/src/com/hsai/*.class ./lesson1/homework/src/com/hsai/SortAlgs/*.class
cd ..
rm -rf ./bin
