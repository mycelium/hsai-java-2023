rm -rf build
mkdir build
javac -d ./build Main.java FileParsing.java
cd buildк
# shellcheck disable=SC1001
jar cfe ../stringTranslator.jar lesson6/homework/Main lesson6/homework/*.class