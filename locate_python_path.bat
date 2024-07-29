@echo off

REM Create the output folder if it doesn't exist
if not exist "%~dp0src\main\java\hust\soict\dsai\blockchain\engine" mkdir "%~dp0src\main\java\hust\soict\dsai\blockchain\engine"

REM Save Python executable path to a file
set OUTPUT_FILE=%~dp0src\main\java\hust\soict\dsai\blockchain\engine\python_path.txt

REM Try to get the Python executable path using python
echo Saving Python executable path...
python -c "import sys; print(sys.executable)" > "%OUTPUT_FILE%"
if errorlevel 1 (
    echo Failed to get Python executable path using python, trying with py...
    py -c "import sys; print(sys.executable)" > "%OUTPUT_FILE%"
    if errorlevel 1 (
        echo Failed to get Python executable path with both python and py
        pause
        exit /b 1
    )
)
pause

echo Script completed successfully.
pause