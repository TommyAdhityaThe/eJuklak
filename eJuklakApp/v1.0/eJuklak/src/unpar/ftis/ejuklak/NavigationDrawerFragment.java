package unpar.ftis.ejuklak;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Fragment used for managing interactions for and presentation of a navigation
 * drawer. See the <a href=
 * "https://developer.android.com/design/patterns/navigation-drawer.html#Interaction"
 * > design guidelines</a> for a complete explanation of the behaviors
 * implemented here.
 * @author Henry Bagus Hermawan
 * @author Alexander Indrawan
 * @author Ivan Lukman
 * @author Tommy Adhitya The
 * @author Tevin Odysseus
 * @version 1.0
 */
public class NavigationDrawerFragment extends Fragment {

	/**
	 * Remember the position of the selected item.
	 */
	private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

	/**
	 * Per the design guidelines, you should show the drawer on launch until the
	 * user manually expands it. This shared preference tracks this.
	 */
	private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

	/**
	 * A pointer to the current callbacks instance (the Activity).
	 */
	private NavigationDrawerCallbacks mCallbacks;

	/**
	 * Helper component that ties the action bar to the navigation drawer.
	 */
	private ActionBarDrawerToggle mDrawerToggle;

	private DrawerLayout mDrawerLayout;
	private ExpandableListView mDrawerListView;
	private View mFragmentContainerView;

	private int mCurrentSelectedPosition = 0;
	private boolean mFromSavedInstanceState;
	private boolean mUserLearnedDrawer;
	
