package de.joergv.smartgarden;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaInterfaceImpl;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.LOG;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewEngine;
import org.json.JSONException;

public class MainActivity extends CordovaActivity {
    private static final String TAG = "MainActivity";
    private CordovaInterfaceImpl iface = new CordovaInterfaceImpl(this);
    private SystemWebView webView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up the webview
        ConfigXmlParser parser = new ConfigXmlParser();
        parser.parse(this);

        webView = (SystemWebView) findViewById(R.id.tutorialView);
        CordovaWebView webInterface = new CordovaWebViewImpl(new SystemWebViewEngine(webView));
        webInterface.init(iface, parser.getPluginEntries(), parser.getPreferences());
        webView.loadUrl(parser.getLaunchUrl());

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setVisibility(View.GONE);
        bottomNavigationView.getLayoutParams().height = 0;
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) webView.getLayoutParams();
        params.bottomMargin = 0;
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                LOG.d("TEST", String.valueOf(item.getItemId()));
                switch (item.getItemId()) {
                    case android.R.id.home:
                        onBackPressed();
                        return true;
                    case R.id.menu_select:
                        webView.loadUrl("javascript:App.goToSelect();");
                        return true;
                    case R.id.menu_dashboard:
                        webView.loadUrl("javascript:App.goToHome();");
                        return true;
                    case R.id.menu_watering:
                        webView.loadUrl("javascript:App.goToWatering();");
                        return true;
                    case R.id.menu_settings:
                        webView.loadUrl("javascript:App.goToSettings();");
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        iface.onActivityResult(requestCode, resultCode, intent);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[],
                                           int[] grantResults) {
        try
        {
            iface.onRequestPermissionResult(requestCode, permissions, grantResults);
        }
        catch (JSONException e)
        {
            Log.d(TAG, "JSONException: Parameters fed into the method are not valid");
            e.printStackTrace();
        }
    }
}