#!/bin/bash
echo "📥 Downloading Gradle wrapper JAR..."

# Create the gradle wrapper directory if it doesn't exist
mkdir -p gradle/wrapper

# Download gradle-wrapper.jar
curl -L -o gradle/wrapper/gradle-wrapper.jar \
  "https://github.com/gradle/gradle/raw/v8.5.0/gradle/wrapper/gradle-wrapper.jar"

if [ $? -eq 0 ]; then
    echo "✅ Gradle wrapper downloaded successfully!"
else
    echo "❌ Failed to download Gradle wrapper. Trying alternative method..."
    
    # Alternative: use wget
    wget -O gradle/wrapper/gradle-wrapper.jar \
      "https://github.com/gradle/gradle/raw/v8.5.0/gradle/wrapper/gradle-wrapper.jar"
    
    if [ $? -eq 0 ]; then
        echo "✅ Gradle wrapper downloaded successfully with wget!"
    else
        echo "❌ Failed to download Gradle wrapper with both curl and wget."
        echo "Please download manually from: https://github.com/gradle/gradle/raw/v8.5.0/gradle/wrapper/gradle-wrapper.jar"
        echo "And place it in: gradle/wrapper/gradle-wrapper.jar"
    fi
fi
