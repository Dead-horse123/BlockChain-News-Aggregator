@echo off

REM Install Python packages

echo Installing pandas...
python -m pip install pandas
if errorlevel 1 (
    py -m pip install pandas
)
pause

echo Updating pip, setuptools, and wheel...
python -m pip install -U pip setuptools wheel
if errorlevel 1 (
    py -m pip install -U pip setuptools wheel
)
pause

echo Installing spacy...
python -m pip install -U spacy
if errorlevel 1 (
    py -m pip install -U spacy
)
pause

REM Try to download the spacy model using python
echo Downloading spacy model using python...
python -m spacy download en_core_web_lg
if errorlevel 1 (
    echo Failed to download spacy model using python, trying with py...
    py -m spacy download en_core_web_lg
    if errorlevel 1 (
        echo Failed to download spacy model with both python and py
        pause
        exit /b 1
    )
)
pause

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
