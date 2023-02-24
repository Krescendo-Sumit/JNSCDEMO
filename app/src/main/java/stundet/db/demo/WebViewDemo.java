package stundet.db.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebViewDemo extends AppCompatActivity {
WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_demo);
        web=findViewById(R.id.web);
      //  web.loadUrl("http://krescendo.co.in/");

        String str="<html><body><h1>Hello World!</h1></body></html>";
      //  web.loadData(str,"text/html","UTF-8");

        web.loadUrl("file:///android_asset/about.html");
    }
}