package com.yadahs.messagedialog;

import android.app.Application;

import com.yadahs.uimessage.OnMessageListener;

/**
 * @Author Dushane Coram
 * @Date 27,August,2022
 * @Project MessageDialog
 * @Company Yadahs LLC,
 * @Copyright (c) 2022 Yadahs LLC. All rights reserved.
 * @Description
 */
public class AppApplication extends Application {

    // Without volatile modifier, it’s possible for another thread in Java to see half
    // initialized state of mInstance variable, but with volatile variable guaranteeing
    // happens-before relationship, all the write will happen on volatile mInstance before
    // any read of mInstance variable.
    private static volatile AppApplication mInstance;

    private OnMessageListener messageListener;


    /** methods **/


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    /**
     * private constructor
     * @return new object
     */
    private static AppApplication newInstance() {
        // prevent reflection spi from allowing more than 1 singleton objected to be instantiated
        if (mInstance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
        return new AppApplication();
    }

    /**
     * get the current instance of our singleton. This is thread safe because we used the synchronized
     * @return current instance of the singleton that's in memory
     */
    public static synchronized AppApplication getInstance() {

        // double check locking pattern
        if (mInstance == null) {

            synchronized (AppApplication.class) {
                // check for the second time.
                // if there is no instance available create new one
                if (mInstance == null) mInstance = newInstance();
            }
        }
        return mInstance;
    }

    public OnMessageListener getMessageListener() {
        return messageListener;
    }

    /**
     * initialize the message listener. must be implemented in activity class
     * @param listener .
     */
    public void setMessageListener(OnMessageListener listener) {
        if (messageListener == null) messageListener = listener;
    }


}
