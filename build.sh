#!/bin/bash
# Build script for Anime Spells Mod

echo "======================================"
echo "Anime Spells Mod - Build Script"
echo "======================================"
echo ""

# Check if gradlew exists
if [ ! -f "gradlew" ]; then
    echo "Error: gradlew not found. Make sure you're in the project root directory."
    exit 1
fi

# Make gradlew executable
chmod +x gradlew

echo "Starting build process..."
echo ""

# Run gradle build
./gradlew build

if [ $? -eq 0 ]; then
    echo ""
    echo "======================================"
    echo "Build completed successfully! ✅"
    echo "======================================"
    echo ""
    echo "JAR file location:"
    echo "build/libs/anime-spells-1.0.0.jar"
    echo ""
    echo "Next steps:"
    echo "1. Copy the JAR to your Minecraft mods folder"
    echo "2. Make sure you have Forge 1.12.2 and Electroblob's Wizardry installed"
    echo "3. Launch Minecraft and enjoy the Doom spell!"
    echo ""
else
    echo ""
    echo "======================================"
    echo "Build failed! ❌"
    echo "======================================"
    exit 1
fi
