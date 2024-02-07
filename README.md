<img src="images/logo.png" alt="App Logo"/>

# Architecture

The RandomUser app follows Google's architecture design recommendations in [the official guide](https://developer.android.com/topic/architecture) 
explained in more detail in the [nowinandroid google app](https://github.com/android/nowinandroid/blob/main/docs/ArchitectureLearningJourney.md)

It is divided into 3 layers
- [ui](https://developer.android.com/jetpack/guide/ui-layer) - Comprises UI elements built using [Jetpack Compose](https://developer.android.com/jetpack/compose) and [Android ViewModels](https://developer.android.com/topic/libraries/architecture/viewmodel) 
- [domain](https://developer.android.com/jetpack/guide/domain-layer) - The domain layer contains use cases. These are classes which have a single invocable method `(operator fun invoke)` containing business logic.
- [data]( https://developer.android.com/jetpack/guide/data-layer) - It is the source of truth for all data in the app.

---
The architecture uses the reactive programming model with [UDF](https://developer.android.com/jetpack/guide/ui-layer#udf) with the data layer below using [kotlin flows](https://developer.android.com/kotlin/flow)

#### benefits

- Higher layers react to changes in lower layers.
- Events flow down.
- Data flows up.


# Modularization

Modularization is the practice of breaking the concept of a monolithic, one-module codebase into loosely coupled, self contained modules.

## benefits of modularization
It offers many benefits, the most notable of which are:
* **Scalability**
* **Enabling work in parallel**
* **Ownership**
* **Encapsulation**
* **Reduced build time**
* **Reusability**
* **Dynamic delivery**


based on:
* **Low coupling** - Modules should be as independent as possible from one another, so that changes to one module have zero or minimal impact on other modules. They should not possess knowledge of the inner workings of other modules.


* **High cohesion** - A module should comprise a collection of code that acts as a system. It should have clearly defined responsibilities and stay within boundaries of certain domain knowledge. For example, the core:network module in Now in Android is responsible for making network requests, handling responses from a remote data source, and supplying data to other modules.


# Build

You can build the application by pressing the green Android Studio button ;) or with the well-known Gradle tasks.

```./gradlew installDebug``` with devices connected

```./gradlew installRelease``` with devices connected

```./gradlew buildDebug``` without devices connected

```./gradlew buildRelease``` without devices connected

# Testing

can run test with following gradle task

- `testDebug` for local unit test
- `connectedDebugAndroidTest` for instrumented test


# Highlighting

* [**build-logic**](build-logic/README.md)
* [**Robot Pattern**](feature/users/src/androidTest/kotlin/com/amarinag/randomuser/feature/users)
* [**Shimmer Animation**](core/designsystem/src/main/kotlin/com/amarinag/randomuser/core/designsystem/component/ListItem.kt#L151-L176)