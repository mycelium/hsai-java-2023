@echo off
md bin
javac -d .\bin .\src\*.java
cd bin
jar -cfe ..\parseString.jar lesson5/homework/src/Main .\lesson5\homework\src\*.class
cd ..\
rd /s /q bin
@pause