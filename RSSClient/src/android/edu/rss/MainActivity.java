package android.edu.rss;

import android.edu.rss.fragments.RSSItemInfo;
import android.edu.rss.fragments.RSSListFragment.OnRSSItemSelected;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.rssclient.R;

public class MainActivity extends FragmentActivity implements OnRSSItemSelected {

	RSSItemInfo fragmentInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onRSSItemSelected(String url) {
		View view = findViewById(R.id.frameInfo);
		if (fragmentInfo == null) {
			fragmentInfo = new RSSItemInfo();
		}
		
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		
		if (view != null) {
			transaction.add(R.id.frameInfo, fragmentInfo);
			transaction.addToBackStack(null);
		} else {
			transaction.replace(R.id.fragmentList, fragmentInfo);
			transaction.addToBackStack("List");
		}
		
		transaction.commit();
		manager.executePendingTransactions();
		
		fragmentInfo.setUrl(url);
	}

}
