@echo off
echo Ready for package.
cd ../../

setlocal
echo Set local environment variables.

echo Packaging ...
call mvn clean -U -Plocal package -Dmaven.test.skip=true

endlocal
echo Package is done.

pause