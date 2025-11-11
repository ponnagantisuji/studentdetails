@echo off
REM Lightweight Maven runner for Windows - downloads Maven 3.9.6 into .mvn on first run.
setlocal
set MAVEN_REL_DIR=.mvn\apache-maven-3.9.6
if not exist "%MAVEN_REL_DIR%\bin\mvn.cmd" (
  echo Maven not found in %MAVEN_REL_DIR%, attempting to download Apache Maven 3.9.6...
  powershell -NoProfile -Command "try { Invoke-WebRequest -Uri 'https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip' -OutFile 'maven.zip' -UseBasicParsing; Add-Type -AssemblyName System.IO.Compression.FileSystem; [System.IO.Compression.ZipFile]::ExtractToDirectory('maven.zip', '.mvn'); Remove-Item 'maven.zip'; } catch { Write-Error 'Download or extraction failed'; exit 1 }"
  if errorlevel 1 (
    echo Failed to download or extract Maven. Please ensure you have internet and PowerShell available, or install Maven manually.
    exit /b 1
  )
)
set MAVEN_HOME=%CD%\%MAVEN_REL_DIR%
"%MAVEN_HOME%\bin\mvn.cmd" %*
endlocal

