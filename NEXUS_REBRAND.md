# Nexus Launcher Rebrand Status

**Package**: `com.nexuslauncher`  
**App Name**: Nexus Launcher  
**Repo**: https://github.com/anjalifredy-ai/BATTLY-LAUNCHER-  
**Branch**: `v3_openjdk`

## Completed
- [x] Root project name → Nexus Launcher
- [x] applicationId → `com.nexuslauncher`
- [x] All storage / share authorities updated
- [x] versionName → `1.0.0-nexus`
- [x] README rewritten
- [x] GitHub Actions workflow (`nexus-build.yml`)
- [x] Branding strings (`nexus_strings.xml`)
- [x] `build.gradle.append` file added (contains the missing afterEvaluate + dependencies)

## How to finish build.gradle (1 minute)

The main `app_pojavlauncher/build.gradle` currently ends at `buildToolsVersion = '36.0.0' }`.

**Do this:**
1. Open `app_pojavlauncher/build.gradle`
2. Open `app_pojavlauncher/build.gradle.append`
3. Copy everything from the append file and paste it at the end of `build.gradle`
4. Delete the `.append` file (optional)
5. Commit

After that the project will have full dependencies (MobileGlues, Terracotta, ltw, Firebase, etc.) and can build.

## Next automatic work
- Soft-disable Battly ads
- Clean remaining strings
- Start Home screen UI matching the screenshots you provided
