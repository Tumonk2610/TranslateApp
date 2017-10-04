package com.cardiomood.hoanglong.api;


public class Api extends BaseApi {
    private MyService myService;



    public MyService serviceViewOrClick2() {
        if (myService == null) {
            myService = viewOrClick2().create(MyService.class);
        }
        return myService;
    }


}
