package metropolia.project.utility;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class WifiScanner implements Runnable {

	private Handler handler;
	private Context ct;
	private boolean scan = true;

	public WifiScanner(Handler handler, Context ct) {
		this.handler = handler;
		this.ct = ct;
	}

	@Override
	public void run() {

		try {
			while (scan) {

				// Do scanning magic here

				WifiManager wm = (WifiManager) ct
						.getSystemService(Context.WIFI_SERVICE);

				Message msg = new Message();
				msg.obj = wm.getConnectionInfo();
				msg.what = 0;
				handler.sendMessage(msg);
				Thread.sleep(1000);
			}
		} catch (Exception e) {

			Log.d("ScannerThread: ", e.getMessage());
		}

	}
}
