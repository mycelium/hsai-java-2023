mkdir build
javac -d ./build lesson1/homework/src/com/hsai/Main.java lesson1/homework/src/com/hsai/IOUtils.java lesson1/homework/src/com/hsai/sorts/*.java
cd build || exit
# shellcheck disable=SC1001
jar cfe sorter.jar lesson1/homework/src/com/hsai/Main.class lesson1/homework/src/com/hsai/sorts/*.class lesson1/homework/src/com/hsai/IOUtils.class
cd ..
#rmdir /s /q build
rm -rf build