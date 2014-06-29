package com.example.whatihadforbreakfast;

import com.example.whatihadforbreakfast.GhettoApi.DownloadGoogleSpreadsheetDataListener;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BreakfastActivity extends Activity implements DownloadGoogleSpreadsheetDataListener {
	static final String SPREADSHEET_KEY = "18JyepUBU2-QAF4agQo7BI25fe5gARfxBr5AvBHFkgpg";
	
	// Table description
	static final int ROW_IDX = 1;
	
	enum Columns {
		TITLE,
		PIC_URL,
		BG_COLOR,
		SHOW_AD,
	}
	
	LinearLayout mLinContainer;
	TextView lblTitle;
	ImageView mImg;
	
	View mAdvertisement;

	Picasso mPicasso;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_breakfast);

		mLinContainer = (LinearLayout) findViewById(R.id.lin_container);
		lblTitle = (TextView) findViewById(R.id.lbl_title);
		mImg = (ImageView) findViewById(R.id.img_photo);
		mAdvertisement = findViewById(R.id.lbl_ad);
		
		mPicasso = Picasso.with(this);
		
		(findViewById(R.id.btn_refresh)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				loadData();
				
			}
		});
		
		loadData();
	}
	
	void loadData() {
		GhettoApi.downloadGoogleSpreadsheetData(SPREADSHEET_KEY, BreakfastActivity.this);
	}

	@Override
	public void onSpreadsheetDataLoaded(String csv) {
		final String [][] rowsCols = GhettoApi.parseCsvToRowColData(csv);
		
		// We should have three columns of data; the first row is col names, the second is vals
		if (rowsCols == null || rowsCols[ROW_IDX].length < 3) {
			return;
		}
		
		// First col is title
		final String title = rowsCols[ROW_IDX][Columns.TITLE.ordinal()];
		
		// Second col is pic url
		final String picUrl = rowsCols[ROW_IDX][Columns.PIC_URL.ordinal()];
		
		// Third col is background color
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
					mPicasso.load(picUrl).into(mImg);
				}
				
				// Background color
				try {
					int color = Color.parseColor(bgColor);
					//mImg.setBackgroundColor(color);
					mLinContainer.setBackgroundColor(color);
				} catch (Exception e) {
					Log.v("blarg", "problem with color " + e.getMessage());
				}
				
				// Show ad?
				mAdvertisement.setVisibility(showAd ? View.VISIBLE : View.INVISIBLE);
			}
		});		
		
	}

	@Override
	public void onSpreadsheetDataFailed(String message) {
		// TODO Auto-generated method stub
		
	}

}
