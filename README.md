# Nexus Launcher

**Nexus Launcher** is a premium Minecraft: Java Edition launcher for Android.

It runs Minecraft Java Edition directly on Android using a modern, proven engine (based on the PojavLauncher / Battly Mobile stack) with full support for modern versions, mod loaders, and high-quality rendering backends.

## Package & Identity

- **App Name**: Nexus Launcher
- **Package ID**: `com.nexuslauncher`
- **Theme**: Dark navy background, blue gradients, glass-like panels, rounded cards, clean typography

## Core Features (Engine inherited)

- Minecraft: Java Edition (legacy + modern versions)
- Java 8 / 17 / 21+ runtime management
- Fabric, Forge, NeoForge, Quilt, LegacyFabric
- MobileGlues / GL4ES / LTW rendering backends
- Instance & Profile management
- Mods, Resource Packs, Shader Packs, Modpacks
- Custom touch controls, keyboard, mouse, controller support
- Microsoft account authentication
- Scoped storage (no MANAGE_EXTERNAL_STORAGE required)

## Target UI (from reference screenshots)

- Top navigation bar with logo, Home / Versions / Servers / Downloads
- Account avatar + username dropdown
- Large Minecraft hero banner with version + Fast / Stable / Customizable labels
- Selected Profile card + Edit button
- Large attractive PLAY button with version info
- Browser Resources + Manager cards
- Instance selector with ACTIVE indicator, Create New Instance, edit actions

## Building

```bash
./gradlew :app_pojavlauncher:assembleDebug --console=plain
```

## License

LGPL-3.0 (inherited from upstream). Full attribution and source distribution requirements apply.

Credits: PojavLauncher, Boardwalk, MobileGlues, LTW, LWJGL, OpenJDK mobile ports, and the open-source Minecraft launcher community.

---

**Nexus Launcher** — Premium Minecraft Java Edition experience on Android.
