package com.example.example3;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Smart Phone Sensing Example 3. Object movement.
 */
public class MainActivity extends Activity implements OnClickListener {

    /**
     * The buttons.
     */
    private Button up, left, right, down;
    /**
     * The images.
     */
    private ImageView image, background;
    /**
     * The text view.
     */
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set the buttons
        up = (Button) findViewById(R.id.button1);
        left = (Button) findViewById(R.id.button2);
        right = (Button) findViewById(R.id.button3);
        down = (Button) findViewById(R.id.button4);

        // set the images
        image = (ImageView) findViewById(R.id.imageView1);
        background = (ImageView) findViewById(R.id.imageView2);

        // set the text view
        textView = (TextView) findViewById(R.id.textView1);

        // set listeners
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        left.setOnClickListener(this);
        right.setOnClickListener(this);

        // get the screen width
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        // create lay out parameters
        MarginLayoutParams marginParams = new MarginLayoutParams(
                image.getLayoutParams());

        // we only set the left and top margins in this example.
        // We do this so that we can determine if the image is still
        // within the margins of our background image.
        // We set the margins to be at the center of our background image.
        marginParams.setMargins(width / 2 - 100, 220, 0, 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                marginParams);
        // set layout parameters
        image.setLayoutParams(layoutParams);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        // This happens when you click any of the four buttons.
        // For each of the buttons, when it is clicked we change:
        // - The text in the center of the buttons
        // - The top or left margin
        // - The text that shows to margin
        switch (v.getId()) {
            // UP BUTTON
            case R.id.button1: {
                Toast.makeText(getApplication(), "UP", Toast.LENGTH_SHORT).show();
                RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) image
                        .getLayoutParams();
                mParams.topMargin -= 20;
                textView.setText("\n\tMove Up" + "\n\tTop Margin = "
                        + mParams.topMargin);
                image.setLayoutParams(mParams);
                break;
            }
            // DOWN BUTTON
            case R.id.button4: {
                Toast.makeText(getApplication(), "DOWN", Toast.LENGTH_SHORT).show();
                RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) image
                        .getLayoutParams();
                mParams.topMargin += 20;
                textView.setText("\n\tMove Down" + "\n\tTop Margin = "
                        + mParams.topMargin);
                image.setLayoutParams(mParams);
                break;
            }
            // LEFT BUTTON
            case R.id.button2: {
                Toast.makeText(getApplication(), "LEFT", Toast.LENGTH_SHORT).show();
                RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) image
                        .getLayoutParams();
                mParams.leftMargin -= 20;
                textView.setText("\n\tMove Left" + "\n\tLeft Margin = "
                        + mParams.leftMargin);
                image.setLayoutParams(mParams);
                break;
            }
            // RIGHT BUTTON
            case R.id.button3: {
                Toast.makeText(getApplication(), "RIGHT", Toast.LENGTH_SHORT)
                        .show();
                RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) image
                        .getLayoutParams();
                mParams.leftMargin += 20;
                textView.setText("\n\tMove Right" + "\n\tLeft Margin = "
                        + mParams.leftMargin);
                image.setLayoutParams(mParams);
                break;
            }
        }

        // update layout parameters
        RelativeLayout.LayoutParams mParams = (RelativeLayout.LayoutParams) image
                .getLayoutParams();

        // When the image falls within the background image, it is visible. Otherwise, it is invisible.
        if ((mParams.topMargin < background.getTop() || mParams.topMargin > background.getBottom() - 1.5 * image.getHeight())
                || (mParams.leftMargin < background.getLeft() || mParams.leftMargin > background.getRight() - 1.5 * image.getWidth())) {
            image.setVisibility(View.INVISIBLE);
        } else {
            image.setVisibility(View.VISIBLE);
        }
    }
}