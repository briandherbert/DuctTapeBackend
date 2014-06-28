package com.example.whatihadforbreakfast;

import com.example.whatihadforbreakfast.GhettoApi.DownloadGoogleSpreadsheetDataListener;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BreakfastActivity extends Activity implements DownloadGoogleSpreadsheetDataListener {
	static final String SPREADSHEET_KEY = "18JyepUBU2-QAF4agQo7BI25fe5gARfxBr5AvBHFkgpg";
	
	LinearLayout mLinContainer;
	TextView lblTitle;
	ImageView mImg;

	Picasso mPicasso;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_breakfast);

		mLinContainer = (LinearLayout) findViewById(R.id.lin_container);
		lblTitle = (TextView) findViewById(R.id.lbl_title);
		mImg = (ImageView) findViewById(R.id.img_photo);
		
		mPicasso = Picasso.with(this);
		
		GhettoApi.downloadGoogleSpreadsheetData(SPREADSHEET_KEY, BreakfastActivity.this);
	}

	@Override
	public void onSpreadsheetDataLoaded(String csv) {
		final String [][] rowsCols = GhettoApi.parseCsvToRowColData(csv);
		
		// We should have three columns of data
		if (rowsCols == null || rowsCols[0].length < 3) {
			return;
		}
		
		// First col is title
		final String title = rowsCols[0][0];
		
		// Second col is pic url
		final String picUrl = rowsCols[0][1];
		
		// Third col is background color
		final String bgColor = rowsCols[0][2];
		
				
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				if (title != null) {
					lblTitle.setText(title);
				}
				
				if (picUrl != null) {
					mPicasso.load(picUrl).into(mImg);
				}
				
				try {
					int color = Color.parseColor(bgColor);
					mImg.setBackgroundColor(color);
					mLinContainer.setBackgroundColor(color);
				} catch (Exception e) {
					Log.v("blarg", "problem with color " + e.getMessage());
				}
				
				
			}
		});		
		
	}

	@Override
	public void onSpreadsheetDataFailed(String message) {
		// TODO Auto-generated method stub
		
	}

}
