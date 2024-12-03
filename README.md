# CI/CD Pipelines mit einer Android App

Dieses Projekt ist ein Beispielprojekt zur Verwendung von Github Actions und Erstellung einer Release Pipeline für eine Android Application. Das Projekt ist im Rahmen einer Gruppenarbeit der FH-Joanneum Kapfenberg mit [eigener Angabe und Fragestellungen](./doc/Angabe.md) entstanden.

## Table of Contents

- [CI/CD Pipelines mit einer Android App](#cicd-pipelines-mit-einer-android-app)
  - [Table of Contents](#table-of-contents)
  - [TODOs](#todos)
  - [Git Repository](#git-repository)
  - [Android App - Taschenrechner](#android-app---taschenrechner)
  - [Github Actions](#github-actions)

## TODOs

Aktuelle TODOs werden separate in [`TODO.md`](./TODO.md) aufgelistet.

## Git Repository

[https://github.com/bschmatz/cicd_android/actions](https://github.com/bschmatz/cicd_android/actions)

## Android App - Taschenrechner

Bei der Beispiel Application handelt es sich um einen einfachen Taschenrechner. Dieser beherrscht derzeit nur die 4 Grundrechnungsarten. Anhand dieses Beispielprojektes sollen die nachfolgenden Github Actions angewandt, automatisch getestet und ein Release erstellt werden.

> Taschenrechner nach dem starten der App
> ![Taschenrechner App](./doc/images/Calculator-App.png)

## Github Actions

Unter `.github/workflows/kotlin.yml` befindet sich die für die [Github Actions](https://github.com/bschmatz/cicd_android/actions) definierten Steps.
