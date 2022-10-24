@echo off
javac -d .\bin .\src\Main.java .\src\HashTable\*.java .\src\Interfaces\*.java
cd bin
jar -cfe ..\app.jar lesson5/homework/src/Main .\lesson5\homework\src\Main.class .\lesson5\homework\src\HashTable\*.class .\lesson5\homework\src\Interfaces\*.class
cd..
rmdir /s /q bin