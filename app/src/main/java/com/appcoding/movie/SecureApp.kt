package com.appcoding.movie

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.WindowManager

class SecureApp :Application() {
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(
            object: Application.ActivityLifecycleCallbacks{
            override fun onActivityCreated(
                activity: Activity, savedInstanceState: Bundle?) {

                activity.window.setFlags(
                    WindowManager.LayoutParams.FLAG_SECURE,
                    WindowManager.LayoutParams.FLAG_SECURE
                )
            }

            override fun onActivityDestroyed(activity: Activity) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
        }

        )
    }
}