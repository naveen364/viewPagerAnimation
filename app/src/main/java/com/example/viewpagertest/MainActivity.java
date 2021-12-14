package com.example.viewpagertest;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<model> list;
    Context c;
    LinearLayout dotLayout;
    TextView[] dots;
    CardView crd;
    RelativeLayout rview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager2 = findViewById(R.id.viewpagers);
        dotLayout = findViewById(R.id.dot);
        crd = findViewById(R.id.crd);
        rview = findViewById(R.id.rview);
        try {
            list = new ArrayList<>();
            list.add(new model("Anime", R.drawable.anime, R.drawable.animeb));
            list.add(new model("Abstract", R.drawable.abs, R.drawable.absb));
            list.add(new model("Animal", R.drawable.animal, R.drawable.animalb));

            adapter adapters = new adapter(list,this);
            viewPager2.setAdapter(adapters);

            dots = new TextView[list.size()];
            dotIndicator(list.size());

            viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    Glide.with(MainActivity.this).load(list.get(position).getMain_back()).into(new CustomTarget<Drawable>(){

                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                rview.setBackground(resource);
                            }
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
                    selectedindicator(position);
                    viewPager2.setPageTransformer(new ViewPager2.PageTransformer() {
                        @Override
                        public void transformPage(@NonNull View page, float position) {
                            float myoffset = position * -(2 * page.getPaddingLeft());
                            if(position<-1){
                                page.setTranslationY(-myoffset);
                            } else if(position<=1) {
                                float scaleFactor = Math.max(0.7f,1-Math.abs(position));
                                page.setScaleY(scaleFactor);
                                page.setTranslationY(myoffset);
                                page.setAlpha(scaleFactor);
                            } else {
                                page.setAlpha(0);
                                page.setTranslationY(myoffset);

                            }
                        }
                    });
                    super.onPageSelected(position);
                }

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    super.onPageScrollStateChanged(state);
                }
            });
            viewPager2.setOffscreenPageLimit(3);
            viewPager2.setClipToPadding(false);
            viewPager2.setClipChildren(false);
            viewPager2.setPadding(300,0,300,0);
        }catch (IndexOutOfBoundsException e){
            
        }

    }

    private void selectedindicator(int position) {
        try {
            for (int i = 0; i <= dots.length; i++) {
                if (i == position) {
                    dots[i].setTextColor(getResources().getColor(R.color.teal_700));
                } else {
                    dots[i].setTextColor(getResources().getColor(R.color.black));
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }
    }

    public void dotIndicator(int size){
        for(int i=0;i<size;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#9679;"));
            dots[i].setTextSize(18);
            dotLayout.addView(dots[i]);
        }
    }
}