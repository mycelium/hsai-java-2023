@echo off
cd src
javac -d ..\bin .\com\hsai\*.java .\com\hsai\strconverter\*.java
cd ..\bin
jar -cfe ..\Main.jar com/hsai/Main .\com\hsai\*.class .\com\hsai\strconverter\*.class
cd..
rmdir /s /q bin
::@pause