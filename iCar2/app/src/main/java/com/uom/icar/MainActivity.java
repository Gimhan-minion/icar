package com.uom.icar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.google.android.material.navigation.NavigationView;
import com.uom.icar.R;
import com.uom.icar.SharedPreference;
import com.uom.icar.databinding.ActivityMainBinding;
import com.uom.icar.ui.home.HomeFragment;
import com.uom.icar.ui.login.LoginFragment;
import com.uom.icar.ui.register.RegisterFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    boolean status= false;
    boolean register= false;
    String userType;
    String NIC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_login,R.id.nav_register)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //NavigationUI.setupWithNavController(navigationView, navController);

        //adding the navigation manually
        getSupportFragmentManager().popBackStack();
        FragmentTransaction trans =getSupportFragmentManager().beginTransaction();

        //shared preference part
        SharedPreference preference= new SharedPreference();
        register =  preference.GetBoolean(getApplicationContext(),SharedPreference.REGISTER);
        status = preference.GetBoolean(getApplicationContext(),SharedPreference.LOGIN_STATUS);
        NIC=preference.GetString(getApplicationContext(),SharedPreference.USER_NIC);

        NIC=preference.GetString(getApplicationContext(),SharedPreference.USER_NIC);


        Temp.setNIC(NIC);


        Menu menu1 = navigationView.getMenu();
        MenuItem item1=menu1.findItem(R.id.nav_home);
        item1.setVisible(false);
        item1=menu1.findItem(R.id.nav_logout);
        item1.setVisible(false);
        item1=menu1.findItem(R.id.nav_register);
        item1.setVisible(false);



        //check register
        if (register){
            Menu menu = navigationView.getMenu();
            MenuItem item;

            //check login
            if(status) {
                item1=menu1.findItem(R.id.nav_home);
                item1.setVisible(true);
                item1=menu1.findItem(R.id.nav_logout);
                item1.setVisible(true);

                item=menu.findItem(R.id.nav_login);
                item.setVisible(false);


                //moving to frag
                HomeFragment fragment = new HomeFragment();
                trans.replace(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();
            }
            else {
                //moving to frag
                LoginFragment fragment = new LoginFragment();
                trans.replace(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();
            }
        }
        else {
            Menu menu = navigationView.getMenu();
            MenuItem item=menu.findItem(R.id.nav_register);
            item.setVisible(true);

            //moving to frag
            RegisterFragment fragment = new RegisterFragment();
            trans.replace(R.id.nav_host_fragment_content_main,fragment);
            trans.addToBackStack(null);
            trans.commit();

        }



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuID =item.getItemId();
                getSupportFragmentManager().popBackStack();
                FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

                if(menuID==R.id.nav_register){
                    RegisterFragment fragment =new RegisterFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }
                else if(menuID ==R.id.nav_login){
                    LoginFragment fragment =new LoginFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }
                else if(menuID ==R.id.nav_home){
                    HomeFragment fragment =new HomeFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }

                else if (menuID==R.id.nav_exit){
                    finish();

                }
                else if (menuID==R.id.nav_logout){
                    LoginFragment fragment = new LoginFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                    trans.addToBackStack(null);

                    preference.SaveBool(getApplicationContext(),false, SharedPreference.LOGIN_STATUS);
                    preference.SaveString(getApplicationContext(),null,SharedPreference.USER_NIC);

                    Menu menu1 = navigationView.getMenu();
                    MenuItem item1=menu1.findItem(R.id.nav_home);
                    item1.setVisible(false);
                    item1=menu1.findItem(R.id.nav_logout);
                    item1.setVisible(false);

                }
                trans.addToBackStack(null);
                trans.commit();
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}