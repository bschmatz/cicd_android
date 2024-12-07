# CI/CD Pipelines mit einer Android App

Dieses Projekt ist ein Beispielprojekt zur Verwendung von GitHub Actions und Erstellung einer Release Pipeline für eine Android Application. Das Projekt ist im Rahmen einer Gruppenarbeit der FH-Joanneum Kapfenberg mit [eigener Angabe und Fragestellungen](./doc/Angabe.md) entstanden.

## Table of Contents

- [CI/CD Pipelines mit einer Android App](#cicd-pipelines-mit-einer-android-app)
  - [Table of Contents](#table-of-contents)
  - [TODOs](#todos)
  - [Git Repository](#git-repository)
  - [Android App - Taschenrechner](#android-app---taschenrechner)
  - [GitHub Actions](#github-actions)
  - [zentrale Fragen](#zentrale-fragen)

## TODOs

Aktuelle TODOs werden separate in [`TODO.md`](./TODO.md) aufgelistet.

## Git Repository

[https://github.com/bschmatz/cicd_android](https://github.com/bschmatz/cicd_android)

## Android App - Taschenrechner

Bei der Beispiel Application handelt es sich um einen einfachen Taschenrechner. Dieser beherrscht derzeit nur die vier Grundrechnungsarten. Anhand dieses Beispielprojektes sollen die nachfolgenden Github Actions angewandt, automatisch getestet und ein Release erstellt werden.

> ![Taschenrechner App](./doc/images/Calculator-App.png)
> Taschenrechner nach dem Starten der App

## GitHub Actions

Unter `.github/workflows/kotlin.yml` befindet sich die für die [GitHub Actions](https://github.com/bschmatz/cicd_android/actions) definierten Steps.

```yml
name: Android CI

on:
  push:
    branches: [ main ]
    tags:
      - "v*"
  pull_request:
    branches: [ main ]

permissions:
  contents: write

jobs:
  lint:
    name: Static Code Analysis
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
          cache: gradle

      - name: Run Lint
        run: ./gradlew lintDebug

      - name: Upload Lint Results
        if : failure()
        uses: actions/upload-artifact@v3
        with:
          name: lint-reports
          path: app/build/reports


  test:
    name: Unit Tests
    needs: [lint]
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
          cache: gradle

      - name: Run Tests
        run: ./gradlew testDebug

      - name: Upload Test Results
        uses: actions/upload-artifact@v3
        with:
          name: test-reports
          path: app/build/reports/tests

  build:
    runs-on: ubuntu-latest
    if: startsWith(github.ref, 'refs/tags/v')
    needs: test
    steps:
      - name: Checkout code
        uses: actions/checkout@v3

      - name: Set up Java 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Build Android App
        uses: sparkfabrik/android-build-action@v1.5.0
        with:
          project-path: .
          output-path: cicidApp.apk
          browserstack-upload: false
          upload-to-play-store: false
          gradle-task: 'assembleDebug'
          ruby-version: '3.1.0'
          fastlane-version: '2.225.0'

      - name: Upload APK as Artifact
        uses: actions/upload-artifact@v3
        with:
          name: my-app-apk
          path: cicidApp.apk
      - name: Create Release
        uses: softprops/action-gh-release@v1
        if: startsWith(github.ref, 'refs/tags/v')
        with:
          files: cicidApp.apk
          generate_release_notes: true
```

## zentrale Fragen

- *Was ist notwendig zur Build-Automatisierung?*

  Zur Build-Automatisierung werden GitHub Actions und die damit verbundene Konfigurationdatei `.github/workflows/kotlin.yml` benötigt.

- *Welchen Vorteil liefert Testautomatisierung in CI/CD-Pipelines?*

  Der wohl größte Vorteil liegt in der Übersichtlichkeit beziehungsweise der genauen Aufschlüsselung jedes einzelnen Build-Steps in Form von Logs oder sogar eigenen Reports.

- *Wozu dient Containerization in der Entwicklung und in CI/CD-Pipelines?*

  Containerization dient zur Vermeidung von Fehlerquellen wie *It runs on my machine* und zur flexibleren Konfiguration der verwendeten Compiler und Build-Tools. Versionen und Dependencies können so einfacher gehandhabt werden.

- *Wie kann Infrastructure as Code dabei unterstützen?*

  Infrastructure as Code ermöglicht eine einfache Konfiguration eines komplexen Build-Systems und sorgt für wenige manuelle Handgriffe, um Einstallungen am System vorzunehmen. Dadurch ist die Fehleranfälligkeit des Gesamtprozesses deutlich verringert.

- *Welche Schritte in einer CI/CD-Pipeline müssen beachtet werden?*

  Die Pipeline dieses Projekts besteht aus einer Static Code Analysis, Unit tests, welche zuvor korrekt implementiert werden müssen, dem eigentlich Compile Prozess und dem Erstellen einer APK-Datei.

- *Wie sieht eine Delivery/Deployment-Strategie aus?*

  Für ein Deployment ist ein Git Tag notwendig, welcher einem Sourcestand eine Version zuweist. Im Zuge des Deployments wird dann ein Release mit der aktuellen Version inklusive Release Notes erstellt. Im besten Fall wird bei der Entwicklung auf einen Trunk Based Workflow gesetzt, damit neue Features schnell auf den eigentlichen Hauptbranch gelangen und dort automatisch kompiliert und getestet werden.

- *Was muss eigentlich "ausgeliefert" werden?*

  Im Fall einer Android Application muss eine (signierte) `.apk` Datei ausgeliefert werden, welche nach dem Deployment beispielsweise auf einem Emulator installiert werden kann.

- *Welche Umgebung ist notwendig zum Ausführen bzw. zur Bereitstellung des jeweiligen Themas?*

  Für die GitHub Actions werden Ubuntu Docker Container verwendet, auf welchen der aktuelle Sourcecode kompiliert wird. Lokal ist ein kompilieren und testen der App mit Android Studio möglich.
