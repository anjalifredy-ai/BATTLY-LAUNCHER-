# Nexus Launcher Rebrand Status

**Target Package**: `com.nexuslauncher`  
**App Name**: Nexus Launcher  
**Base Repo**: https://github.com/anjalifredy-ai/BATTLY-LAUNCHER-  
**Branch**: v3_openjdk  
**Engine**: Fully preserved (MobileGlues, LTW, Java runtimes, version install, launch pipeline)

## Completed
- [x] Root project name → Nexus Launcher
- [x] README rewritten for Nexus Launcher
- [x] Branding override resource file added
- [x] GitHub Actions workflow for debug APK (`nexus-build.yml`)
- [x] This tracking document

## Next (in progress)
- [ ] applicationId → `com.nexuslauncher` (all buildTypes + authorities)
- [ ] App name strings (app_name / app_short_name)
- [ ] Disable / remove Battly-specific ads, Battly+ screens, BattlyWorlds branding where possible
- [ ] Home screen UI matching the provided screenshots (hero banner, PLAY button, Selected Profile, Browser Resources, Manager)
- [ ] Instance selector UI matching second screenshot (ACTIVE indicator, Create New Instance)
- [ ] Clean navigation bar (Home, Versions, Servers, Downloads)

## Important
Do **not** delete working engine code.  
All changes must keep the Minecraft Java launch path intact.

## How to get APK
1. Go to Actions tab
2. Run "Nexus Launcher APK Build" workflow
3. Download the artifact `NexusLauncher-debug`
