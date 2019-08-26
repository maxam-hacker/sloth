echo "Removing files (if needed)..."

rm -rf \
    ./src/com/maxamhacker/sloth/*.class \
    ./src/com/maxamhacker/sloth/server/*.class \
    ./src/com/maxamhacker/sloth/http/*.class

rm sloth.jar

echo "Compiling..."
javac -d bin \
    -cp ./libs/junit-4.12.jar \
    ./src/com/maxamhacker/sloth/*.java \
    ./src/com/maxamhacker/sloth/server/*.java \
    ./src/com/maxamhacker/sloth/http/*.java

cd ./bin

echo "Jar processing..."
jar \
    cfm ../sloth.jar ../MANIFEST.MF \
    ./com/maxamhacker/sloth/*.class \
    ./com/maxamhacker/sloth/server/*.class \
    ./com/maxamhacker/sloth/http/*.class

cd ..

echo "Testing..."
java \
    -cp ./libs/junit-4.12.jar;\
    ./bin/com/maxamhacker/sloth/http/*;\
    ./bin/com/maxamhacker/sloth/server/*;\
    ./bin/com/maxamhacker/sloth/* \
    org.junit.runner.JUnitCore \
    com.maxamhacker.sloth.http.HttpProcessorTest
