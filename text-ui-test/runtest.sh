# for mac!!!
#!/usr/bin/env bash

# create bin directory if it doesn't exist
mkdir -p ../bin

# delete output from previous run
rm -f ACTUAL.TXT EXPECTED-UNIX.TXT

# compile ALL Java files recursively
if ! javac -Xlint:none -d ../bin $(find ../src/main/java -name "*.java")
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program
java -classpath ../bin NUT.NUT < input.txt > ACTUAL.TXT

# normalise line endings
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT 2>/dev/null

# compare output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi