@echo off
java -jar Main.jar testASCII.txt 10 ASCII
echo.
java -jar Main.jar testU8.txt 16 UTF-8
echo.
java -jar Main.jar testU16.txt 16 UTF-16
echo.
java -jar Main.jar 16-hello-world.txt 16 UTF-8
echo.
java -jar Main.jar 10-hello-world.txt 10 ASCII
@pause