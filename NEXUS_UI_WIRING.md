# Nexus UI Wiring Guide

## New files
- `res/layout/nexus_home_screen.xml`
- `res/layout/nexus_instance_selector.xml`
- `res/values/nexus_colors.xml`
- `res/values/nexus_styles.xml`
- `res/drawable/nexus_*`
- `fragments/NexusHomeFragment.java`
- `fragments/NexusInstanceSelectorFragment.java`

## How to show Nexus home instead of Battly home

1. Open `LauncherActivity.java` (or the fragment that currently hosts the main home UI).
2. Find where the default home fragment is added (often a Battly or Launcher home fragment).
3. Replace the transaction with:

```java
getSupportFragmentManager()
    .beginTransaction()
    .replace(R.id.container_or_your_id, new NexusHomeFragment(), NexusHomeFragment.TAG)
    .commit();
```

4. For the instance selector (opened from profile dropdown / edit):

```java
getSupportFragmentManager()
    .beginTransaction()
    .replace(R.id.container_or_your_id, new NexusInstanceSelectorFragment(), NexusInstanceSelectorFragment.TAG)
    .addToBackStack(null)
    .commit();
```

## Play button
Connect `R.id.nexus_play_button` click to the **same launch method** the existing home Play button uses (do not invent a new launch path).

## Profiles / instances
Use existing:
- `net.kdt.pojavlaunch.value.launcherprofiles.LauncherProfiles`
- `MinecraftLauncherProfiles`
- `ProfileEditorFragment`

## Build reminder
Still append `app_pojavlauncher/build.gradle.append` into `build.gradle` before a full build.
