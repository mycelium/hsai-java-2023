@echo off
java -jar ParserApp.jar test1.txt 8 ASCII
echo.
java -jar ParserApp.jar test2.txt 16 UTF-8
echo.
java -jar ParserApp.jar test3.txt 10 UTF-16
echo.
@pause
