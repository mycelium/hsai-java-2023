@echo off
javac -encoding UTF-8 -Xlint:unchecked -d .\bin .\*.java
cd bin
jar -cfe ..\stringParse.jar lesson6/homework/Main .\lesson6\homework\*.class .\lesson6\homework\Tests\*.txt
cd..
rmdir /s /q bin
@pause