package android.edu.rss.fragments;

import com.example.rssclient.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class RSSItemInfo extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_info, container, false);
		
		WebView webView = (WebView) view.findViewById(R.id.webView);
		webView.setWebViewClient(new WebClient());
		webView.loadUrl("http://google.com");
				
		return view;
	}
	
	public void setUrl(String url) {
		Log.e("TAG", url);
		WebView webView = (WebView) getView().findViewById(R.id.webView);
		if(webView == null) {
			Log.e("TAG", "null");
		}
		webView.loadUrl(url);
	}
	
	private class WebClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return false;
		}
	}
}
