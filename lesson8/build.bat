@echo off
javac -d .\bin .\Main.java .\converters\*.java .\parsers\*.java
cd bin
jar -cfe ..\startProgram.jar lesson8/Main .\lesson8\Main.class .\lesson8\converters\*.class .\lesson8\parsers\*.class
cd..
rmdir /s /q bin
@pause