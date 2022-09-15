@echo off
md bin
javac -d .\bin .\src\com\hsai\*.java .\src\com\hsai\algorithms\*.java
cd bin
jar -cfe ..\sorter.jar lesson1/homework/src/com/hsai/Main .\lesson1\homework\src\com\hsai\*.class .\lesson1\homework\src\com\hsai\algorithms\*.class
cd ..\
rd /s /q bin
