package com.example.counterapp;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel implements LifecycleObserver {

    Thread counterThread;
    int count;
    boolean isCounterinProgress;

    MutableLiveData<Integer> counter;

    public MainActivityViewModel(){
        isCounterinProgress = false;

        counter = new MutableLiveData<>();

        counterThread = new Thread(new Runnable() {
            @Override
            public void run() {

                while(isCounterinProgress){
                    try {
                        Thread.sleep(1000);
                        count++;
                        counter.postValue(count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        });
    }

    public MutableLiveData<Integer> getCounter(){
        return counter;
    }

    public void startMethod(){

        if(!isCounterinProgress){
            isCounterinProgress = true;
            counterThread.start();
        }
    }

    public void stopMethod(){

        isCounterinProgress = false;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void functionalitybasedonlifecycleevent(){
        Log.d("Testinggg", "App is starteddd");
    }

}
