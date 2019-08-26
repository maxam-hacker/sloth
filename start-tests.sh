rm -rf \
    ./src/com/maxamhacker/sloth/*.class \
    ./src/com/maxamhacker/sloth/server/*.class \
    ./src/com/maxamhacker/sloth/http/*.class

rm sloth.jar

javac -d bin \
    -cp ./libs/junit-4.12.jar \
    ./src/com/maxamhacker/sloth/*.java \
    ./src/com/maxamhacker/sloth/server/*.java \
    ./src/com/maxamhacker/sloth/http/*.java

#chmod 777 \
#    ./src/com/maxamhacker/sloth/*.class \
#    ./src/com/maxamhacker/sloth/server/*.class \
#    ./src/com/maxamhacker/sloth/http/*.class

cd ./bin

jar \
    cfm ../sloth.jar ../MANIFEST.MF \
    ./com/maxamhacker/sloth/*.class \
    ./com/maxamhacker/sloth/server/*.class \
    ./com/maxamhacker/sloth/http/*.class

cd ..

java \
    -cp ./libs/junit-4.12.jar;\
    ./bin/com/maxamhacker/sloth/*.class;\
    ./bin/com/maxamhacker/sloth/server/*.class;\
    ./bin/com/maxamhacker/sloth/http/*.class \
    org.junit.runner.JUnitCore \
    com.maxamhacker.sloth.http.HttpProcessorTest