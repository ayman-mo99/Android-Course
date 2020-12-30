package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finaltry.R;
import com.squareup.picasso.Picasso;

import java.net.URI;

public class DetailsActivity extends AppCompatActivity {
private ImageView img;
private TextView tvTitle;
private TextView tvdes;

    private TextView tvloc;
    private TextView tvtype;
    private TextView tvcap;
    private TextView tvprice;
Bundle ex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
       img = (ImageView) findViewById(R.id.det_img);
       tvTitle = (TextView)findViewById(R.id.det_title);
       tvdes = (TextView)findViewById(R.id.det_des);

        tvloc = (TextView)findViewById(R.id.det_loc);
        tvtype = (TextView)findViewById(R.id.det_type);
        tvcap = (TextView)findViewById(R.id.det_cap);
        tvprice = (TextView)findViewById(R.id.det_price);


       ex = getIntent().getExtras();
       if(ex!=null){
           tvTitle.setText(ex.getString("title"));
           tvdes.setText(ex.getString("desc"));

           tvloc.setText(ex.getString("location"));
           tvtype.setText(ex.getString("type"));
           tvcap.setText(ex.getString("capacity"));
           tvprice.setText(ex.getString("price"));

           Uri i = Uri.parse(ex.getString("image")) ;
           //img.setImageURI( i);
           Picasso.with(this)
                   .load(i)
                   .into(img);
       }


    }
}