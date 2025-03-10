@echo off
echo We assume that sourceanalyzer is in your local path
echo If it is not, update your path to include SCA.
echo
echo Cleaning up...
sourceanalyzer -b riches -clean
call ant clean

echo Translating...
call ant all
sourceanalyzer -b riches -cp "jsplibs/**/*.jar;lib/**/*.jar;WEB-INF/lib/**/*.jar" -java-build-dir "WEB-INF/classes" WEB-INF/ js/ jsplibs/ login/ pages/ config/

echo Scanning...
sourceanalyzer -b riches -scan -scan-policy classic -f scanResults.fpr
