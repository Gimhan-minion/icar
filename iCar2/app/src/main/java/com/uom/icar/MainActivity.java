package com.uom.icar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.uom.icar.databinding.ActivityMainBinding;
import com.uom.icar.ui.home.HomeFragment;
import com.uom.icar.ui.login.LoginFragment;
import com.uom.icar.ui.manageService.AddServiceFragment;
import com.uom.icar.ui.manageVehicle.AddVehicleFragment;
import com.uom.icar.ui.manageVehicle.EditVehicleFragment;
import com.uom.icar.ui.register.RegisterFragment;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        boolean status= false;
        boolean register= false;
        String userType;
        String NIC;

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

        //adding the navigation manually
        getSupportFragmentManager().popBackStack();
        FragmentTransaction trans =getSupportFragmentManager().beginTransaction();

        //shared preference part
        SharedPreference preference= new SharedPreference();
        register =  preference.GetBoolean(getApplicationContext(),SharedPreference.REGISTER);
        status = preference.GetBoolean(getApplicationContext(),SharedPreference.LOGIN_STATUS);
        NIC=preference.GetString(getApplicationContext(),SharedPreference.USER_NIC);


        Temp.setNIC(NIC);

//        Menu menu1 = navigationView.getMenu();
//        MenuItem item1=menu1.findItem(R.id.nav_home);
//        item1.setVisible(false);
//        item1=menu1.findItem(R.id.nav_gallery);
//        item1.setVisible(false);
//        item1=menu1.findItem(R.id.nav_slideshow);
//        item1.setVisible(false);
//        item1=menu1.findItem(R.id.nav_addVehicle);
//        item1.setVisible(false);
//        item1=menu1.findItem(R.id.nav_addService);
//        item1.setVisible(false);
//        item1=menu1.findItem(R.id.nav_editVehicle);
//        item1.setVisible(false);
//        item1=menu1.findItem(R.id.nav_register);
//        item1.setVisible(false);
//        item1=menu1.findItem(R.id.nav_logout);
//        item1.setVisible(false);
//        item1=menu1.findItem(R.id.nav_exit);
//        item1.setVisible(false);



        //check register
        if (register){
            Menu menu = navigationView.getMenu();
            MenuItem item;

            //check login
            if(status) {
//                item=menu.findItem(R.id.nav_home);
//                item.setVisible(true);
//                item=menu.findItem(R.id.nav_gallery);
//                item.setVisible(true);
//                item=menu.findItem(R.id.nav_slideshow);
//                item.setVisible(true);
//                item=menu.findItem(R.id.nav_addService);
//                item.setVisible(true);
//                item=menu.findItem(R.id.nav_addVehicle);
//                item.setVisible(true);
//                item=menu.findItem(R.id.nav_logout);
//                item.setVisible(true);
//                item=menu.findItem(R.id.nav_exit);
//                item.setVisible(true);
//
//
//                item=menu.findItem(R.id.nav_login);
//                item.setVisible(false);
//
//
//                //moving to frag
//                HomeFragment fragment = new HomeFragment();
//                trans.replace(R.id.nav_host_fragment_content_main, fragment);
//                trans.addToBackStack(null);
//                trans.commit();
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

                if(menuID==R.id.nav_addVehicle){
                    AddVehicleFragment fragment =new AddVehicleFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }
                else if(menuID ==R.id.nav_editVehicle){
                    EditVehicleFragment fragment =new EditVehicleFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }
//                else if(menuID ==R.id.nav_home){
//                    HomeFragment fragment =new HomeFragment();
//                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
//                }
                else if(menuID ==R.id.nav_login){
                    LoginFragment fragment =new LoginFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }
                else if(menuID ==R.id.nav_register){
                    RegisterFragment fragment =new RegisterFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }
                else if(menuID ==R.id.nav_addService){
                    AddServiceFragment fragment =new AddServiceFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                }
                else if (menuID==R.id.nav_exit){
                    finish();

                }
                else if (menuID==R.id.nav_logout){
                    LoginFragment fragment = new LoginFragment();
                    trans.replace(R.id.nav_host_fragment_content_main,fragment);
                    trans.addToBackStack(null);

                    preference.SaveBool(getApplicationContext(),false,SharedPreference.LOGIN_STATUS);
                    preference.SaveString(getApplicationContext(),null,SharedPreference.USER_NIC);

                    Menu menu1 = navigationView.getMenu();
                    MenuItem item1=menu1.findItem(R.id.nav_home);
                    item1.setVisible(false);
                    item1=menu1.findItem(R.id.nav_addVehicle);
                    item1.setVisible(false);
                    item1=menu1.findItem(R.id.nav_addVehicle);
                    item1.setVisible(false);
                    item1=menu1.findItem(R.id.nav_addService);
                    item1.setVisible(false);
                    item1=menu1.findItem(R.id.nav_logout);
                    item1.setVisible(false);
                    item1=menu1.findItem(R.id.nav_register);
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