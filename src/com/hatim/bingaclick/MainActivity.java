package com.hatim.bingaclick;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	private String url = "http://www.bingaclick.com/mobile";
	
	public final boolean networkCheck() {
	    ConnectivityManager connec =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

	    if ( connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
	    connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING ||
	    connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
	    connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED ) {
	        return true;
	    } else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED || connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED  ) {
	        return false;
	    }
	    return false;
	}
	
	
	@SuppressLint("SetJavaScriptEnabled")
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splach);
        
		if(networkCheck()) {

	        final WebView webview; 
	        webview = new WebView(MainActivity.this);
	        webview.getSettings().setJavaScriptEnabled(true);
	        webview.loadUrl(url); 

	        webview.setWebViewClient(new WebViewClient() { 
	            @Override 
	            public boolean shouldOverrideUrlLoading(WebView view, String url) {     
	                //view.loadUrl(url);     
	                return false;
	            } 

	            @Override 
	            public void onPageFinished(WebView view, String url) { 
	                super.onPageFinished(view, url); 
	                setContentView(webview);
	             } 
	        });

	    } else {
	        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
	        alertDialog.setTitle("Erreur ");
	        alertDialog.setMessage("Aucune connexion à l'Internet");

	        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {

	            }
	        });
	        alertDialog.show();
	    }
        

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
