package unpar.ftis.ejuklak;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

public class Bab2Fragment extends Fragment {
	String link;
	String bab2html;
	public Bab2Fragment(String al, String konten) {
		// TODO Auto-generated constructor stub
		link = al;
		bab2html = konten;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_bab2 , container, false);
		WebView wv = (WebView)view.findViewById(R.id.webViewBab2);
//		wv.getSettings().setLoadWithOverviewMode(true);
//		wv.getSettings().setUseWideViewPort(true);
//		wv.getSettings().setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
		
		String HTML = bab2html;
		WebSettings settings = wv.getSettings();
        
        settings.setDefaultTextEncodingName("utf-8");
//        wv.loadDataWithBaseURL(null, HTML, "text/html", "utf-8", null);
        wv.loadUrl("file:///android_asset/"+link);
		return view;
	}
}
