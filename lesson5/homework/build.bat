@echo off
cd src
javac -d ..\bin .\com\hsai\*.java  .\com\hsai\point\*.java ^
.\com\hsai\hashmap\*.java .\com\hsai\hashmap\exceptions\*.java
cd ..\bin
jar -cfe ..\HashMapTest.jar com/hsai/Test .\com\hsai\*.class ^
.\com\hsai\point\*.class .\com\hsai\hashmap\*.class .\com\hsai\hashmap\exceptions\*.class
cd..
rmdir /s /q bin
::@pause