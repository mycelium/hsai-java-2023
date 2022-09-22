@echo off
javac -d .\bin .\src\com\hsai\*.java .\src\com\hsai\utils\*.java .\src\com\hsai\sortclasses\*.java
cd bin
jar -cfe ..\Homework1.jar lesson1/homework/src/com/hsai/Main .\lesson1\homework\src\com\hsai\*.class .\lesson1\homework\src\com\hsai\utils\*.class .\lesson1\homework\src\com\hsai\sortclasses\*.class
cd..
rmdir /s /q bin
@pause