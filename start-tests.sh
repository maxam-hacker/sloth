rm -rf \
    ./src/com/maxamhacker/sloth/*.class \
    ./src/com/maxamhacker/sloth/server/*.class \
    ./src/com/maxamhacker/sloth/http/*.class

javac \
    -cp ./libs/junit-4.12.jar \
    ./src/com/maxamhacker/sloth/*.java \
    ./src/com/maxamhacker/sloth/server/*.java \
    ./src/com/maxamhacker/sloth/http/*.java

#chmod 777 \
#    ./src/com/maxamhacker/sloth/*.class \
#    ./src/com/maxamhacker/sloth/server/*.class \
#    ./src/com/maxamhacker/sloth/http/*.class

jar \
    cf sloth.jar \
    ./src/com/maxamhacker/sloth/*.class \
    ./src/com/maxamhacker/sloth/server/*.class \
    ./src/com/maxamhacker/sloth/http/*.class

java \
    -cp ./libs/junit-4.12.jar;\
    ./src/com/maxamhacker/sloth/*.class;\
    ./src/com/maxamhacker/sloth/server/*.class;\
    ./src/com/maxamhacker/sloth/http/*.class \
    org.junit.runner.JUnitCore com.maxamhacker.sloth.http.HttpProcessorTest