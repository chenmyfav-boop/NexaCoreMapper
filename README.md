# NexaCore Mapper — UI Prototype

Android native starter project, inspired by the layout patterns in the screenshots supplied by the user. This is an original prototype and does not use Nebula branding or proprietary assets.

## Included
- Dark green/black dashboard
- Bottom navigation: Mulai Game, Akun Saya, Pengaturan
- Responsive native layouts
- Basic device information display
- Placeholder status panels for Wireless Debugging and Shizuku
- No heavy third-party libraries

## Build
Open this folder in Android Studio, allow Gradle sync, then:
- Build > Build APK(s)
- For release, use Build > Generate Signed Bundle / APK and select APK.

Target APK size under 10 MB is a goal, not guaranteed until built and measured. The current project is UI-only; mapping, pairing, overlays, and input services are not implemented yet. Do not treat placeholder panels as working features.

## Performance
Uses standard Android Views and avoids large assets or animation-heavy effects. Test on the target device before making FPS claims.
