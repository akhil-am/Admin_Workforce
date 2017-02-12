package com.example.akhil.admin_workforce.extras;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.akhil.admin_workforce.R;

/**
 * Created by akhil on 06/02/17.
 */

public class FragmentInflater extends FragmentActivity {
Context context; Fragment fragment=new Fragment();FragmentManager fragmentManager;
    public FragmentInflater(Context context,Fragment fragment,FragmentManager fragmentManager) {
        this.context=context;
        this.fragment=fragment;
        this.fragmentManager=fragmentManager;
    }

    public void loadFragment() {



            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.v("re", "its");
                    try {

                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fr, fragment).addToBackStack(null)
                                .commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();


    }
}