	public int group = 0;
	public int child = 0;
	Activity a;
	public NavigationDrawerFragment() {
	}
	public void setActivity(MainActivity ma) {
		this.a = ma;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Read in the flag indicating whether or not the user has demonstrated
		// awareness of the
		// drawer. See PREF_USER_LEARNED_DRAWER for details.
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
		mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

		if (savedInstanceState != null) {
			mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
			mFromSavedInstanceState = true;
		}

		// Select either the default item (0) or the last selected item.
		selectItem(mCurrentSelectedPosition, 0);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Indicate that this fragment would like to influence the set of
		// actions in the action bar.
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mDrawerListView = (ExpandableListView) inflater.inflate( R.layout.fragment_navigation_drawer, container, false);
		
		//set listener for the each item clicked
		mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						selectItem(position, 0);
					}
				});
		
		//set listener for the each child of items clicked
		mDrawerListView.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				selectItem(groupPosition, childPosition);
				return false;
			}
		});
		
		
		/**
		 * List of DrawerItem to contain the navigation drawer items
		 */
		ArrayList<DrawerItem> al = new ArrayList<DrawerItem>();
		
		DrawerItem bab0 = new DrawerItem();
		bab0.name = "Cover";
		bab0.innerList.add("KATA PENGANTAR");
		al.add(bab0);
		
		DrawerItem bab1 = new DrawerItem();
		bab1.name = "Bab 1";
		bab1.innerList.add("PENDAHULUAN");
		bab1.innerList.add("1.1:\nSejarah Fakultas Teknologi Informasi dan Sains");
		bab1.innerList.add("1.2:\nVisi, Misi, Tujuan, dan Sasaran FTIS");
		bab1.innerList.add("1.3:\nKeberhasilan FTIS");
		bab1.innerList.add("1.4:\nPengelola Fakultas");
		bab1.innerList.add("1.5:\nDaftar Dosen FTIS");
		al.add(bab1);
		
		DrawerItem bab2 = new DrawerItem();
		bab2.name = "Bab 2";
		bab2.innerList.add("PENYELENGGARAAN MATA KULIAH");
		bab2.innerList.add("2.1:\nMatakuliah Pilihan");
		bab2.innerList.add("2.2:\nMatakuliah Prasyarat");
		bab2.innerList.add("2.3:\nMatakuliah Layanan");
		bab2.innerList.add("2.4:\nMatakuliah Umum");
		bab2.innerList.add("2.5:\nKurikulum Program Studi Matematika");
		bab2.innerList.add("2.6:\nKurikulum Program Studi Fisika");
		bab2.innerList.add("2.7:\nKurikulum Program Studi Teknik Informatika");
		al.add(bab2);
		
		DrawerItem bab3 = new DrawerItem();
		bab3.name = "Bab 3";
		bab3.innerList.add("KEGIATAN AKADEMIK");
		bab3.innerList.add("3.1:\nPenyusunan Rencana Studi");
		bab3.innerList.add("3.2:\nKegiatan Perkuliahan");
		bab3.innerList.add("3.3:\nTata Cara Ujian");
		bab3.innerList.add("3.4:\nCuti dan Gencat Studi");
		bab3.innerList.add("3.5:\nPengunduran Diri Sebagai Mahasiswa");
		al.add(bab3);
		
		DrawerItem bab4 = new DrawerItem();
		bab4.name = "Bab 4";
		bab4.innerList.add("EVALUASI KEBERHASILAN BELAJAR");
		bab4.innerList.add("4.1:\nEvaluasi Keberhasilan Belajar Tiap Mata Kuliah");
		bab4.innerList.add("4.2:\nEvaluasi Keberhasilan Belajar Dalam Suatu Tahap Belajar");
		bab4.innerList.add("4.3:\nKemampuan Bahasa Inggris Mahasiswa UNPAR");
		al.add(bab4);
		
		DrawerItem lam1 = new DrawerItem();
		lam1.name = "Lampiran 1";
		lam1.innerList.add("JADWAL AKADEMIK TAHUN AKADEMIK 2014/2015");
		al.add(lam1);
		
		DrawerItem lam2 = new DrawerItem();
		lam2.name = "Lampiran 2";
		lam2.innerList.add("JADWAL PEMBAYARAN BIAYA STUDI TAHUN AKADEMIK 2014/2015");
		al.add(lam2);
		
		DrawerItem lam3 = new DrawerItem();
		lam3.name = "Lampiran 3";
		lam3.innerList.add("SIDANG SARJANA DAN WISUDA TAHUN AKADEMIK 2014/2015");
		al.add(lam3);
		
		DrawerItem lam4 = new DrawerItem();
		lam4.name = "Lampiran 4";
		lam4.innerList.add("KEPUTUSAN REKTOR UNPAR TENTANG EVALUASI KEBERHASILAN BELAJAR DALAM MATA "
				+ "KULIAH DAN EVALUASI TAHAP KEBERHASILAN BELAJAR PADA PROGRAM SARJANA DAN DIPLOMA III "
				+ "DI LINGKUNGAN UNIVERSITAS KATOLIK PARAHYANGAN");
		al.add(lam4);
		
		DrawerItem lam5 = new DrawerItem();
		lam5.name = "Lampiran 5";
		lam5.innerList.add("KEPUTUSAN REKTOR UNPAR TENTANG STANDAR KEMAMPUAN BAHASA INGGRIS MAHASISWA "
				+ "UNIVERSITAS KATOLIK PARAHYANGAN");
		al.add(lam5);
		
		DrawerItem lam6 = new DrawerItem();
		lam6.name = "Lampiran 6";
		lam6.innerList.add("KEPUTUSAN REKTOR UNPAR TENTANG ATURAN BAGI MAHASISWA YANG CUTI STUDI DAN "
				+ "MAHASISWA YANG TIDAK AKTIF PADA PROGRAM SARJANA DAN PROGRAM DIPLOMA III DI "
				+ "UNIVERSITAS KATOLIK PARAHYANGAN");
		al.add(lam6);
		
		DrawerItem lam7 = new DrawerItem();
		lam7.name = "Lampiran 7";
		lam7.innerList.add("KEPUTUSAN REKTOR UNPAR TENTANG PERATURAN TATA TERTIB MAHASISWA DAN "
				+ "PROSEDUR PENJATUHAN SANKSI");
		al.add(lam7);
		
		/**
		 * implement the adapter
		 */
		ExpandableAdapter adapter = new ExpandableAdapter(getActivity(), al);
		mDrawerListView.setAdapter(adapter);
		mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
		
		return mDrawerListView;
	}

	public boolean isDrawerOpen() {
		return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
	}

	/**
	 * Users of this fragment must call this method to set up the navigation
	 * drawer interactions.
	 *
	 * @param fragmentId
	 *            The android:id of this fragment in its activity's layout.
	 * @param drawerLayout
	 *            The DrawerLayout containing this fragment's UI.
	 */
	public void setUp(int fragmentId, DrawerLayout drawerLayout) {
		mFragmentContainerView = getActivity().findViewById(fragmentId);
		mDrawerLayout = drawerLayout;

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the navigation drawer and the action bar app icon.
		mDrawerToggle = new ActionBarDrawerToggle(getActivity(), /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.navigation_drawer_open, /*
										 * "open drawer" description for
										 * accessibility
										 */
		R.string.navigation_drawer_close /*
										 * "close drawer" description for
										 * accessibility
										 */
		) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				if (!isAdded()) {
					return;
				}

				getActivity().invalidateOptionsMenu(); // calls
														// onPrepareOptionsMenu()
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				if (!isAdded()) {
					return;
				}

				if (!mUserLearnedDrawer) {
					// The user manually opened the drawer; store this flag to
					// prevent auto-showing
					// the navigation drawer automatically in the future.
					mUserLearnedDrawer = true;
					SharedPreferences sp = PreferenceManager
							.getDefaultSharedPreferences(getActivity());
					sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true)
							.apply();
				}

				getActivity().invalidateOptionsMenu(); // calls
														// onPrepareOptionsMenu()
			}
		};

		// If the user hasn't 'learned' about the drawer, open it to introduce
		// them to the drawer,
		// per the navigation drawer design guidelines.
		if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
			mDrawerLayout.openDrawer(mFragmentContainerView);
		}

		// Defer code dependent on restoration of previous instance state.
		mDrawerLayout.post(new Runnable() {
			@Override
			public void run() {
				mDrawerToggle.syncState();
			}
		});

		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private void selectItem(int groupPosition, int childPosition) {
		group = groupPosition;
		((MainActivity)getActivity()).child = childPosition;
		mCurrentSelectedPosition = groupPosition;
		if (mDrawerListView != null) {
			mDrawerListView.setItemChecked(groupPosition, true);
		}
		if (mDrawerLayout != null) {
			mDrawerLayout.closeDrawer(mFragmentContainerView);
		}
		if (mCallbacks != null) {
			mCallbacks.onNavigationDrawerItemSelected(groupPosition);
		}
		
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallbacks = (NavigationDrawerCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					"Activity must implement NavigationDrawerCallbacks.");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Forward the new configuration the drawer toggle component.
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// If the drawer is open, show the global app actions in the action bar.
		// See also
		// showGlobalContextActionBar, which controls the top-left area of the
		// action bar.
		if (mDrawerLayout != null && isDrawerOpen()) {
			inflater.inflate(R.menu.global, menu);
			showGlobalContextActionBar();
		}
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * Per the navigation drawer design guidelines, updates the action bar to
	 * show the global app 'context', rather than just what's in the current
	 * screen.
	 */
	private void showGlobalContextActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setTitle(R.string.app_name);
	}

	private ActionBar getActionBar() {
		return getActivity().getActionBar();
	}

	/**
	 * Callbacks interface that all activities using this fragment must
	 * implement.
	 */
	public static interface NavigationDrawerCallbacks {
		/**
		 * Called when an item in the navigation drawer is selected.
		 */
		void onNavigationDrawerItemSelected(int position);
	}
}
