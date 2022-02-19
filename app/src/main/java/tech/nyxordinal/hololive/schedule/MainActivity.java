package tech.nyxordinal.hololive.schedule;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        WebView browser = new WebView(getApplicationContext());
        browser.setWebViewClient(new MyWebViewClient());
        browser.loadUrl("https://schedule.hololive.tv");

        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);

        setContentView(browser);
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            try {
                if (url.startsWith("http://schedule.hololive.tv")
                        || url.startsWith("https://schedule.hololive.tv")) {
                    return false;
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return true;
        }

    }

}

