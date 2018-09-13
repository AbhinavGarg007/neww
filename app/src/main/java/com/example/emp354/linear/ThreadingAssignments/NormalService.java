package com.example.emp354.linear.ThreadingAssignments;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;

public class NormalService extends Service{

    Looper mLooper;
    ServiceHandler serviceHandler;


    private final class ServiceHandler extends Handler{
        ServiceHandler(Looper looper)
        {
            super(looper);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
