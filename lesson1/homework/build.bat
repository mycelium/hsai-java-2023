@echo off
javac -d .\bin .\src\com\hsai\*.java .\src\com\hsai\SortAlgs\*.java
cd bin
jar -cfe ..\Main.jar lesson1/homework/src/com/hsai/Main .\lesson1\homework\src\com\hsai\*.class .\lesson1\homework\src\com\hsai\SortAlgs\*.class
cd..
@pause