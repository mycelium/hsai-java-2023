@echo off
cd src
javac -d ..\bin .\*.java .\utils\*.java
cd ..\bin
jar -cfe ..\StringParserApp.jar Main .\*.class .\utils\*.class
cd..
rmdir /s /q bin
@pause