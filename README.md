# Online Shared Preferences Example
This Online Shared Preferences sample code demonstrates how to use Onine Shared Preferences lib.

Import by using gradle and start using.

I find it useful for: Students, POCs, MVPs or small and quick projects.

![LOGO](https://github.com/PerrchicK/OnlineSharedPreferencesExample/blob/master/OSP.png)

**Instalation**

Modify your app *build.gradle* file.

Due to firebase integration, prevent 'duplicate' compilation errors by adding this:
```
android {
    ...
    packagingOptions {
        exclude 'META-INF/LICENSE'
        exclude 'META-INF/LICENSE-FIREBASE.txt'
        exclude 'META-INF/NOTICE'
    }
}
```
Create dependency and download the module to your project, by adding this:
```
dependencies {
    ...
    compile 'com.github.perrchick:onlinesharedpreferences:1.1.0'
}
```
