# Nexus Launcher Rebrand Status

**Package**: `com.nexuslauncher`  
**App Name**: Nexus Launcher  
**Repo**: https://github.com/anjalifredy-ai/BATTLY-LAUNCHER-  
**Branch**: `v3_openjdk`

## Completed
- [x] Root project name → Nexus Launcher
- [x] applicationId → `com.nexuslauncher` (defaultConfig + all buildTypes + authorities)
- [x] versionName → `1.0.0-nexus`
- [x] README rewritten
- [x] GitHub Actions workflow (`nexus-build.yml`)
- [x] Branding strings (`nexus_strings.xml`)
- [x] This status document

## Critical pending
- [ ] **build.gradle full restore**: The afterEvaluate block + full dependencies block was truncated during large-file updates. The package ID is correct, but the file is currently missing the second half (afterEvaluate, R8 checks, native page-size tasks, and the dependencies {} block including MobileGlues/Terracotta/ltw/Firebase). This must be restored for a successful build.

## Next steps (automatic)
1. Restore complete build.gradle (highest priority)
2. Soft-disable Battly ads / Battly+ branding
3. Home screen UI matching screenshots
4. Instance selector UI matching screenshots

## Engine status
Fully preserved (no engine code deleted).
