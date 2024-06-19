#!/bin/bash

# Function to search and execute mvn install
search_and_install() {
  for dir in "$1"/*; do
    if [ -d "$dir" ] && [ -f "$dir/pom.xml" ]; then
        echo "Found pom.xml in $dir"
        ./mvnw -f $dir/pom.xml install
    fi
  done
}

# Start searching from the current directory
search_and_install "."