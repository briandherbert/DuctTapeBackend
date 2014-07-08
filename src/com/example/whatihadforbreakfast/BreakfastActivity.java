package com.example.whatihadforbreakfast;

import com.example.whatihadforbreakfast.DuctTapeBackend.DownloadGoogleSpreadsheetDataListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Picasso.LoadedFrom;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Quick-and-dirty activity demonstrating how to implement {@link DuctTapeBackend}
 * 
 * The title, image url, background color, and ad presence are configurable by
 * changing the respective columns in the {@link #ROW_IDX} row of a Google doc
 * 
 * @author brianherbert <briandherbert@gmail.com>
 * 
 */
public class BreakfastActivity extends Activity implements
		DownloadGoogleSpreadsheetDataListener {
	static final String TAG = BreakfastActivity.class.getSimpleName();

	//static final String SPREADSHEET_KEY = "18JyepUBU2-QAF4agQo7BI25fe5gARfxBr5AvBHFkgpg";

	// Table description
	static final int ROW_IDX = 1;

	enum Columns {
		TITLE, PIC_URL, BG_COLOR, SHOW_AD,
	}

	LinearLayout mLinContainer;
	TextView lblTitle;
	ImageView mImg;
	View mAdvertisement;

	Picasso mPicasso;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    Log.v(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_breakfast);

		mLinContainer = (LinearLayout) findViewById(R.id.lin_container);
		lblTitle = (TextView) findViewById(R.id.lbl_title);
		mImg = (ImageView) findViewById(R.id.img_photo);
		mAdvertisement = findViewById(R.id.lbl_ad);

		mPicasso = Picasso.with(this);

		(findViewById(R.id.btn_refresh))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						loadData();
					}
				});

		loadData();
	}

	/**
	 * Download the Google Doc spreadsheet as csv
	 */
	void loadData() {
		DuctTapeBackend.downloadGoogleSpreadsheetData("18JyepUBU2-QAF4agQo7BI25fe5gARfxBr5AvBHFkgpg",
				BreakfastActivity.this);
	}

	@Override
	public void onSpreadsheetDataLoaded(String csv) {
		final String[][] rowsCols = DuctTapeBackend.parseCsvToRowColData(csv);

		// We should have three columns of data; the first row is col names, the
		// second is vals
		if (rowsCols == null
				|| rowsCols[ROW_IDX].length < Columns.values().length) {
			return;
		}

		final String title = rowsCols[ROW_IDX][Columns.TITLE.ordinal()];

		final String picUrl = rowsCols[ROW_IDX][Columns.PIC_URL.ordinal()];

		final String bgColor = rowsCols[ROW_IDX][Columns.BG_COLOR.ordinal()];

		String showAdStr = rowsCols[ROW_IDX][Columns.SHOW_AD.ordinal()];
		final boolean showAd = "TRUE".equalsIgnoreCase(showAdStr);

		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// Set title
				if (title != null) {
					lblTitle.setText(title);
				}

				// Pic
				if (picUrl != null) {
					mPicasso.load(picUrl).into(new Target() {
                        
                        @Override
                        public void onPrepareLoad(Drawable arg0) {
                            // TODO Auto-generated method stub                            
                        }
                        
                        @Override
                        public void onBitmapLoaded(Bitmap bmp, LoadedFrom arg1) {
                            Log.v(TAG, "Loaded image");
                            mImg.setImageBitmap(bmp);                            
                        }
                        
                        @Override
                        public void onBitmapFailed(Drawable arg0) {
                            Log.w(TAG, "Failed to load image at " + picUrl);
                            Toast.makeText(BreakfastActivity.this, "Image failed to download!", Toast.LENGTH_LONG).show();                            
                        }
                    });
				}

				// Background color
				try {
					int color = Color.parseColor(bgColor);
					// mImg.setBackgroundColor(color);
					mLinContainer.setBackgroundColor(color);
				} catch (Exception e) {
					Log.v(TAG, "problem with color " + e.getMessage());
				}

				// Show ad?
				mAdvertisement.setVisibility(showAd ? View.VISIBLE
						: View.INVISIBLE);
			}
		});

	}

	@Override
	public void onSpreadsheetDataFailed(String message) {
		// TODO Auto-generated method stub

	}

}
