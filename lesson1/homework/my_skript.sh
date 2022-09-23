#!/bin/sh
rm -rf build
mkdir build
javac -d ./build src/com/hsai/Main.java src/com/hsai/IOUtils.java src/com/hsai/sorts/*.java
cd build
jar cfe ../sorter.jar lesson1/homework/src/com/hsai/Main lesson1/homework/src/com/hsai/Main.class lesson1/homework/src/com/hsai/sorts/*.class lesson1/homework/src/com/hsai/IOUtils.class

