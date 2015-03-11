package unpar.ftis.ejuklak;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Bab3Fragment extends Fragment {
	String link;
	String bab3html;
	public Bab3Fragment(String al, String konten) {
		// TODO Auto-generated constructor stub
		link = al;
		bab3html = konten;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_bab3 , container, false);
		WebView wv = (WebView)view.findViewById(R.id.webViewBab3);
		
		String HTML = bab3html;
        WebSettings settings = wv.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
//        wv.loadDataWithBaseURL(null, HTML, "text/html", "utf-8", null);
        wv.loadUrl("file:///android_asset/"+link);
		return view;
	}
}
