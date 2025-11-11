#!/bin/sh
# Lightweight Maven runner for Unix-like systems - downloads Maven 3.9.6 into .mvn on first run.
MAVEN_REL_DIR=".mvn/apache-maven-3.9.6"
if [ ! -x "$MAVEN_REL_DIR/bin/mvn" ]; then
  echo "Maven not found, attempting to download Apache Maven 3.9.6..."
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL -o maven.zip "https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip"
  elif command -v wget >/dev/null 2>&1; then
    wget -q -O maven.zip "https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.zip"
  else
    echo "curl or wget is required to download Maven. Please install one or install Maven manually."
    exit 1
  fi
  mkdir -p .mvn
  if command -v unzip >/dev/null 2>&1; then
    unzip -q maven.zip -d .mvn || { echo "unzip failed"; exit 1; }
  elif command -v jar >/dev/null 2>&1; then
    jar xf maven.zip || { echo "jar extraction failed"; exit 1; }
    mv apache-maven-3.9.6 .mvn/
  else
    echo "unzip or jar is required to extract the downloaded Maven zip."
    exit 1
  fi
  rm -f maven.zip
fi
MAVEN_HOME="$(pwd)/$MAVEN_REL_DIR"
exec "$MAVEN_HOME/bin/mvn" "$@"

