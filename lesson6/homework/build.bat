@echo off
cd src
javac -Xlint:unchecked -d ..\bin .\com\hsai\*.java  .\com\hsai\parser\*.java ^
.\com\hsai\parser\exceptions\*.java
cd ..\bin
jar -cfe ..\ParserApp.jar com/hsai/Main .\com\hsai\*.class ^
.\com\hsai\parser\*.class .\com\hsai\parser\exceptions\*.class
cd..
rmdir /s /q bin
::@pause
