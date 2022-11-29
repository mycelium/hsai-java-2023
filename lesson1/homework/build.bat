@echo off
javac -d .\bin .\src\com\hsai\*.java .\src\com\hsai\additional\*.java .\src\com\hsai\algorithms\*.java
cd bin
jar -cfe ..\SortApp.jar com/hsai/SortApp .\com\hsai\*.class .\com\hsai\additional\*.class .\com\hsai\algorithms\*.class
cd..
rmdir /s /q bin