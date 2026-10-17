# Nexus Launcher Rebrand Status

**Package**: `com.nexuslauncher`  
**App Name**: Nexus Launcher  
**Repo**: https://github.com/anjalifredy-ai/BATTLY-LAUNCHER-  
**Branch**: `v3_openjdk`

## Completed
- [x] Root project name → Nexus Launcher
- [x] applicationId → `com.nexuslauncher`
- [x] Debug / release authorities updated
- [x] versionName → `1.0.0-nexus`
- [x] README rewritten
- [x] GitHub Actions workflow (`nexus-build.yml`)
- [x] Branding strings override file
- [x] This status document

## In Progress / Known Issues
- [ ] `build.gradle` afterEvaluate + full dependencies block may be truncated in latest commit (package ID is correct). Needs full restore for clean builds.
- [ ] Main `strings.xml` still contains some old Pojav/Battly text (override file added)
- [ ] Battly ads / Battly+ / BattlyWorlds branding still present in code (to be soft-disabled)
- [ ] Home screen UI (screenshot style) not yet started
- [ ] Instance selector UI not yet started

## Engine
Fully preserved: MobileGlues, LTW, Java runtimes, version installation, Fabric/Forge/NeoForge, launch pipeline, controls.

## Next automatic steps
1. Restore complete build.gradle dependencies
2. Soft-disable ads
3. Begin home screen layout files
