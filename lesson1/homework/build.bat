javac -d .\bin .\src\com\hsai\*.java .\src\com\hsai\sort\*.java
cd bin
jar -cfe ..\sorter.jar lesson1/homework/src/com/hsai/Main .\lesson1\homework\src\com\hsai\*.class .\lesson1\homework\src\com\hsai\sort\*.class
cd..
rmdir /s /q bin
cmd /k