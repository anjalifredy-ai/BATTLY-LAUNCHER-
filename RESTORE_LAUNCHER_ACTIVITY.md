# CRITICAL: Restore LauncherActivity.java

A tool upload truncated this file. Restore from git history:

```bash
git fetch origin
git checkout 7cfebef2850567e7b9b579d1de6bfbe9a7b2bdff -- app_pojavlauncher/src/main/java/net/kdt/pojavlaunch/LauncherActivity.java
```

Then change **one line** so Nexus home is the root:

Find:
```java
.add(R.id.container_fragment, MainMenuFragment.class, null, "ROOT").commit();
```

Replace with:
```java
.add(R.id.container_fragment, NexusHomeFragment.class, null, "ROOT").commit();
```

Also in settings/auth listeners, allow `NexusHomeFragment` the same way as `MainMenuFragment`.

Or on GitHub website:
1. Open file history for LauncherActivity.java
2. Restore the version from commit `7cfebef`
3. Apply the MainMenu → NexusHome change above

## Features already pushed (keep these)
- NexusServersFragment + Aternos/Minehut WebViews
- Home multipayer section
- Fabric badge drawable
- NexusHomeFragment wiring (PLAY, mods, manager, servers)
- Signed release APK CI
