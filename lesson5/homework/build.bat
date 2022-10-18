@echo off
cd src
javac -d ..\bin .\com\hsai\*.java  .\com\hsai\mypoint\*.java ^
.\com\hsai\myhashmap\*.java .\com\hsai\myhashmap\interfaces\*.java ^
.\com\hsai\myhashmap\exceptions\*.java ^
.\com\hsai\myfileio\*.java .\com\hsai\mypair\*.java
cd ..\bin
jar -cfe ..\HashMapTestApp.jar com/hsai/HashMapTestClass .\com\hsai\*.class  .\com\hsai\mypoint\*.class ^
.\com\hsai\myhashmap\*.class .\com\hsai\myhashmap\interfaces\*.class ^
.\com\hsai\myhashmap\exceptions\*.class ^
.\com\hsai\myfileio\*.class .\com\hsai\mypair\*.class
cd..
rmdir /s /q bin
@pause