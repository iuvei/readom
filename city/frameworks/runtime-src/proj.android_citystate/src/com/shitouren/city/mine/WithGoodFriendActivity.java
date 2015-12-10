package com.shitouren.city.mine;

import com.shitouren.citystate.R;
import com.shitouren.fragment.MineFansFragment;
import com.shitouren.fragment.MineFocusFragment;
import com.shitouren.utils.Utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WithGoodFriendActivity extends FragmentActivity implements OnClickListener {

	//topbar布局
	TextView tvTopbarMiddle,tvTopbarRight;
	ImageView ivTopbarLeft,ivTopbarRight;
	RelativeLayout rlLeftTV,rlRightTV;
	ViewPager twoViewPager;
	View leftViewLine;
	
	private int mWidth;
	
	private PopupWindow window;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mine_good_friend_activity);
		mWidth = Utils.windowXY(this)[0];
		
		/**
		 * 头布局
		 */
		initTopbarView();
		/**
		 * viewpager
		 */
		initViewPager();
	}

	private void initViewPager() {
		// TODO Auto-generated method stub
		rlLeftTV = (RelativeLayout) findViewById(R.id.rlLeftTV);
		rlRightTV = (RelativeLayout) findViewById(R.id.rlRightTV);
		twoViewPager = (ViewPager) findViewById(R.id.twoViewPager);
		leftViewLine = (View) findViewById(R.id.leftViewLine);
		
		rlLeftTV.setOnClickListener(this);
		rlRightTV.setOnClickListener(this);
		twoViewPager.setAdapter(new WithGoodFriendPagerAdater(getSupportFragmentManager()));
		twoViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				int one = mWidth/2;
				Animation animation = null;
				if (arg0 == 0) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				} else if (arg0 == 1) {
					animation = new TranslateAnimation(0, one, 0, 0);
				}
				animation.setFillAfter(true);
				animation.setDuration(200);
				leftViewLine.startAnimation(animation);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void initTopbarView() {
		// TODO Auto-generated method stub
		tvTopbarMiddle = (TextView) findViewById(R.id.tvTopbarMiddle);
		tvTopbarRight = (TextView) findViewById(R.id.tvTopbarRight);
		ivTopbarLeft =  (ImageView) findViewById(R.id.ivTopbarLeft);
		ivTopbarRight =  (ImageView) findViewById(R.id.ivTopbarRight);
		tvTopbarMiddle.setText("好友关系");
		ivTopbarRight.setVisibility(View.VISIBLE);
		ivTopbarRight.setBackgroundResource(R.drawable.sousuo);
		tvTopbarMiddle.setOnClickListener(this);
		ivTopbarRight.setOnClickListener(this);
		ivTopbarLeft.setOnClickListener(this);
	}
	
	class WithGoodFriendPagerAdater extends FragmentStatePagerAdapter{

		public WithGoodFriendPagerAdater(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment fragment = null;
			switch (arg0) {
			case 0:
				fragment = new MineFocusFragment();
				break;
			case 1:
				fragment = new MineFansFragment();
				break;

			default:
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}
		
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.tvTopbarMiddle:
			break;
		case R.id.ivTopbarRight:
			showSearchPW(); 
			break;
		case R.id.ivTopbarLeft:
			finish();
			break;
			
			
		/**
		 * viewpager控制
		 */
		case R.id.rlLeftTV:
			twoViewPager.setCurrentItem(0);
			break;
		case R.id.rlRightTV:
			twoViewPager.setCurrentItem(1);
			break;

		default:
			break;
		}
		
	}

	private void showSearchPW() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.mine_good_friend_search_popupwindow, null);
		
		window = new PopupWindow(view, 
				WindowManager.LayoutParams.MATCH_PARENT, 
				WindowManager.LayoutParams.WRAP_CONTENT);
		// 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
		window.setFocusable(true);
		// 实例化一个ColorDrawable颜色为半透明
	    ColorDrawable dw = new ColorDrawable(0xb0000000);
	    window.setBackgroundDrawable(dw);

	    
	    // 设置popWindow的显示和消失动画
	    window.setAnimationStyle(R.style.mypopwindow_anim_top_style);
	    // 在底部显示
	    window.showAtLocation(WithGoodFriendActivity.this.findViewById(R.id.ivTopbarRight),
	        Gravity.TOP, 0, 30);
		
	}
}