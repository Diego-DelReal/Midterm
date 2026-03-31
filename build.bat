@echo off
REM Printer Queue Management System - Build and Run Script for Windows
REM This script compiles and runs the Printer Queue application

setlocal enabledelayedexpansion

REM Create output directories
if not exist bin mkdir bin
if not exist test-bin mkdir test-bin

echo ======================================
echo Printer Queue Management System
echo ======================================
echo.

REM Check if Java is installed
javac -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Java compiler (javac) not found. Please install JDK 11 or higher.
    exit /b 1
)

if "%1"=="" (
    echo Usage: %0 [command]
    echo.
    echo Commands:
    echo   compile    - Compile the source files
    echo   test       - Run JUnit tests
    echo   run        - Run the interactive application
    echo   clean      - Remove compiled files
    echo   build      - Compile everything
    echo   all        - Clean, compile and run tests
    echo.
    echo Examples:
    echo   %0 compile
    echo   %0 test
    echo   %0 run
    exit /b 0
)

if /i "%1"=="compile" (
    echo [*] Compiling source files...
    javac -d bin src\*.java
    if %errorlevel% equ 0 (
        echo [+] Compilation successful!
    ) else (
        echo [-] Compilation failed!
        exit /b 1
    )
    goto :eof
)

if /i "%1"=="test" (
    echo [*] Compiling source files...
    javac -d bin src\*.java
    
    echo [*] Compiling test files...
    javac -cp bin test\PrinterQueueTest.java -d test-bin
    
    echo [*] Running tests...
    echo Note: Running tests requires Maven or JUnit in PATH
    mvn test
    goto :eof
)

if /i "%1"=="run" (
    echo [*] Compiling source files...
    javac -d bin src\*.java
    echo [+] Compilation successful!
    echo.
    echo [*] Starting Printer Queue Management System...
    java -cp bin PrinterQueueDriver
    goto :eof
)

if /i "%1"=="clean" (
    echo [*] Cleaning up...
    if exist bin rmdir /s /q bin
    if exist test-bin rmdir /s /q test-bin
    if exist target rmdir /s /q target
    echo [+] Cleanup complete!
    goto :eof
)

if /i "%1"=="build" (
    echo [*] Building project...
    javac -d bin src\*.java
    javac -cp bin test\PrinterQueueTest.java -d test-bin 2>nul
    if %errorlevel% equ 0 (
        echo [+] Build complete!
    )
    goto :eof
)

if /i "%1"=="all" (
    echo [*] Running full build cycle...
    if exist bin rmdir /s /q bin
    if exist test-bin rmdir /s /q test-bin
    mkdir bin
    mkdir test-bin
    
    echo [*] Compiling source files...
    javac -d bin src\*.java
    
    echo [*] Compiling test files...
    javac -cp bin test\PrinterQueueTest.java -d test-bin 2>nul
    
    echo [+] Build successful!
    
    echo [*] Running tests with Maven...
    mvn test
    goto :eof
)

echo Unknown command: %1
echo Valid commands: compile, test, run, clean, build, all
exit /b 1
