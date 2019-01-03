package com.mycompany.pizzanow.ui.DisplayActivities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.mycompany.pizzanow.R;
import com.mycompany.pizzanow.database.entity.PosEntity;
import com.mycompany.pizzanow.ui.Toolbar.ToolbarActivity;
import com.mycompany.pizzanow.viewmodel.POS.PosViewModel;


//diplay the slide move
public class ShowSuccActivity extends ToolbarActivity {

    private static final int NUM_PAGES = 2;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    //data pos
    private PosViewModel posViewModel;
    private PosEntity posEntity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_succ);

        //Récupération du POS
        String posID = "";
        posEntity = (PosEntity) getIntent().getSerializableExtra("serializable_extra");

        posID = posEntity.getIdPos();

        PosViewModel.Factory factoryPos = new PosViewModel.Factory(getApplication(),posID);
        posViewModel = ViewModelProviders.of(this, factoryPos).get(PosViewModel.class);
        posViewModel.getPos().observe(this,  posEntity1-> {
            if(posEntity1 != null){
                setPosEntity(posEntity1);
            }

        });

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    public PosEntity getPosEntity(){
        return posEntity;
    }

    public void setPosEntity(PosEntity pos){
        this.posEntity = pos;
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return new SuccInfoFragment();
            else
                return new SuccDetailsFragment();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        public void goToSecondFragment() {
        }


    }


}
