@echo off
java -jar Parser.jar myTest.txt 10 SASCII
java -jar Parser.jar myTest.txt 17 ASCII
java -jar Parser.jar 10-hello-world.txt 10 UTF-8
java -jar Parser.jar 16-hello-world.txt 16 UTF-8
java -jar Parser.jar myTest.txt 10 ASCII
@pause
