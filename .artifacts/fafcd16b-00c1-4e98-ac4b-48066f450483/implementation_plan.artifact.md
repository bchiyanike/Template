# Master Architecture Plan: Enterprise Template Upgrade

This plan outlines the transformation of the current "Template" project into an enterprise-grade Android application base. The project currently has a multi-module structure but lacks implementation in the core layers.

## User Review Required

> [!IMPORTANT]
> **Firebase Credentials**: I will need to set up Firebase dependencies, but a `google-services.json` file will be required for a real build. I will provide placeholders/stubs for now.
> **Package Name**: The current package is `com.lionico.template`. Please confirm if this should be changed for the "real client" version.
> **Offline-First Strategy**: I will implement a "Cache-then-Network" strategy using Room and WorkManager.

## Proposed Changes

### Phase 1: Core Infrastructure Implementation
Implement the backbone of the application across the existing `core` modules.

#### [MODIFY] [libs.versions.toml](file:///C:/Users/Blessing/AndroidStudioProjects/Template/gradle/libs.versions.toml)
- Update dependencies to latest stable versions (Firebase, Play Billing, Security-Crypto).

#### [NEW] Core Utilities (:core:common)
- Result/Resource wrappers for UI-Data communication.
- Dispatchers provider for testing.
- Network connectivity observer.

#### [NEW] Domain Layer (:core:domain)
- Base UseCase interfaces.
- Authentication and User domain models.

#### [NEW] Data Layer (:core:data & :core:database & :core:network)
- **:core:network**: Retrofit configuration, OkHttp interceptors (Auth, Logging), SSL Pinning.
- **:core:database**: Room database setup, Encrypted triggers (optional), Type converters.
- **:core:data**: Repository implementations with offline-first logic using `flow`.

---

### Phase 2: Firebase Integration
Integrate Firebase services for production monitoring and user management.

#### [NEW] Firebase Service Wrapper (:core:network or new :core:firebase)
- Authentication repository (Google/Email).
- Analytics and Crashlytics utility.
- Remote Config for feature flagging.

---

### Phase 3: Security & Hardening
Apply enterprise-grade security measures.

#### [MODIFY] [build.gradle.kts](file:///C:/Users/Blessing/AndroidStudioProjects/Template/app/build.gradle.kts)
- Configure `release` build type with aggressive R8 rules.

#### [NEW] Encrypted Storage (:core:data)
- DataStore implementation using AES-GCM encryption for sensitive user data.

---

### Phase 4: Monetization Readiness
Prepare the app for revenue generation.

#### [NEW] Billing Module (:core:billing - *to be created*)
- Wrapper around Google Play Billing Library.
- Subscription state management.

#### [NEW] Ads Infrastructure (:core:ui)
- AdMob container components (Banner, Interstitial stubs).

---

### Phase 5: UI & Testing
Finalize the design system and ensure full coverage.

#### [MODIFY] Theme & Typography (:core:ui)
- Complete the Material 3 implementation with custom styles.

#### [NEW] Testing Suites
- Unit tests for Repositories and ViewModels.
- Hilt-based UI tests for critical flows.

## Verification Plan

### Automated Tests
- `./gradlew test` for all modules.
- `./gradlew connectedCheck` for `:app`.

### Manual Verification
- Verify Hilt dependency graph builds successfully.
- Verify Offline-first behavior by simulating network loss.
- Verify R8 shrinking in release builds.
