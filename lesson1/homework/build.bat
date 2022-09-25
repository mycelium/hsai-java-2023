@echo off
javac -d .\bin .\src\com\hsai\*.java .\src\com\hsai\sorting\*.java
cd bin
jar -cfe ..\sorter.jar lesson1/homework/src/com/hsai/MainSort .\lesson1\homework\src\com\hsai\*.class .\lesson1\homework\src\com\hsai\sorting\*.class
cd..
rmdir /s /q bin
