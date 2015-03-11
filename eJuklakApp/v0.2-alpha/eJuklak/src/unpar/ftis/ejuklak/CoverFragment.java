package unpar.ftis.ejuklak;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class CoverFragment extends Fragment {
	String link;
	String coverhtml;
	public CoverFragment(String alamat, String konten) {
		// TODO Auto-generated constructor stub
		link = alamat;
		coverhtml = konten;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_bab1 , container, false);
		WebView wv = (WebView)view.findViewById(R.id.webViewBab1);
		
		String HTML = coverhtml;
        WebSettings settings = wv.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
//        wv.loadDataWithBaseURL(null, HTML, "text/html", "utf-8", null);
        wv.loadUrl("file:///android_asset/"+link);
		return view;
	}
}
