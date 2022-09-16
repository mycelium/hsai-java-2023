@echo off
javac -d .\build .\*.java .\algorithms\*.java
cd build
jar -cfe ..\sorter.jar lesson1.homework.Main .\lesson1\homework\*.class .\lesson1\homework\algorithms\*.class
cd ..\
rd build /q/s