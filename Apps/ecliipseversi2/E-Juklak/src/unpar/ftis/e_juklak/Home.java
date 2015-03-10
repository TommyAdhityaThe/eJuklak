package unpar.ftis.e_juklak;


import java.io.File;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.view.View.OnClickListener;
public class Home extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		WebView web = (WebView)findViewById(R.id.webView1);
		   // web.setWebViewClient(new WebViewClient() {
		    //                    @Override
		     //                   public boolean shouldOverrideUrlLoading(WebView view, String url) {
		      //                      view.loadUrl(url);
		       //                     return true;
		        //                }
		         //           });
		    File lFile = new File(Environment.getExternalStorageDirectory() + "android_assets/halaman1.html");
		    web.loadUrl("file:///" + lFile.getAbsolutePath());
		    
		
		Button b= (Button)findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
	@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),SecondPage.class);
               startActivity(intent);   
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
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
