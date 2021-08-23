<div align="center">
    <img alt="Icon" src="app/src/main/res/mipmap-xxxhdpi/ic_launcher_round.png" width="200" />
</div>

<h2 align="center">
    Lady Happy - handmade hats and accessories
</h2>


<p align="center">
    <a target="_blank" href="https://github.com/egorikftp/Lady-happy/stargazers"><img src="https://img.shields.io/github/stars/egorikftp/Lady-happy.svg"></a>
    <a href="https://github.com/egorikftp/Lady-happy/network"><img alt="API" src="https://img.shields.io/github/forks/egorikftp/Lady-happy.svg"/></a>
    <a target="_blank" href="https://github.com/egorikftp/Lady-happy/blob/dev/LICENSE"><img src="https://img.shields.io/github/license/egorikftp/Lady-happy.svg"></a>
    <a target="_blank" href="https://appcenter.ms"><img src="https://build.appcenter.ms/v0.1/apps/5c717ffd-1796-4c51-9ec5-c40fa19ed472/branches/dev/badge"></a>
    <a href="https://www.codacy.com/gh/egorikftp/Lady-happy-Android/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=egorikftp/Lady-happy-Android&amp;utm_campaign=Badge_Grade"><img src="https://app.codacy.com/project/badge/Grade/a7df6f4e41734df48ef1a4ece56265b5"/></a>
</p>

![Download Status](https://playbadges.pavi2410.me/badge/downloads?id=com.egoriku.ladyhappy) ![Rating Status](https://PlayBadges.pavi2410.me/badge/ratings?id=com.egoriku.ladyhappy)

# Project characteristics 🚀

This project brings to the table set of best practices, tools, and solutions:

* 100% [Kotlin](https://kotlinlang.org/)
* Modern architecture (Clean Architecture, Multi-Module setup, Model-View-ViewModel)
* [Android Jetpack](https://developer.android.com/jetpack)
* Single-activity architecture
* CI/CD ([AppCenter](https://appcenter.ms/users/egorikftp/apps/Lady-Happy-Beta)) and [GitHub Actions](https://docs.github.com/en/actions)
* Dependency Injection ([Koin](https://github.com/InsertKoinIO/))
* Material Design
* Dark Theme


# Integration with Google Assistance 🧏‍

Currently available commands in production:
* [actions.intent.GET_THING](https://developers.google.com/assistant/app/reference/built-in-intents/common/get-thing) - Easter egg in application, the real feature with search functionality will be available in the next feature drop. 

	**Command example**: *Hey Google, search **[felt hats]** on lady happy*

* [actions.intent.OPEN_APP_FEATURE](https://developers.google.com/assistant/app/reference/built-in-intents/common/open-app-feature) - voice intent for opening main application screens:

	**Command example**: *Hey Google, open **[catalog, about, news, settings]** screen on lady happy*
  

# Built With 🛠

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - Kotlin's way of way of writing asynchronous, non-blocking code
  - [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow) - notify views when the underlying data changes
  - [SharedFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#sharedflow) - notify multiple subscribers that data changes 

- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps
  - [View Binding](https://developer.android.com/topic/libraries/view-binding) - Allows you to more easily write code that interacts with views
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI-related data in a lifecycle conscious way
  - [Saved State](https://developer.android.com/topic/libraries/architecture/viewmodel-savedstate) - Prevent data lost from system-initiated process death

- [Detekt](https://github.com/detekt/detekt) - static code analysis tool for the Kotlin programming language
- [Koin](https://github.com/InsertKoinIO/) - Dependency Injection Framework
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android

- Firebase libraries
  - [Firebase Analytics](https://firebase.google.com/docs/analytics) - Free app measurement solution that provides insight on app usage and user engagement
  - [Firebase Cloud Functions](https://firebase.google.com/docs/functions) - Custom triggers for make some changes in Firestore database
  - [Firebase Crashlytics](https://firebase.google.com/docs/crashlytics) - Lightweight, realtime crash reporter that helps you track, prioritize, and fix stability issues that erode your app quality
  - [Firebase Firestore](https://firebase.google.com/docs/firestore) - Realtime data storage
  - [Firebase Performance](https://firebase.google.com/docs/perf-mon) - Service that helps to gain insight into the performance characteristics app
  - [Firebase Storage](https://firebase.google.com/docs/storage) - Storage for images and files


# Try without building sources
<a href='https://play.google.com/store/apps/details?id=com.egoriku.ladyhappy'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' width="200"/></a>

Future plans
-------
- Step by step migration to Jetpack Compose, please follow <b>compose</b> branch
- Allow everyone to build project with demo keystore
- Allow to try Dynamic delivery with demo credentials

Other things please request using issues

# How to build
//TODO :)

# Find this repository useful? :heart:

Support it by joining __[stargazers](https://github.com/egorikftp/lady-happy/stargazers)__ for this repository. :star: <br>
And __[follow](https://github.com/egorikftp)__ me for my next creations! 🤩


# Structure 🔍

This project follows multi-module structure: 

<div align="center">
    <img alt="Icon" src="assets/modules/modules_structure.png" width="700" />
</div>

# App screenshots

##### Public features

<img alt="Icon" src="assets/images/Screenshot_20200831-220313.png" width="300" /><img alt="Icon" src="assets/images/Screenshot_20200831-220329.png" width="300" /><img alt="Icon" src="assets/images/Screenshot_20200831-220337.png" width="300" /><img alt="Icon" src="assets/images/Screenshot_20200831-220344.png" width="300" />
<img alt="Icon" src="assets/images/Screenshot_20200831-220357.png" width="300" />

##### Dynamic features

<img alt="Icon" src="assets/images/dynamic_feature_create_post.png" width="300" /><img alt="Icon" src="assets/images/dynamic_feature_edit.png" width="300" />


# License

     Copyright 2021 Yahor Urbanovich

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.

