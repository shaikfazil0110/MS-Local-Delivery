# M S Local Delivery — Android APK

Simple local delivery Android app for **Ilaiyangudi and nearby villages**. No server, login, database or paid API.

## Features
- Grocery, vegetables, hotel food, medicine and parcel category cards with bundled artwork
- Tamil + English display toggle
- Customer name, phone, delivery address and order requirements form
- WhatsApp click-to-chat to **+91 8807169133** with a prefilled order message (customer must tap Send)
- Tap-to-dial contact button
- Cash on Delivery / UPI information; payments are **not** processed inside the app

## Build an APK on GitHub (no Android Studio required)
1. Create a new GitHub repository (for example `ms-local-delivery`).
2. Upload **the contents of this project folder to the repository root**. Keep the hidden `.github/workflows/build-apk.yml` folder and file.
3. Open the repository's **Actions** tab and enable Actions if GitHub asks.
4. Select **Build Android APK** and click **Run workflow** (or push to `main`/`master`).
5. Open the completed run, scroll to **Artifacts**, download `MS-Local-Delivery-debug-APK`.
6. Unzip the artifact, copy `app-debug.apk` to an Android phone, and install it (you may need to allow installs from your file manager).

The workflow installs Java 17, Android API 35 and Gradle 8.9, builds `:app:assembleDebug`, and uploads the debug APK. No Gradle wrapper JAR is required because GitHub Actions installs Gradle.

**Note:** This is a *debug APK* for testing and direct sharing, not a signed production Play Store release. The app itself stores no orders; WhatsApp needs an internet connection to send. Customers must press Send in WhatsApp. GitHub-hosted Actions are subject to GitHub's usage limits.

## Build locally (optional)
Open the project in Android Studio and sync Gradle 8.9, or install Gradle 8.9 and Android SDK API 35, then run `gradle :app:assembleDebug`.

## Edit business details
In `app/src/main/java/com/mslocal/delivery/MainActivity.java`, update `8807169133` and the `https://wa.me/918807169133` URL together if the business number changes.

## Project layout
- `.github/workflows/build-apk.yml` — GitHub Actions build workflow
- `app/src/main/java/com/mslocal/delivery/MainActivity.java` — complete Android UI and WhatsApp ordering
- `app/src/main/res/drawable/` — bundled category images
- `app/build.gradle.kts` — Android app build settings
