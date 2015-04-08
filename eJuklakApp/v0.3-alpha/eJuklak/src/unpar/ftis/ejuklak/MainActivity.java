package unpar.ftis.ejuklak;


import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mChapters;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mChapters = getResources().getStringArray(R.array.chapters_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navi_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.fragment_main, mChapters));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
        case R.id.visit:
            // create intent to perform web search for this planet
            Intent intent = new Intent(Intent.ACTION_VIEW,
            		Uri.parse("http://unpar.ac.id"));
            //intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "Web Browser not ready.", Toast.LENGTH_SHORT).show();
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }
    
    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(PlaceholderFragment.ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mChapters[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
    
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    /**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";


		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_navigation_drawer, container,
					false);
			int i = getArguments().getInt(ARG_SECTION_NUMBER);
            String chap = getResources().getStringArray(R.array.chapters_array)[i];
//            int chapId = getResources().getIdentifier(chap.toLowerCase(Locale.getDefault()),
//                            "drawable", getActivity().getPackageName());
//            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
			WebView wv = (WebView)rootView.findViewById(R.id.webView1);
			
//			wv.setBackgroundResource(R.drawable.index);
//			wv.setBackgroundColor(Color.TRANSPARENT);
			
			WebSettings settings = wv.getSettings();
	        settings.setDefaultTextEncodingName("utf-8");
//	        wv.loadDataWithBaseURL(null, HTML, "text/html", "utf-8", null);
	        String url = "file:///android_asset/pendahuluan.html";
	        if(chap.compareTo(getResources().getStringArray(R.array.chapters_array)[0])==0) {//cover
	        	url = "file:///android_asset/pendahuluan.html";
	        	getActivity().setTitle(R.string.app_name);
			} else {
				wv.setBackgroundResource(R.drawable.bening);
				wv.setBackgroundColor(Color.TRANSPARENT);
				if(chap.compareTo(getResources().getStringArray(R.array.chapters_array)[1])==0) {//bab 1
		        	url = "file:///android_asset/bab1.html";
				} else if(chap.compareTo(getResources().getStringArray(R.array.chapters_array)[2])==0) {//bab 2
					url = "file:///android_asset/bab2.html";
				} else if(chap.compareTo(getResources().getStringArray(R.array.chapters_array)[3])==0) {//bab 3
					url = "file:///android_asset/bab3.html";
				} else if(chap.compareTo(getResources().getStringArray(R.array.chapters_array)[4])==0) {//bab 4
					url = "file:///android_asset/bab4.html";
				} else if(chap.compareTo(getResources().getStringArray(R.array.chapters_array)[5])==0) {//Lampiran
					url = "file:///android_asset/bab4.html";
				}
				getActivity().setTitle("eJuklak - "+ chap);
			}
//	        String htmlData = "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />";
	        // lets assume we have /assets/style.css file
//	        wv.loadDataWithBaseURL(url, htmlData, "text/html", "UTF-8", null);
	        wv.loadUrl(url);
//	        try {
//				Document c = (Document) Jsoup.connect("https://google.com").get();
//				c.head().getElementsByTag("style").remove();
//				c.head().appendElement("link").attr("rel", "stylesheet").attr("type","text/css").attr("href","style.css");
//				String htmldata = c.outerHtml();
//				wv.loadDataWithBaseURL(url, htmldata, "text/html", "UTF-8", null);
//				
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	        
			return rootView;
		}
	}
    

}
