package tech.nyxordinal.hololive.schedule;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("https://schedule.hololive.tv");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> webView.reload());
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

