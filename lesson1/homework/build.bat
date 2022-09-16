@echo off
javac -d .\bin .\src\com\hsai\*.java .\src\com\hsai\auxclasses\*.java .\src\com\hsai\sortalgs\*.java
cd bin
jar -cfe ..\SortApp.jar com/hsai/SortApp .\com\hsai\*.class .\com\hsai\auxclasses\*.class .\com\hsai\sortalgs\*.class
cd..
rmdir /s /q bin
@pause