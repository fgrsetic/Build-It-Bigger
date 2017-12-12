## Udacity Android Nanodegree Project - Build It Bigger

Projects where app is with multiple flavors that uses
multiple libraries and Google Cloud Endpoints. The app consist
of four modules. A Java library that provides jokes, a Google Cloud Endpoints
(GCE) project that serves those jokes, an Android Library containing an
activity for displaying jokes, and an Android app that fetches jokes from the
GCE module and passes them to the Android Library for display.

Why this Project?

As Android projects grow in complexity, it becomes necessary to customize the
behavior of the Gradle build tool, allowing automation of repetitive tasks.
Particularly, factoring functionality into libraries and creating product
flavors allow for much bigger projects with minimal added complexity.


For adding Interstitial Ad to the free version follow these instructions: 
https://developers.google.com/mobile-ads-sdk/docs/admob/android/interstitial

Required Behavior:
- App retrieves jokes from Google Cloud Endpoints module and displays them via an Activity from the Android Library.

Optional Components of the app:
- Free app variant display interstitial ads between the main activity and the joke-displaying activity
- App displays a loading indicator while the joke is being fetched from the server
- Gradle task starts the GCE dev server, runs all the Android tests, and shuts down the dev server
