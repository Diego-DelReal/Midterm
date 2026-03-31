#!/bin/bash

# Printer Queue Management System - Build and Run Script
# This script compiles and runs the Printer Queue application

set -e  # Exit on error

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

# Create output directories
mkdir -p bin test-bin

echo "======================================"
echo "Printer Queue Management System"
echo "======================================"
echo

# Check if Java is installed
if ! command -v javac &> /dev/null; then
    echo "Error: Java compiler (javac) not found. Please install JDK 11 or higher."
    exit 1
fi

# Color codes for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Menu
if [ $# -eq 0 ]; then
    echo "Usage: $0 [command]"
    echo
    echo "Commands:"
    echo "  compile    - Compile the source files"
    echo "  test       - Run JUnit tests"
    echo "  run        - Run the interactive application"
    echo "  clean      - Remove compiled files"
    echo "  build      - Compile everything"
    echo "  all        - Clean, compile and run tests"
    echo
    echo "Examples:"
    echo "  $0 compile"
    echo "  $0 test"
    echo "  $0 run"
    exit 0
fi

case "$1" in
    compile)
        echo -e "${BLUE}[*] Compiling source files...${NC}"
        javac -d bin src/*.java
        echo -e "${GREEN}[✓] Compilation successful!${NC}"
        ;;

    test)
        echo -e "${BLUE}[*] Compiling source files...${NC}"
        javac -d bin src/*.java
        
        echo -e "${BLUE}[*] Compiling test files...${NC}"
        javac -cp bin:test test/PrinterQueueTest.java -d test-bin 2>/dev/null || {
            echo -e "${BLUE}[*] Downloading JUnit if needed...${NC}"
            javac -cp bin test/PrinterQueueTest.java -d test-bin
        }
        
        echo -e "${BLUE}[*] Running tests...${NC}"
        # Try to run tests with JUnit
        if command -v junit &> /dev/null; then
            java -cp bin:test-bin:. org.junit.runner.JUnitCore PrinterQueueTest
        else
            echo "JUnit not found in PATH. Trying with Maven..."
            if command -v mvn &> /dev/null; then
                mvn test
            else
                echo "JUnit or Maven not found. Please install Maven or JUnit."
                exit 1
            fi
        fi
        ;;

    run)
        echo -e "${BLUE}[*] Compiling source files...${NC}"
        javac -d bin src/*.java
        echo -e "${GREEN}[✓] Compilation successful!${NC}"
        echo
        echo -e "${BLUE}[*] Starting Printer Queue Management System...${NC}"
        java -cp bin PrinterQueueDriver
        ;;

    clean)
        echo -e "${BLUE}[*] Cleaning up...${NC}"
        rm -rf bin test-bin target *.class
        echo -e "${GREEN}[✓] Cleanup complete!${NC}"
        ;;

    build)
        echo -e "${BLUE}[*] Building project...${NC}"
        javac -d bin src/*.java
        javac -cp bin:test test/PrinterQueueTest.java -d test-bin 2>/dev/null || true
        echo -e "${GREEN}[✓] Build complete!${NC}"
        ;;

    all)
        echo -e "${BLUE}[*] Running full build cycle...${NC}"
        # Clean
        rm -rf bin test-bin
        mkdir -p bin test-bin
        # Compile
        javac -d bin src/*.java
        javac -cp bin:test test/PrinterQueueTest.java -d test-bin 2>/dev/null || true
        echo -e "${GREEN}[✓] Build successful!${NC}"
        # Run tests
        if command -v mvn &> /dev/null; then
            echo -e "${BLUE}[*] Running tests with Maven...${NC}"
            mvn test
        else
            echo "Maven not found, skipping automated tests."
            echo "To run tests, use: $0 test"
        fi
        ;;

    *)
        echo "Unknown command: $1"
        echo "Valid commands: compile, test, run, clean, build, all"
        exit 1
        ;;
esac
