# Nexus Launcher PRO – Status

**Package**: `com.nexuslauncher`  
**App name**: Nexus Launcher PRO  
**Branch**: `v3_openjdk`

## Fixed
- [x] `build.gradle` restored with `afterEvaluate` + `dependencies` (was truncated → build broken)
- [x] CI workflow Android SDK packages specified
- [x] Logo vectors + adaptive icon
- [x] Home / instance UI layouts
- [x] PLAY → real launch path

## Enable Nexus home (1 line in LauncherActivity.java)
```java
.add(R.id.container_fragment, NexusHomeFragment.class, null, "ROOT").commit();
```

## Build
Push to `v3_openjdk` or run **Nexus Launcher APK Build** workflow.
