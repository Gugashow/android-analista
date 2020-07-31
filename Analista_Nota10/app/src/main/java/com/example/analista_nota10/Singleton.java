package com.example.analista_nota10;

import com.example.analista_nota10.Model.Login;

public class Singleton {
    private static Singleton ShareDataInstance = null;
    public Login login;

    private Singleton(){}

    public static Singleton getInstance() {
        if (ShareDataInstance != null)
            return ShareDataInstance;

        ShareDataInstance = new Singleton();
        return ShareDataInstance;
    }

}
