package club.towme.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class urlViewerActivity extends AppCompatActivity {
    WebView webview;
    String url=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null) url = savedInstanceState.getString("url", null);
        setContentView(R.layout.activity_url_viewer);

        if(url==null || url.length()==0) {
            url = getIntent().getStringExtra("url");
            if (url == null || url.length() == 0) url = "https://casting.getguide.biz/";
        }
        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
//        String path = Uri.parse(url).toString();
        webview.loadUrl(url);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
 //               view.loadUrl("javascript:window.android.onUrlChange(window.location.href);");
                urlViewerActivity.this.url = url;
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("url", this.url);
    }
}