@echo off
javac -d .\bin .\src\com\hsai\Main.java  .\src\com\hsai\parser\Parser.java 
cd bin
jar -cfe ..\Parser.jar com/hsai/Main .\com\hsai\*.class .\com\hsai\parser\*.class
cd..
rmdir /s /q bin
@pause