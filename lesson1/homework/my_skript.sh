mkdir build
javac -d ./build /Users/polinaglezova/IdeaProjects/hsai-java-2023/lesson1/homework/src/com/hsai/Main.java /Users/polinaglezova/IdeaProjects/hsai-java-2023/lesson1/homework/src/com/hsai/IOUtils.java /Users/polinaglezova/IdeaProjects/hsai-java-2023/lesson1/homework/src/com/hsai/sorts/*.java
cd build || exit
jar cfe sorter.jar /Users/polinaglezova/IdeaProjects/hsai-java-2023/lesson1/homework/src/com/hsai/main.class /Users/polinaglezova/IdeaProjects/hsai-java-2023/lesson1/homework/src/com/hsai/sorts/*.class /Users/polinaglezova/IdeaProjects/hsai-java-2023/lesson1/homework/src/com/hsai/IOUtils.class
cd ..
rm -rf build

