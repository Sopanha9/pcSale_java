# PC Sale POS System - Quick Launcher
# This script runs the application (compile first with build.ps1)

Write-Host "Starting PC Sale POS System..." -ForegroundColor Green
Write-Host ""

$projectRoot = $PSScriptRoot
$binDir = Join-Path $projectRoot "bin"
$libDir = Join-Path $projectRoot "lib"

# Check if compiled
if (-not (Test-Path $binDir) -or (Get-ChildItem $binDir).Count -eq 0) {
    Write-Host "ERROR: Application not compiled!" -ForegroundColor Red
    Write-Host "Please run build.ps1 first to compile the application." -ForegroundColor Yellow
    Write-Host ""
    $compile = Read-Host "Run build script now? (y/n)"
    if ($compile -eq "y") {
        & "$projectRoot\build.ps1"
        exit
    } else {
        exit 1
    }
}

# Check for MySQL driver
$mysqlJar = Get-ChildItem -Path $libDir -Filter "mysql-connector*.jar" -ErrorAction SilentlyContinue

if (-not $mysqlJar) {
    Write-Host "WARNING: MySQL JDBC driver not found!" -ForegroundColor Yellow
    Write-Host "The application may not connect to the database." -ForegroundColor Yellow
    Write-Host ""
}

# Run the application
try {
    java -cp "$binDir;$libDir\*" com.pcsale.gui.LoginFrame
} catch {
    Write-Host "ERROR: Failed to start application!" -ForegroundColor Red
    Write-Host $_.Exception.Message -ForegroundColor Red
    pause
    exit 1
}
