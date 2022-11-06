@echo off
md bin
javac -d .\bin .\StringParser\src\*.java
cd bin
jar -cfe ..\StringParser.jar lesson6/homework/StringParser/src/Main .\lesson6\homework\StringParser\src\*.class
cd ..\
rd /s /q bin
@pause