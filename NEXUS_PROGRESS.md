# Nexus Launcher – continuous progress log

## Latest commits
- Theme colors + styles + drawables (screenshot palette)
- Home + instance layouts with proper view IDs
- NexusHomeFragment: PLAY / EDIT / profile card / Browser / Manager clicks
- NexusInstanceSelectorFragment stub
- LauncherActivity one-line switch documented
- build.gradle.append for missing dependencies

## Do on your machine now
1. Append `build.gradle.append` → `build.gradle`
2. In LauncherActivity replace MainMenuFragment → NexusHomeFragment (1 line)
3. Build debug APK

## Still coding next
- Real launch from PLAY (MainMenuFragment method)
- Real profile list in instance selector
- Soft-disable ads
