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

    // System
    private Context mContext = null;
    private IFragmentListener mFragmentListener = null;

    public BalanceFragment() {
    }

    public BalanceFragment(Context c, IFragmentListener l) {
        mContext = c;
        mFragmentListener = l;
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
}
