package unpar.ftis.ejuklak;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.support.v4.widget.DrawerLayout;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Main Activity of the application
 * Reference: http://developer.android.com
 * @author ftis unpar
 * @author Henry Bagus Hermawan
 * @author Alexander Indrawan
 * @author Ivan Lukman
 * @author Tommy Adhitya The
 * @author Tevin Odysseus
 * @version 1.0
 */
public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	
	
	/**
	 * WebView for displaying the html pages
	 */
	private WebView web;
	
	/**
	 * Used for fragment manager initialization
	 */
	public static int child = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/**
		 * SetContentView to layout activity_main.xml
		 */
		setContentView(R.layout.activity_main);
		
		/**
		 * initialize the fragment navigation drawer
		 */
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
		mNavigationDrawerFragment.setActivity(this);
		
		/**
		 * set the current title
		 */
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, WebViewFragment.newInstance((position),child)).commit();
	}

	/**
	 * Change the current title of the application
	 * @param number the number clicked
	 */
	public void onSectionAttached(int number) {
		switch (number) {
		case 0:
			mTitle = "Pendahuluan";
			break;
		case 1:
			mTitle = "Bab 1";
			break;
		case 2:
			mTitle = "Bab 2";
			break;
		case 3:
			mTitle = "Bab 3";
			break;
		case 4:
			mTitle = "Bab 4";
			break;
		case 5:
			mTitle = "Lampiran 1";
			break;
		case 6:
			mTitle = "Lampiran 2";
			break;
		case 7:
			mTitle = "Lampiran 3";
			break;
		case 8:
			mTitle = "Lampiran 4";
			break;
		case 9:
			mTitle = "Lampiran 5";
			break;
		case 10:
			mTitle = "Lampiran 6";
			break;
		case 12:
			mTitle = "Lampiran 7";
			break;
		}
		
		
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		
		/**
		 * This is the extra feature to visit http://unpar.ac.id
		 */
		if (id == R.id.action_visit) {
			// create intent to perform web search for this planet
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://unpar.ac.id"));
            
            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "Web Browser not ready.", Toast.LENGTH_SHORT).show();
            }
            return true;
		}
		
		
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 * this fragment controls the WebView contents
	 */
	public static class WebViewFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		/**
		 * the section number
		 */
		private static int sectionN;
		/**
		 * the child position number
		 */
		private static int childPosition;

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static WebViewFragment newInstance(int sectionNumber, int childPos) {
			sectionN = sectionNumber;
			childPosition = childPos;
			WebViewFragment fragment = new WebViewFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			
			
			return fragment;
		}

		/**
		 * Empty Constructor for WebViewFragment
		 */
		public WebViewFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			WebView wv = (WebView)rootView.findViewById(R.id.webViewContent);
			
			//customizing the WebView
			wv.setBackgroundColor(Color.TRANSPARENT);
			wv.getSettings().setBuiltInZoomControls(true);
			wv.getSettings().setDisplayZoomControls(true);

			//the html file to be read
			String url = "file:///android_asset/pendahuluan.html";
			if (sectionN == 0) { //pendahuluan
				//no anchor
			} else if(sectionN == 1) { //Bab 1
				url = "file:///android_asset/bab1.html";
				if (child == 1) {//1.1
					url = "file:///android_asset/bab1.html#sejarah-fakultas-teknologi-informasi-dan-sains";
				} else if (child == 2) {//1.2
					url = "file:///android_asset/bab1.html#visi-misi-tujuan-dan-sasaran-ftis";
				} else if (child == 3) {//1.3
					url = "file:///android_asset/bab1.html#keberhasilan-ftis";
				} else if (child == 4) {//1.4
					url = "file:///android_asset/bab1.html#pengelola-fakultas";
				} else if (child == 5) {//1.5
					url = "file:///android_asset/bab1.html#daftar-dosen-ftis";
				}
			} else if(sectionN == 2) { //Bab 2
				url = "file:///android_asset/bab2.html";
				if (child == 1) {//2.1
					url = "file:///android_asset/bab2.html#matakuliah-pilihan";
				} else if (child == 2) {//2.2
					url = "file:///android_asset/bab2.html#matakuliah-prasyarat";
				} else if (child == 3) {//2.3
					url = "file:///android_asset/bab2.html#matakuliah-layanan";
				} else if (child == 4) {//2.4
					url = "file:///android_asset/bab2.html#matakuliah-umum";
				} else if (child == 5) {//2.5
					url = "file:///android_asset/bab2.html#kurikulum-program-studi-matematika";
				} else if (child == 6) {//2.6
					url = "file:///android_asset/bab2.html#kurikulum-program-studi-fisika";
				} else if (child == 7) {//2.7
					url = "file:///android_asset/bab2.html#kurikulum-program-studi-teknik-informatika";
				}
			} else if(sectionN == 3) { //Bab 3
				url = "file:///android_asset/bab3.html";
				if (child == 1) {//3.1
					url = "file:///android_asset/bab3.html#penyusunan-rencana-studi";
				} else if (child == 2) {//3.2
					url = "file:///android_asset/bab3.html#kegiatan-perkuliahan";
				} else if (child == 3) {//3.3
					url = "file:///android_asset/bab3.html#tata-cara-ujian";
				} else if (child == 4) {//3.4
					url = "file:///android_asset/bab3.html#cuti-dan-gencat-studi";
				} else if (child == 5) {//3.5
					url = "file:///android_asset/bab3.html#pengunduran-diri-sebagai-mahasiswa";
				}
			} else if(sectionN == 4) { //Bab 4
				url = "file:///android_asset/bab4.html";
				if (child == 1) {//4.1
					url = "file:///android_asset/bab4.html#evaluasi-keberhasilan-belajar-tiap-mata-kuliah";
				} else if (child == 2) {//4.2
					url = "file:///android_asset/bab4.html#evaluasi-keberhasilan-belajar-dalam-suatu-tahap-belajar";
				} else if (child == 3) {//4.3
					url = "file:///android_asset/bab4.html#kemampuan-bahasa-inggris-mahasiswa-unpar";
				}
			} else if(sectionN == 5) { //Lampiran 1
				url = "file:///android_asset/lampiran1.html";
			} else if(sectionN == 6) { //Lampiran 2
				url = "file:///android_asset/lampiran2.html";
			} else if(sectionN == 7) { //Lampiran 3
				url = "file:///android_asset/lampiran3.html";
			} else if(sectionN == 8) { //Lampiran 4
				url = "file:///android_asset/lampiran4.html";
			} else if(sectionN == 9) { //Lampiran 5
				url = "file:///android_asset/lampiran5.html";
			} else if(sectionN == 10) { //Lampiran 6
				url = "file:///android_asset/lampiran6.html";
			} else if(sectionN == 11) { //Lampiran 7
				url = "file:///android_asset/lampiran7.html";
			}
			wv.loadUrl(url);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
		
	}

}
