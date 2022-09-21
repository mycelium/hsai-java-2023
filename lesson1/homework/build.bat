@echo off
javac -d .\bin .\src\com\hsai\*.java .\src\com\hsai\auxclasses\*.java .\src\com\hsai\sortalgs\*.java
cd bin
jar -cfe ..\SortApp.jar lesson1/homework/src/com/hsai/SortApp .\lesson1\homework\src\com\hsai\*.class .\lesson1\homework\src\com\hsai\auxclasses\*.class .\lesson1\homework\src\com\hsai\sortalgs\*.class
cd..
rmdir /s /q bin
@pause
