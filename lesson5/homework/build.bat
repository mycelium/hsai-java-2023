@echo off
javac -d .\bin .\src\Main.java .\src\MyHashMap\*.java .\src\Interfaces\*.java
cd bin
jar -cfe ..\app.jar lesson5/homework/src/Main .\lesson5\homework\src\Main.class .\lesson5\homework\src\MyHashMap\*.class .\lesson5\homework\src\Interfaces\*.class
cd..
rmdir /s /q bin