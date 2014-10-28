package com.bdlions.test;
import com.bdlions.load.image.ImageLoader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;


public class ActivityLoadImage extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_image_loader);
        ImageLoader imageLoader = new ImageLoader(getApplicationContext());
        ImageView imageView = (ImageView) findViewById(R.id.imageViewTest);
        imageLoader.DisplayImage("https://pbs.twimg.com/profile_images/1701796334/TA-New-Logo_normal.jpg", imageView);
    }
    
}