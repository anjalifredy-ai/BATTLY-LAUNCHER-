# Nexus Launcher Rebrand Status

**Package**: `com.nexuslauncher`  
**App Name**: Nexus Launcher  
**Repo**: https://github.com/anjalifredy-ai/BATTLY-LAUNCHER-  
**Branch**: `v3_openjdk`

## Completed
- [x] Root project name → Nexus Launcher
- [x] applicationId → `com.nexuslauncher`
- [x] Authorities + versionName updated
- [x] README + GitHub Actions workflow
- [x] Branding strings (`nexus_strings.xml`)
- [x] **Theme colors** (`nexus_colors.xml`) – dark navy + blue glass
- [x] **Styles** (`nexus_styles.xml`) – cards, play button, active badge
- [x] **Drawables** – play button gradient, active badge, card backgrounds
- [x] **Home screen layout stub** (`nexus_home_screen.xml`) – matches screenshot structure
- [x] **Instance selector layout stub** (`nexus_instance_selector.xml`) – matches screenshot structure
- [x] `build.gradle.append` (missing afterEvaluate + dependencies)

## Still required for full UI
- Wire these layouts into the existing launcher Activity/Fragment navigation
- Replace Battly/Pojav home fragments with Nexus home
- Connect Play button to real launch pipeline
- Connect instance list to real profile/instance data
- Icons, hero banner artwork, account spinner
- Full responsive portrait/landscape handling

## Critical build fix
`app_pojavlauncher/build.gradle` is still truncated.  
Open `build.gradle.append` → copy content → paste at end of `build.gradle` → commit.

## Theme reference (from your screenshots)
- Background: near-black navy (`#0B0F1A`)
- Cards: dark blue glass (`#1A2236`)
- Accent: blue (`#3B82F6`)
- Play button: blue gradient, rounded
- ACTIVE badge: green with soft background
- Labels: FAST (green) / STABLE (blue) / CUSTOMIZABLE (purple)
