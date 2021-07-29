
# Welcome to Android Kotlin Boilerplate!  
  
## Getting Started  
Android Kotlin Boilerplate refers to standardized methods, procedures and files that may be used over again for efficiency developing new Android mobile applications.  
  
## What's included  
* An Android app with _modular architecture_ and _MVVM_ architectural pattern.
* _Splash screen_ with app version and empty _MainActivity_.
* _Network connectivity interceptor_ for HTTP requests.
* _Dagger2_ for dependencies injection.
* _ViewBinding_ for activities and fragments.
* _Timber_ for logging purposes.
* Android Studio _EditorConfig_ file to maintain consistent coding styles.
* Gradle’s Kotlin _DSL_.
* _SonarQube_ configuration files.
* _JaCoCo_ maven plugin to generate test coverage reports.
* _ktlint_ for static code analysis.
* _LeakCanary_ for memory leaks detection.
* _Fastlane_ for CI/CD tasks.
* _SonarCloud_ for static code analysis.
* _Github_ workflows for automated PR actions and Firebase app distribution.
* _dokka_ for Kotlin's documentation generation.

## Installation  
Clone this repository and import it into **Android Studio**  
```bash  
git clone https://github.com/celerik/android-kotlin-boilerplate.git  
```  
  
## Build variants  
Herein you can find multiple targets that the app takes into account:  
  
|          |Staging    |Production  |
|----------|-----------|------------|  
|`Internal`|Debug      |Debug       |
|`External`|Release     |Release    |
  
 Where the following formed variants are built for staging purposes:  
- stagingInternalDebug  
- stagingInternalRelease  
  
 And these ones for production purposes:  
- productionInternalDebug  
- productionInternalRelease  
- productionExternalDebug  
- productionExternalRelease  
  
**_Sidenote:_**  environments with _Internal_ keyword, for example, could set a specific timeout for debug servers, whereas environments with _External_ keyword could have another timeout according to production servers' features. In the other hand, environments with _Debug_ keyword, could keep a debug logger activated; whereas environments with _Release_ keyword don't.  
  
## Debug app signing
In order to sign your debug app build using _debug-keystore.jks_ keystore, these are the credentials you will have to take in mind:
   
`STORE_FILE = ./app/debug-keystore.jks`

`STORE_PASSWORD = android`

`KEY_ALIAS = android_celerik`

`KEY_PASSWORD = android`
  
## Others  
1. Project's CodeStyle can be found [here](docs/codestyle.md).  
2. Project utilities file can be found [here](docs/utilities.md).
3. CI/CD documentation can be found [here](docs/cicd.md).  

## Screenshots  
  
![Screenshot_1626474458](https://user-images.githubusercontent.com/25390317/126014560-dbd18cf5-75f9-4e0a-a72e-9b63e6db0bf4.png)
![Screenshot_1626474925](https://user-images.githubusercontent.com/25390317/126014713-1c25cf42-7307-4d05-b121-5be96abdf1a4.png)