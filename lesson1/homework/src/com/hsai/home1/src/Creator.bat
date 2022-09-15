@echo off
md bin
javac -encoding UTF8 -d .\bin App.java .\alghorithm\*.java .\inOut\*.java
cd bin
jar -cfe ..\final.jar App .\alghorithm\*.class .\inOut\*.class
cd ..\
rd /s /q bin
