LauncherActivity was truncated. CI restores it automatically from git history (commit 7cfebef) and sets NexusHomeFragment as ROOT.
Loading screen strings are rebranded to Nexus in the same workflow.

For local restore:
  git show 7cfebef2850567e7b9b579d1de6bfbe9a7b2bdff:app_pojavlauncher/src/main/java/net/kdt/pojavlaunch/LauncherActivity.java > app_pojavlauncher/src/main/java/net/kdt/pojavlaunch/LauncherActivity.java
  sed -i 's/MainMenuFragment.class, null, "ROOT"/NexusHomeFragment.class, null, "ROOT"/' app_pojavlauncher/src/main/java/net/kdt/pojavlaunch/LauncherActivity.java
