# PC Sale POS System - Build and Run Script
# PowerShell script to compile and run the application

Write-Host "==================================" -ForegroundColor Cyan
Write-Host "PC Sale POS System - Build Script" -ForegroundColor Cyan
Write-Host "==================================" -ForegroundColor Cyan
Write-Host ""

# Set variables
$projectRoot = $PSScriptRoot
$srcDir = Join-Path $projectRoot "src"
$binDir = Join-Path $projectRoot "bin"
$libDir = Join-Path $projectRoot "lib"

# Check if source directory exists
if (-not (Test-Path $srcDir)) {
    Write-Host "ERROR: Source directory not found!" -ForegroundColor Red
    exit 1
}

# Create bin directory if it doesn't exist
if (-not (Test-Path $binDir)) {
    Write-Host "Creating bin directory..." -ForegroundColor Yellow
    New-Item -ItemType Directory -Path $binDir | Out-Null
}

# Check for MySQL JDBC driver
$mysqlJar = Get-ChildItem -Path $libDir -Filter "mysql-connector*.jar" -ErrorAction SilentlyContinue | Select-Object -First 1

if (-not $mysqlJar) {
    Write-Host "WARNING: MySQL JDBC driver not found in lib/ folder!" -ForegroundColor Yellow
    Write-Host "Please download mysql-connector-j-8.x.x.jar from:" -ForegroundColor Yellow
    Write-Host "https://dev.mysql.com/downloads/connector/j/" -ForegroundColor Yellow
    Write-Host ""
    $continue = Read-Host "Continue anyway? (y/n)"
    if ($continue -ne "y") {
        exit 1
    }
}

# Compile
Write-Host "Compiling Java files..." -ForegroundColor Green
Write-Host ""

$javaFiles = Get-ChildItem -Path $srcDir -Filter "*.java" -Recurse

if ($javaFiles.Count -eq 0) {
    Write-Host "ERROR: No Java files found!" -ForegroundColor Red
    exit 1
}

# Build classpath
$classpath = "$binDir;$libDir\*"

# Compile command
$compileCmd = "javac -d `"$binDir`" -cp `"$classpath`" -encoding UTF-8"

# Add all Java files
foreach ($file in $javaFiles) {
    $compileCmd += " `"$($file.FullName)`""
}

# Execute compilation
try {
    Invoke-Expression $compileCmd
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host "Compilation successful!" -ForegroundColor Green
        Write-Host ""
    } else {
        Write-Host "Compilation failed with errors!" -ForegroundColor Red
        exit 1
    }
} catch {
    Write-Host "ERROR: Compilation failed!" -ForegroundColor Red
    Write-Host $_.Exception.Message -ForegroundColor Red
    exit 1
}

# Ask to run
Write-Host "==================================" -ForegroundColor Cyan
$runApp = Read-Host "Run the application now? (y/n)"

if ($runApp -eq "y") {
    Write-Host ""
    Write-Host "Starting PC Sale POS System..." -ForegroundColor Green
    Write-Host ""
    
    # Run the application
    java -cp "$binDir;$libDir\*" com.pcsale.gui.LoginFrame
}

Write-Host ""
Write-Host "==================================" -ForegroundColor Cyan
Write-Host "Build script completed!" -ForegroundColor Cyan
Write-Host "==================================" -ForegroundColor Cyan
