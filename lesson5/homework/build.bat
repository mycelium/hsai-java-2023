@echo off
javac -d .\bin .\Tester.java .\src\HashMap\*.java .\src\SearchStrategies\*.java
cd bin
jar -cfe ..\startProgram.jar lesson5/homework/Tester .\lesson5\homework\Tester.class .\lesson5\homework\src\HashMap\*.class .\lesson5\homework\src\SearchStrategies\*.class
cd..
rmdir /s /q bin
@pause