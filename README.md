Installation docs will be updated soon!

# react-native-sweet-alert
React Native Wrapper for `Sweet Alert`

`import ReactNativeSweetAlert from 'react-native-sweet-alert';`

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
