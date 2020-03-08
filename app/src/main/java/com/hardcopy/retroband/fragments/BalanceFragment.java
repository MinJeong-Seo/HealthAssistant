/*
 * Copyright (C) 2014 The Retro Band - Open source smart band project
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

package com.hardcopy.retroband.fragments;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.hardcopy.retroband.R;
import com.hardcopy.retroband.R.id;
import com.hardcopy.retroband.R.layout;
import com.hardcopy.retroband.R.string;
import com.hardcopy.retroband.contents.ActivityReport;
import com.hardcopy.retroband.contents.ContentManager;
import com.hardcopy.retroband.logic.Analyzer;
import com.hardcopy.retroband.utils.Logs;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BalanceFragment extends Fragment implements IAdapterListener {
    private static final String TAG = "BalanceFragment";


    // System
    private Context mContext = null;
    private Handler mHandler = null;
    private IFragmentListener mFragmentListener;

    // Contents
    private ContentManager mContentManager;

    // View
    private RenderingStatistics mRenderStatistics;
    private TextView mCalorieText = null;
    private TextView mWalksText = null;
    //private ListView mTimelineList = null;
    private TimelineAdapter mTimelineListAdapter = null;


    // Auto-refresh timer
    private Timer mRefreshTimer = null;


    public BalanceFragment() {
    }

    public BalanceFragment(Context c, IFragmentListener l, Handler h) {
        mContext = c;
        mFragmentListener = l;
        mHandler = h;
    }


    /*****************************************************
     *	Overrided methods
     ******************************************************/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logs.d(TAG, "# MessageListFragment - onCreateView()");

        View rootView = inflater.inflate(layout.fragment_balance, container, false);

        mRenderStatistics = (RenderingStatistics)rootView.findViewById(R.id.render_Bstatistics);


        mCalorieText = (TextView) rootView.findViewById(R.id.text_Bcontent_calorie);
        mCalorieText.setText("0");
        mWalksText = (TextView) rootView.findViewById(R.id.text_content_works);
        mWalksText.setText("0");


        // TODO: If you need to show activity data as list, use below code
		/*
		mTimelineList = (ListView) rootView.findViewById(R.id.list_timeline);
		if(mTimelineListAdapter == null)
			mTimelineListAdapter = new TimelineAdapter(mContext, R.layout.list_item_timeline, null);
		mTimelineListAdapter.setAdapterParams(this);
		mTimelineList.setAdapter(mTimelineListAdapter);
		*/

        return rootView;
    }

    @Override
    public void OnAdapterCallback(int msgType, int arg0, int arg1, String arg2, String arg3, Object arg4) {
        switch(msgType) {
            case IAdapterListener.CALLBACK_xxx:
                // TODO:
                //if(arg4 != null)
                //	mFragmentListener.OnFragmentCallback(IFragmentListener.CALLBACK_REQUEST_ADD_FILTER, 0, 0, null, null, arg4);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mRefreshTimer = new Timer();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mRefreshTimer != null) {
            mRefreshTimer.cancel();
            mRefreshTimer = null;
        }
    }


    /*****************************************************
     *	Private methods
     ******************************************************/
    /**
     * Initialize rendering view
     * @return	boolean		is initialized or not
     */
    private boolean checkRenderView() {
        if(mRenderStatistics != null) {
            mRenderStatistics.initializeGraphics(0);
            return true;
        }

        return false;
    }


    /*****************************************************
     *	Public methods
     ******************************************************/
    /**
     * Show sum of calorie and sum of walk count
     * Service triggers this at every sync
     * @param object
     */
    public void showActivityReport(ActivityReport object) {
        if(object != null) {
            String str = String.format("%,.0f", object.mSumOfCalorie);
            mCalorieText.setText(str);
            mWalksText.setText(Integer.toString(object.mShakeActionCount));
        }
    }

    public void addMessage(ActivityReport object) {
        if(object != null && mTimelineListAdapter != null) {
            mTimelineListAdapter.addObject(object);
            mTimelineListAdapter.notifyDataSetChanged();
        }
    }

    public void addMessageOnTop(ActivityReport object) {
        if(object != null && mTimelineListAdapter != null) {
            mTimelineListAdapter.addObjectOnTop(object);
            mTimelineListAdapter.notifyDataSetChanged();
        }
    }

    public void addMessageAll(ArrayList<ActivityReport> objList) {
        if(objList != null && mTimelineListAdapter != null) {
            mTimelineListAdapter.addObjectAll(objList);
            mTimelineListAdapter.notifyDataSetChanged();
        }
    }

    public void deleteMessage(int id) {
        if(mTimelineListAdapter != null) {
            mTimelineListAdapter.deleteObject(id);
            mTimelineListAdapter.notifyDataSetChanged();
        }
    }

    public void deleteMessageByType(int type) {
        if(mTimelineListAdapter != null) {
            mTimelineListAdapter.deleteObjectByType(type);
            mTimelineListAdapter.notifyDataSetChanged();
        }
    }

    public void deleteMessageAll() {
        if(mTimelineListAdapter != null) {
            mTimelineListAdapter.deleteObjectAll();
            mTimelineListAdapter.notifyDataSetChanged();
        }
    }


    /*****************************************************
     *	Handler, Listener, Timer, Sub classes
     ******************************************************/
    /**
     * Auto-refresh Timer
     */
    private class RefreshTimerTask extends TimerTask {
        public RefreshTimerTask() {}

        @Override
        public void run() {
            if(mHandler != null) {
                mHandler.post(new Runnable() {
                    public void run() {
                        }
                });
            }
        }
    }

}
