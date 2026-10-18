# Nexus Launcher – Status (fast track)

**Package**: `com.nexuslauncher`  
**App**: Nexus Launcher  
**Repo**: https://github.com/anjalifredy-ai/BATTLY-LAUNCHER-  
**Branch**: `v3_openjdk`

## Done
- [x] Package + authorities + versionName
- [x] Theme colors / styles / drawables (screenshot palette)
- [x] Home layout XML (hero, profile, PLAY, resources, manager)
- [x] Instance selector layout XML (ACTIVE, Create New)
- [x] `NexusHomeFragment` + `NexusInstanceSelectorFragment`
- [x] Wiring docs: `NEXUS_UI_WIRING.md` + `NEXUS_LAUNCHER_ACTIVITY_PATCH.md`
- [x] GitHub Actions workflow
- [x] `build.gradle.append` (missing deps block)

## You must do (2 minutes)
1. **Build**: paste `app_pojavlauncher/build.gradle.append` → end of `build.gradle`
2. **Home**: in `LauncherActivity.java` replace  
   `MainMenuFragment.class` → `NexusHomeFragment.class`  
   (one line, see `NEXUS_LAUNCHER_ACTIVITY_PATCH.md`)

## Next (code)
- Wire PLAY to real launch (same as MainMenuFragment)
- Bind profiles from `LauncherProfiles`
- Open instance selector from profile UI
- Soft-disable Battly ads / Battly+ branding

Engine is untouched. Only UI shell + package rebrand so far.
