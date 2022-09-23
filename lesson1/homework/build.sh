rm -rf build
mkdir build
javac -d ./build /Users/nnnn/IdeaProjects/hsai-java-2023/lesson1/homework/src/com/hsai/Main.java /Users/nnnn/IdeaProjects/hsai-java-2023/lesson1/homework/src/com/hsai/IOUtils.java /Users/nnnn/IdeaProjects/hsai-java-2023/lesson1/homework/src/com/hsai/sorts/*.java
cd build || exit
# shellcheck disable=SC1001
jar cfe sorter.jar lesson1/homework/src/com/hsai/Main lesson1/homework/src/com/hsai/sorts/*.class lesson1/homework/src/com/hsai/Main.class lesson1/homework/src/com/hsai/IOUtils.class
cd ..
#rm -rf build