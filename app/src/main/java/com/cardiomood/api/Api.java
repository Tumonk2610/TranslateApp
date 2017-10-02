package com.cardiomood.api;


/**
 * Created by Administrator on 24/11/2015.
 */
public class Api extends BaseApi {
    private MyService myService;



    public MyService serviceViewOrClick2() {
        if (myService == null) {
            myService = viewOrClick2().create(MyService.class);
        }
        return myService;
    }


}
