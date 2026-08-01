@echo off
REM Build script for Anime Spells Mod (Windows)

echo ======================================
echo Anime Spells Mod - Build Script
echo ======================================
echo.

REM Check if gradlew.bat exists
if not exist "gradlew.bat" (
    echo Error: gradlew.bat not found. Make sure you're in the project root directory.
    pause
    exit /b 1
)

echo Starting build process...
echo.

REM Run gradle build
call gradlew.bat build

if %errorlevel% equ 0 (
    echo.
    echo ======================================
    echo Build completed successfully! ✅
    echo ======================================
    echo.
    echo JAR file location:
    echo build\libs\anime-spells-1.0.0.jar
    echo.
    echo Next steps:
    echo 1. Copy the JAR to your Minecraft mods folder
    echo 2. Make sure you have Forge 1.12.2 and Electroblob's Wizardry installed
    echo 3. Launch Minecraft and enjoy the Doom spell!
    echo.
    pause
) else (
    echo.
    echo ======================================
    echo Build failed! ❌
    echo ======================================
    pause
    exit /b 1
)
