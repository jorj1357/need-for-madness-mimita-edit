@echo off
setlocal

for /f %%i in ('powershell -NoProfile -Command "Get-Date -Format yyyyMMdd_HHmmss"') do set VERSION=%%i

set BUILD_DIR=build
set RELEASE_DIR=releases\%VERSION%

echo Building version %VERSION%

if not exist %BUILD_DIR% mkdir %BUILD_DIR%
if not exist releases mkdir releases
if not exist %RELEASE_DIR% mkdir %RELEASE_DIR%

del /s /q %BUILD_DIR%\* >nul 2>&1

javac -encoding UTF-8 -d %BUILD_DIR% clean2\*.java

if errorlevel 1 (
    echo.
    echo COMPILE FAILED
    pause
    exit /b
)

jar cfm %RELEASE_DIR%\NFM_%VERSION%.jar manifest.txt -C %BUILD_DIR% .

echo.
echo BUILD SUCCESS
echo.
echo Output:
echo %RELEASE_DIR%\NFM_%VERSION%.jar
echo.

pause