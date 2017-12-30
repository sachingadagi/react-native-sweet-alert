
# react-native-sweet-alert
React Native Wrapper for `Sweet Alert`
## Installation

install via npm

`npm install @sachingadagi/reactnativesweetalert`

change your `settings.gradle` to look like this

```
include ':reactnativesweetalert', ':app'
project(':reactnativesweetalert').projectDir = new File(rootProject.projectDir, '../node_modules/@sachingadagi/reactnativesweetalert/android')
```

in your app gradle file (`android\app\build.gradle`)

`compile project(':reactnativesweetalert')`

 Add `tools:replace="android:icon"` to `<application>` tag in `AndroidManifest.xml` so that it looks like

 ```
     <application
      android:name=".MainApplication"
      android:allowBackup="true"
      android:label="@string/app_name"
      android:icon="@mipmap/ic_launcher"
      android:theme="@style/AppTheme"
      tools:replace="android:icon">
  ```


Add
`new ReactSweetAlertPackage()` to Array of packages loaded in `MainAppliction.java` inside `getPackages()` method so that it looks like

```
    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
          new ReactSweetAlertPackage() // <-- Here

      );
    }

```

Usage
```
ReactNativeSweetAlert.show(
  { 
      alertType : ReactNativeSweetAlert.WARNING_TYPE,
      title : 'You sure? ',
      confirmText : 'OK',
      cancelText : 'Cancel',
      setCanceledOnTouchOutside : false
   },
   () => { console.log('On Confirm clicked!')},
   () => { console.log('On Cancel clicked!') }
);
```
## Methods:

`show(configurtionObject,successCallback,errorCallback)`

configuration object accepts following properties

objectName | object type | description | allowed values 
-------|-------|--------------------|--------------------------------
alertType | string |  sets the type of alert title |  ReactNativeSweetAlert.WARNING_TYPE, ReactNativeSweetAlert.ERROR_TYPE,ReactNativeSweetAlert.SUCCESS_TYPE,ReactNativeSweetAlert.PROGRESS_TYPE,
confirmText | string | sets the text on confirm button | -
cancelText | string |  sets the button on cancel button | -
setCanceledOnTouchOutside | boolean | decides whether the alert is cancelable when you touch the outer area | true/false


`isSpinning()`
returns a promise with boolean parameter

e.g. 
```
 ReactNativeSweetAlert.isSpinning().then( isSpinning =>  /* your logic */)
```


`changeAlertType(alertType)`

`setRimColor(color)`

`setBarColor(width)` 
