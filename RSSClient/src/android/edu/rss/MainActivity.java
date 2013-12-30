package android.edu.rss;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

import org.xml.sax.SAXException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.rssclient.R;

public class MainActivity extends Activity {

	private ListView feedList;
	private ProgressBar pbLoad;
	
	private final String url = "http://9gagrss.com/feed/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        feedList = (ListView)findViewById(R.id.feedList);
        pbLoad = (ProgressBar)findViewById(R.id.pbLoad);
        new GetRSSFeedTask().execute(url);
    }
    
    private void updateFeedList(ArrayList<RssItem> items){
    	feedList.setAdapter(new FeedAdapter(items, this));
    	showList();
    }
    
    private void showList(){
    	pbLoad.setVisibility(View.GONE);
    	feedList.setVisibility(View.VISIBLE);
    }
    
    private void showProgress(){
    	pbLoad.setVisibility(View.VISIBLE);
    	feedList.setVisibility(View.GONE);
    }
    
    class GetRSSFeedTask extends AsyncTask<String, Void, RssFeed>{

    	@Override
    	protected void onPreExecute() {
    		showProgress();
    		super.onPreExecute();
    	}
    	@Override
    	protected RssFeed doInBackground(String... params) {
    		RssFeed feed = null;
            try {
            	URL url = new URL(params[0]);
    			feed = RssReader.read(url);
    		} catch (SAXException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
            return feed;
    	}
    	
    	@Override
    	protected void onPostExecute(RssFeed feed) {
    		super.onPostExecute(feed);
    		if(feed == null)
    			return;
    		updateFeedList(feed.getRssItems());
    	}
    }
}

