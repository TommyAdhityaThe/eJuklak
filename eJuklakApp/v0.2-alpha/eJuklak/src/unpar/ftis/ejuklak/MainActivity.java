package unpar.ftis.ejuklak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {
	static WebView browser;
	static String link0 = "pendahuluan.html";
	static String link1 = "bab1.html";
	static String link2 = "bab2.html";
	static String link3 = "bab3.html";
	static String link4 = "bab4.html";
	static String cover,bab1,bab2,bab3,bab4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cover = prepare("pendahuluan.html");
        bab1 = prepare("bab1.html");
        bab2 = prepare("bab2.html");
        bab3 = prepare("bab3.html");
        bab4 = prepare("bab4.html");
        
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.menuContainer, new PlaceholderFragment())
                    .commit();
        }
    }
    
    public String prepare(String halaman) {
    	String res = "";
		try{
		    BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open(halaman)));
		    String temp = "";
		    String hasil =br.readLine();
		    while(hasil!=null){
		    	temp =temp + hasil;
		    	hasil = br.readLine();
		    }
		    
		    res = temp;
		    
//		    browser.loadData(temp, "text/html; charset=UTF-8", null);
		    
	    }catch(IOException ex){
	    	ex.printStackTrace();
	    }
		return res;
	}


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

    	Fragment frag;
    	FragmentTransaction ftran;
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            frag = new CoverFragment(link0,cover);
    		ftran = getFragmentManager().beginTransaction().add(R.id.container, frag);
    		ftran.commit();
    		
    		Button button0 = (Button)rootView.findViewById(R.id.ButtonCover);
    		Button button1 = (Button)rootView.findViewById(R.id.buttonBab1);
    		Button button2 = (Button)rootView.findViewById(R.id.buttonBab2);
    		Button button3 = (Button)rootView.findViewById(R.id.buttonBab3);
    		Button button4 = (Button)rootView.findViewById(R.id.buttonBab4);
    		
			button0.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				frag = new CoverFragment(link0,cover);
    				ftran = getFragmentManager().beginTransaction().replace(R.id.container, frag);
    				ftran.commit();
    			}
    		});

    		button1.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				frag = new Bab1Fragment(link1,bab1);
    				ftran = getFragmentManager().beginTransaction().replace(R.id.container, frag);
    				ftran.commit();
    			}
    		});
    		
    		button2.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				frag = new Bab2Fragment(link2,bab2);
    				ftran = getFragmentManager().beginTransaction().replace(R.id.container, frag);
    				ftran.commit();
    			}
    		});

    		button3.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				frag = new Bab3Fragment(link3,bab3);
    				ftran = getFragmentManager().beginTransaction().replace(R.id.container, frag);
    				ftran.commit();
    			}
    		});

    		button4.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				frag = new Bab4Fragment(link4,bab4);
    				ftran = getFragmentManager().beginTransaction().replace(R.id.container, frag);
    				ftran.commit();
    			}
    		});
            
            return rootView;
        }
    }
}
