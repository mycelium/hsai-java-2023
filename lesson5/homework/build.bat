@echo off
cd src
javac -d ..\bin .\com\hsai\*.java .\com\hsai\mypoint\*.java ^
.\com\hsai\myhashtab\*.java .\com\hsai\myhashtab\strategies\*.java ^
.\com\hsai\myhashtab\exceptions\*.java 
cd ..\bin
jar -cfe ..\Main.jar com/hsai/Main .\com\hsai\*.class .\com\hsai\mypoint\*.class ^
.\com\hsai\myhashtab\*.class .\com\hsai\myhashtab\strategies\*.class ^
.\com\hsai\myhashtab\exceptions\*.class
cd..
rmdir /s /q bin
@pause