package com.hardcopy.retroband.fragments;

public interface IBalanceListener {
    public static final int CALLBACK_RUN_IN_BACKGROUND = 1;

    public void OnFragmentCallback(int msgType, int arg0, int arg1, String arg2, String arg3, Object arg4);
}
