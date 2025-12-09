@echo off
REM PC Sale POS System - Simple Build and Run Batch Script

echo ==================================
echo PC Sale POS System - Build Script
echo ==================================
echo.

REM Create bin directory if not exists
if not exist "bin" mkdir bin

REM Check for lib directory
if not exist "lib" (
    echo ERROR: lib directory not found!
    echo Please create lib folder and add MySQL JDBC driver.
    pause
    exit /b 1
)

REM Compile
echo Compiling Java files...
echo.
javac -d bin -cp "lib\*" src\com\pcsale\model\*.java src\com\pcsale\dao\*.java src\com\pcsale\util\*.java src\com\pcsale\gui\*.java

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR: Compilation failed!
    pause
    exit /b 1
)

echo.
echo Compilation successful!
echo.

REM Ask to run
set /p run="Run the application now? (y/n): "
if /i "%run%"=="y" (
    echo.
    echo Starting PC Sale POS System...
    echo.
    java -cp "bin;lib\*" com.pcsale.gui.LoginFrame
)

echo.
echo ==================================
echo Build script completed!
echo ==================================
pause
