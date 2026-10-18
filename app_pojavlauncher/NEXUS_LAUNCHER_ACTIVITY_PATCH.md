# One-line home switch (LauncherActivity.java)

File: `app_pojavlauncher/src/main/java/net/kdt/pojavlaunch/LauncherActivity.java`

## Change 1 – default home fragment (required)

Find (~line 342):

```java
.add(R.id.container_fragment, MainMenuFragment.class, null, "ROOT").commit();
```

Replace with:

```java
.add(R.id.container_fragment, NexusHomeFragment.class, null, "ROOT").commit();
```

`NexusHomeFragment` is already imported via `import net.kdt.pojavlaunch.fragments.*;`

## Change 2 – home button behavior (optional)

`Tools.backToMainMenu(this)` still expects MainMenuFragment.  
Until that is updated, use the normal back stack or temporarily keep MainMenuFragment for the home button.

## After this change
- App opens on Nexus dark navy home layout
- PLAY button shows toast until launch is wired
- Old Battly home is no longer the root

## Next code steps
1. In `NexusHomeFragment`, call the same launch method MainMenuFragment uses for Play
2. Bind profile name from `LauncherProfiles`
3. Open `NexusInstanceSelectorFragment` from profile dropdown
