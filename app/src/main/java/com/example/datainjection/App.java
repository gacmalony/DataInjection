package com.example.datainjection;

import android.app.Application;

import com.example.datainjection.di.AppModule;
import com.example.datainjection.di.DaggerMyComponent;
import com.example.datainjection.di.MyComponent;

public class App extends Application {
    private static App app;
    private MyComponent myComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        myComponent = DaggerMyComponent.builder().appModule(new AppModule(this)).build();
    }
     public static App getApp(){
        return app;
     }

    public MyComponent getMyComponent() {
        return myComponent;
    }
}
