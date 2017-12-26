package sachingadagi.in.reactnativesweetalert;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.Nullable;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Sachin Gadagi on 26-12-2017.
 */

public class SweetAlertModule extends ReactContextBaseJavaModule {

    public static final String MODULE_NAME = "ReactNativeSweetAlert";

    private static final String WARNING_TYPE = "WARNING_TYPE";
    private static final String PROGRESS_TYPE = "PROGRESS_TYPE";
    private static final String ERROR_TYPE = "ERROR_TYPE";
    private static final String NORMAL_TYPE = "NORMAL_TYPE";
    private static final String CUSTOM_IMAGE_TYPE = "CUSTOM_IMAGE_TYPE";


    SweetAlertDialog pDialog;
    Context self;
    public SweetAlertModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(WARNING_TYPE, SweetAlertDialog.WARNING_TYPE);
        constants.put(ERROR_TYPE, SweetAlertDialog.ERROR_TYPE);
        constants.put(PROGRESS_TYPE, SweetAlertDialog.PROGRESS_TYPE);
        constants.put(NORMAL_TYPE, SweetAlertDialog.NORMAL_TYPE);
        constants.put(CUSTOM_IMAGE_TYPE, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        return constants;
    }

    /**
     * Very important to set this value, else we won't be able to load this via Native Module
      * @return
     */
    @Override
    public String getName() {
        return MODULE_NAME;
    }

    private SweetAlertDialog getInstance(){

        if(this.pDialog == null){
        self = getCurrentActivity();

        // Here getReactApplicationContext wont work, need to use getCurrent Activity!
        this.pDialog = new SweetAlertDialog(self);
        }
        return this.pDialog;
    }
    @ReactMethod
    public void show(ReadableMap options, @Nullable Callback onConfirmClick, @Nullable Callback onCancelClick) {

        getInstance(); // Instantiates, if has not been yet.
        prepareAlert(options,onConfirmClick,onCancelClick);
        pDialog.show();

    }

    @ReactMethod
    void changeAlertType(Integer alertType){
        getInstance().changeAlertType(alertType);
    }

    @ReactMethod
    public void isSpinning(Promise promise){
        try{
        promise.resolve(getInstance().isShowing());
        }
        catch (Exception ex){
            promise.reject("Error", ex.getMessage());
        }
    }

    @ReactMethod
    public void setRimColor(String color){
            getInstance().getProgressHelper().setRimColor(Color.parseColor(color));
    }

    @ReactMethod
    public void setBarColor(String color){
        getInstance().getProgressHelper().setBarColor(Color.parseColor(color));
    }

    @ReactMethod
    public void setBarWidth(String color){
        getInstance().getProgressHelper().setBarWidth(Color.parseColor(color));
    }


    private void prepareAlert(ReadableMap options, @Nullable final Callback onConfirmClick, @Nullable final Callback onCancelClick) {

        if (options.hasKey("alertType"))
            this.pDialog.changeAlertType(options.getInt("alertType"));
        if (options.hasKey("title"))
            this.pDialog.setTitleText(options.getString("title"));
        if (options.hasKey("contentText"))
            this.pDialog.setContentText(options.getString("contentText"));
        if (options.hasKey("confirmText"))
            this.pDialog.setConfirmText(options.getString("confirmText"));
        if (options.hasKey("cancelText"))
            this.pDialog.setCancelText(options.getString("cancelText"));
        if (options.hasKey("setCanceledOnTouchOutside"))
            this.pDialog.setCanceledOnTouchOutside((options.getBoolean("setCanceledOnTouchOutside")));

        this.pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                if( onConfirmClick != null ) onConfirmClick.invoke();
                sweetAlertDialog.dismiss();
            }
        });

        this.pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                if(onCancelClick != null) onCancelClick.invoke();
                sweetAlertDialog.dismiss();
            }
        });
    }
}
