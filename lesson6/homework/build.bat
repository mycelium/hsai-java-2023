@echo off
javac -encoding UTF8 -d .\bin .\Main.java .\src\Decoder.java
cd bin
jar -cfe ..\stringTranslator.jar lesson6/homework/Main .\lesson6\homework\Main.class .\lesson6\homework\src\Decoder.class
cd..
rmdir /s /q bin