import os
import shutil
import subprocess
from datetime import datetime

# =========================
# VERSION / PATHS
# =========================

VERSION = datetime.now().strftime("%Y%m%d_%H%M%S")

BUILD_DIR = "build"
RELEASE_DIR = os.path.join("releases", f"{VERSION}_A")

JAR_NAME = f"NFM_{VERSION}.jar"

# =========================
# CREATE FOLDERS
# =========================

os.makedirs(BUILD_DIR, exist_ok=True)
os.makedirs("releases", exist_ok=True)
os.makedirs(RELEASE_DIR, exist_ok=True)

print("\n===================================")
print(f"Building version {VERSION}")
print("===================================\n")

# =========================
# CLEAN BUILD FOLDER
# =========================

if os.path.exists(BUILD_DIR):
    for root, dirs, files in os.walk(BUILD_DIR):
        for file in files:
            try:
                os.remove(os.path.join(root, file))
            except Exception as e:
                print(f"Failed deleting {file}: {e}")

# =========================
# FIND JAVA FILES RECURSIVELY
# =========================

java_files = []

for root, dirs, files in os.walk("clean2"):

    for file in files:

        if file.endswith(".java"):

            java_files.append(
                os.path.join(root, file)
            )

if not java_files:
    print("No Java files found!")
    input("Press Enter to exit...")
    raise SystemExit

print(f"Found {len(java_files)} Java files.")

# =========================
# COMPILE JAVA
# =========================

compile_cmd = [
    "javac",
    "-encoding",
    "UTF-8",
    "-d",
    BUILD_DIR
]

compile_cmd.extend(java_files)

print("Compiling Java files...\n")

result = subprocess.run(compile_cmd)

if result.returncode != 0:
    print("\n===================================")
    print("COMPILE FAILED")
    print("===================================\n")
    input("Press Enter to exit...")
    raise SystemExit

# =========================
# BUILD JAR
# =========================

jar_path = os.path.join(RELEASE_DIR, JAR_NAME)

jar_cmd = [
    "jar",
    "cfm",
    jar_path,
    "manifest.txt",
    "-C",
    BUILD_DIR,
    "."
]

print("\nBuilding jar...\n")

result = subprocess.run(jar_cmd)

if result.returncode != 0:
    print("\n===================================")
    print("JAR BUILD FAILED")
    print("===================================\n")
    input("Press Enter to exit...")
    raise SystemExit

# =========================
# COPY GAME DATA
# =========================

copy_folders = [
    "data",
    "music",
    "stages",
    "cars",
    "graphics",
    "mycars"
]

for folder in copy_folders:

    if os.path.exists(folder):

        dst = os.path.join(RELEASE_DIR, folder)

        if os.path.exists(dst):
            shutil.rmtree(dst)

        print(f"Copying {folder}...")

        shutil.copytree(folder, dst)

# =========================
# CREATE START SCRIPT
# =========================

start_bat = os.path.join(RELEASE_DIR, "START_GAME.bat")

with open(start_bat, "w") as f:
    f.write("@echo off\n")
    f.write("cd /d %~dp0\n")
    f.write(f'java -jar "{JAR_NAME}"\n')
    f.write("pause\n")

# =========================
# BUILD SUCCESS
# =========================

print("\n===================================")
print("BUILD SUCCESS")
print("===================================\n")

print("Output:")
print(jar_path)

# =========================
# AUTO START GAME
# =========================

print("\nLaunching game...\n")

subprocess.Popen(start_bat, shell=True)

input("Press Enter to exit...")