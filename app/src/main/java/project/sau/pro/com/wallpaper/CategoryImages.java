package project.sau.pro.com.wallpaper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.srx.widget.PullToLoadView;

import project.sau.pro.com.wallpaper.paginator.Paginator;
import project.sau.pro.com.wallpaper.paginator_categories.Paginator_categories;
import project.sau.pro.com.wallpaper.paginator_categoryimage.Paginator_categoryimage;

public class CategoryImages extends AppCompatActivity {

    PullToLoadView category_images;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_image);
        category_images=findViewById(R.id.category_images);

        String string =getIntent().getStringExtra("name");
        Toast.makeText(this, "cate id "+string, Toast.LENGTH_SHORT).show();

        new Paginator_categoryimage(getApplicationContext(),category_images,string);




    }
}
