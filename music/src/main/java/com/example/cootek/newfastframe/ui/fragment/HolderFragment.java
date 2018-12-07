package com.example.cootek.newfastframe.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;

import com.example.commonlibrary.BaseFragment;
import com.example.commonlibrary.baseadapter.adapter.ViewPagerAdapter;
import com.example.commonlibrary.cusotomview.ToolBarOption;
import com.example.commonlibrary.cusotomview.ViewPagerIndicator;
import com.example.commonlibrary.cusotomview.WrappedViewPager;
import com.example.cootek.newfastframe.R;
import com.example.cootek.newfastframe.base.MusicBaseFragment;
import com.example.cootek.newfastframe.ui.MainActivity;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by COOTEK on 2017/8/16.
 */

public class HolderFragment extends MusicBaseFragment implements View.OnClickListener {


    private ViewPagerIndicator viewPagerIndicator;
    private WrappedViewPager display;
    private View expend;

    @Override
    public void updateData(Object o) {

    }

    @Override
    protected boolean isNeedHeadLayout() {
        return true;
    }

    @Override
    protected boolean isNeedEmptyLayout() {
        return false;
    }


    @Override
    protected boolean needStatusPadding() {
        return true;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_holder;
    }

    @Override
    protected void initView() {
        display = (WrappedViewPager) findViewById(R.id.vp_fragment_holder_display);
        ToolBarOption toolBarOption = new ToolBarOption();
        toolBarOption.setCustomView(getToolBarView());
        toolBarOption.setBgColor(getResources().getColor(R.color.light_blue_600));
        setToolBar(toolBarOption);
    }

    private View getToolBarView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_fragment_holder_header_view, null, false);
        viewPagerIndicator = view.findViewById(R.id.vpi_view_fragment_holder_header_view_indicator);
        expend = view.findViewById(R.id.iv_view_Fragment_holder_header_view_expend);
        expend.setOnClickListener(this);
        view.findViewById(R.id.iv_view_Fragment_holder_header_view_search).setOnClickListener(this);
        return view;
    }

    @Override
    protected void initData() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        List<String> titleList = new ArrayList<>();
        titleList.add("首页");
        titleList.add("排行榜");
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(ListenerFragment.newInstance());
        fragments.add(RankFragment.newInstance());
        viewPagerAdapter.setTitleAndFragments(titleList, fragments);
        display.setAdapter(viewPagerAdapter);
        display.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    ((MainActivity) getActivity()).notifyIntercept(false);
                } else {
                    ((MainActivity) getActivity()).notifyIntercept(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPagerIndicator.setViewPager(display, 0);
    }

    @Override
    protected void updateView() {
        display.setCurrentItem(0);
    }

    public static HolderFragment newInstance() {

        return new HolderFragment();
    }

    @Override
    public void onClick(View v) {
         if (v.getId() == R.id.iv_view_Fragment_holder_header_view_expend) {
            ((MainActivity) getActivity()).switchMenu();
        }
    }

    public void onDrag(float delta) {
        ViewHelper.setAlpha(expend, (1 - delta));
    }
}
