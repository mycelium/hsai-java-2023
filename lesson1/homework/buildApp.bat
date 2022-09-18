@echo off
javac -d .\bin .\src\com\hsai\*.java .\src\com\hsai\SortingMethods\*.java
cd bin
jar -cfe ..\sorter.jar lesson1/homework/src/com/hsai/Main .\lesson1\homework\src\com\hsai\*.class .\lesson1\homework\src\com\hsai\SortingMethods\*.class
cd..
rmdir /s /q bin