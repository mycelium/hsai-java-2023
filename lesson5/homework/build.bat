@echo off
md bin
javac -d .\bin .\src\*.java .\src\exeptions\*.java .\src\Test\*.java
cd bin
jar -cfe ..\parseString.jar lesson5/homework/src/Main .\lesson5\homework\src\*.class .\lesson5\homework\src\exeptions\*.class .\lesson5\homework\src\Test\*.class
cd ..\
rd /s /q bin
@pause