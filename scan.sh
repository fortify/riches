#!/bin/sh
echo We assume that sourceanalyzer is in your local path
echo If it is not, update your path to include SCA.
echo ANT should also be installed on your local machine.


echo Cleaning up...
sourceanalyzer -b riches -clean
ant clean

echo Translating...
ant all
sourceanalyzer -b riches -cp "jsplibs/**/*.jar;lib/**/*.jar;WEB-INF/lib/**/*.jar" -java-build-dir "WEB-INF/classes" WEB-INF/ js/ jsplibs/ login/ pages/ config/

echo Scanning...
sourceanalyzer -b riches -scan -scan-policy classic -f scanResults.fpr
