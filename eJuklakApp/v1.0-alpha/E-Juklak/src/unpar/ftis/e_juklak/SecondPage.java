package unpar.ftis.e_juklak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.message.BufferedHeader;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SecondPage extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second_page);
		WebView web = (WebView)findViewById(R.id.webView1);
	    web.setWebViewClient(new WebViewClient() {
	                        @Override
	                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
	                            view.loadUrl(url);
	                            return true;
	                        }
	                    });
	    try{
	    BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("halaman1.html")));
	    String temp = "";
	    String hasil =br.readLine();
	    while(hasil!=null){
	    	temp =temp + hasil;
	    	hasil = br.readLine();
	    }
	    web.loadData(hasil, "text/html; charset=UTF-8", null);
	    
	    }catch(IOException ex){
	    	ex.printStackTrace();
	    	
	    }
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
