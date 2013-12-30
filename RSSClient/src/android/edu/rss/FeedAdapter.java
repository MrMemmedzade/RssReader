package android.edu.rss;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import nl.matshofman.saxrssreader.RssItem;
import android.content.Context;
import android.edu.rss.utils.ImageLoader;
import android.edu.rss.utils.Utils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rssclient.R;

public class FeedAdapter extends BaseAdapter {

	private ArrayList<RssItem> data;
	private Context mContext;
	public FeedAdapter(ArrayList<RssItem> data, Context context){
		this.data = data;
		mContext = context;
	}
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		FeedHolder holder;
		ImageLoader mImageLoader = new ImageLoader(mContext);
		if(convertView == null){
			convertView = (ViewGroup)inflater.inflate(R.layout.feed_item, null);
			holder = new FeedHolder();
			holder.titleTv = (TextView) convertView.findViewById(R.id.titleTv);
			holder.imageIv = (ImageView) convertView.findViewById(R.id.imageIv);
			convertView.setTag(holder);
		}
		else {
			holder = (FeedHolder)convertView.getTag();
		}
		
		RssItem feed = (RssItem)getItem(position);
		String title = feed.getTitle();
		String imageUrl = Utils.getImageUrl(feed.getDescription());
		
		if(title != null && imageUrl != null){
			holder.titleTv.setText(title);
			mImageLoader.displayImage(imageUrl, holder.imageIv);
		}
		return convertView;
	}
	
	class FeedHolder{
		public TextView titleTv;
		public ImageView imageIv;
	}
}
