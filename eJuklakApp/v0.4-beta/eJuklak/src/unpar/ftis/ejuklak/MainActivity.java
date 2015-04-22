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
	
	private WebView web;
	public static int child = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//fullscreen
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_main);
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mNavigationDrawerFragment.setActivity(this);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		
		
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container,
						WebViewFragment.newInstance((position),child
								)).commit();
	}

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
	 */
	public static class WebViewFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";
		private static int sectionN;
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

		public WebViewFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			WebView wv = (WebView)rootView.findViewById(R.id.webViewContent);
			wv.setBackgroundColor(Color.TRANSPARENT);
			wv.getSettings().setBuiltInZoomControls(true);
			wv.getSettings().setDisplayZoomControls(true);
//			wv.setBackgroundResource(R.drawable.bening);
			
			//edit rotation handling di manifest.
			
			String url = "file:///android_asset/pendahuluan.html";
			if (sectionN == 0) { //pendahuluan
				//no anchor
			} else if(sectionN == 1) { //Bab 1
				url = "file:///android_asset/bab1.html";
				if (child == 0) {//1.1
					url = "file:///android_asset/bab1.html#bab-1-pendahuluan";
				} else if (child == 1) {//1.2
					url = "file:///android_asset/bab1.html#visi-misi-tujuan-dan-sasaran-ftis";
				} else if (child == 2) {//1.3
					url = "file:///android_asset/bab1.html#keberhasilan-ftis";
				} else if (child == 3) {//1.4
					url = "file:///android_asset/bab1.html#pengelola-fakultas";
				} else if (child == 4) {//1.5
					url = "file:///android_asset/bab1.html#daftar-dosen-ftis";
				}
			} else if(sectionN == 2) { //Bab 2
				url = "file:///android_asset/bab2.html";
				if (child == 0) {//2.1
					url = "file:///android_asset/bab2.html#bab-2-penyelenggaraan-mata-kuliah";
				} else if (child == 1) {//2.2
					url = "file:///android_asset/bab2.html#matakuliah-prasyarat";
				} else if (child == 2) {//2.3
					url = "file:///android_asset/bab2.html#matakuliah-layanan";
				} else if (child == 3) {//2.4
					url = "file:///android_asset/bab2.html#matakuliah-umum";
				} else if (child == 4) {//2.5
					url = "file:///android_asset/bab2.html#kurikulum-program-studi-matematika";
				} else if (child == 5) {//2.6
					url = "file:///android_asset/bab2.html#kurikulum-program-studi-fisika";
				} else if (child == 6) {//2.7
					url = "file:///android_asset/bab2.html#kurikulum-program-studi-teknik-informatika";
				}
			} else if(sectionN == 3) { //Bab 3
				url = "file:///android_asset/bab3.html";
				if (child == 0) {//3.1
					url = "file:///android_asset/bab3.html#bab-3-kegiatan-akademik";
				} else if (child == 1) {//3.2
					url = "file:///android_asset/bab3.html#kegiatan-perkuliahan";
				} else if (child == 2) {//3.3
					url = "file:///android_asset/bab3.html#tata-cara-ujian";
				} else if (child == 3) {//3.4
					url = "file:///android_asset/bab3.html#cuti-dan-gencat-studi";
				} else if (child == 4) {//3.5
					url = "file:///android_asset/bab3.html#pengunduran-diri-sebagai-mahasiswa";
				}
			} else if(sectionN == 4) { //Bab 4
				url = "file:///android_asset/bab4.html";
				if (child == 0) {//4.1
					url = "file:///android_asset/bab4.html#bab-4-evaluasi-keberhasilan-belajar";
				} else if (child == 1) {//4.2
					url = "file:///android_asset/bab4.html#evaluasi-keberhasilan-belajar-dalam-suatu-tahap-belajar";
				} else if (child == 2) {//4.3
					url = "file:///android_asset/bab4.html#kemampuan-bahasa-inggris-mahasiswa-unpar";
				} else if (child == 3) {//LAMPIRAN
					url = "file:///android_asset/bab4.html#lampiran-1";
				}
			}
			wv.loadUrl(url);
			
			
//			Toast.makeText(getActivity(), "number "+sectionN+", child "+childPosition, Toast.LENGTH_SHORT).show();
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
