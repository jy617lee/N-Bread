/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yunihuani.accountmanager.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yunihuani.accountmanager.R;
import com.yunihuani.accountmanager.component.SlidingTabLayout;

public class SlidingTabsBasicFragment extends Fragment {

    static final String LOG_TAG = "SlidingTabsBasicFragment";

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_sliding, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter(getActivity().getSupportFragmentManager()));

        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    class SamplePagerAdapter extends FragmentPagerAdapter {
        public SamplePagerAdapter(FragmentManager fm) {
            super(fm);
        }
        Fragment[] fragments = {
                new MainActivityGroupFragment(),
                new MainActivityFriendFragment()
        };
        @Override
        public int getCount() {
            return fragments.length;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return "그룹";
                case 1:
                    return "친구";
            }
            return null;
        }
        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }
    }
}
