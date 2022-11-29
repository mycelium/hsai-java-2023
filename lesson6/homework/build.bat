@echo off
cd src
javac -d ..\bin .\*.java .\Parser\*.java
cd ..\bin
jar -cfe ..\homework6.jar Main .\*.class .\Parser\*.class
cd..
rmdir /s /q bin
@pause