@echo off
javac -d .\build .\*.java .\algorithms\*.java
jar -cfe sorter.jar .\build\lesson1\homework\src\com\hsai\*.class .\build\lesson1\homework\src\com\hsai\algorithms\*.class
