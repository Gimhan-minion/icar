package com.uom.icar.ui.register;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.uom.icar.R;
import com.uom.icar.model.User;
import com.uom.icar.pashwrdHash.passwordHash;
import com.uom.icar.ui.login.LoginFragment;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
    EditText txtNIC,txtName,txtAddress,txtEmail,txtNum,txtPw,txtCpw;
    Button btnRegister;
    TextView txtLog;
    DatabaseReference ref;
    FirebaseDatabase rootNode;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_register, container, false);

        txtLog =view.findViewById(R.id.txtLog);

        txtNIC = view.findViewById(R.id.txtNIC);
        txtName = view.findViewById(R.id.Name);
        txtEmail = view.findViewById(R.id.Email);
        txtNum = view.findViewById(R.id.Num);
        txtPw = view.findViewById(R.id.txtPw);
        txtCpw = view.findViewById(R.id.txtCpw);

        btnRegister = view.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validation
                if (checkValid()){
                    if(txtPw.getText().toString().equals(txtCpw.getText().toString())){
                        String nic = txtNIC.getText().toString();
                        String name = txtName.getText().toString();
                        String email = txtEmail.getText().toString();
                        String num = txtNum.getText().toString();
                        String hashPW = passwordHash.getMd5(txtPw.getText().toString());




                        User user=new User(nic,name,email,num,hashPW);

                        //checking duplicates
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
                        Query checkUser = reference.orderByChild("nic").equalTo(nic);

                        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                preloader.dismissDialog();
                                if(snapshot.exists()){

                                    Toast.makeText(getActivity().getApplicationContext(),"There is an exiting account for this NIC number",Toast.LENGTH_LONG).show();

                                }
                                else{

                                    //saving data to DB
                                    try{
                                        rootNode = FirebaseDatabase.getInstance();
                                        ref =rootNode.getReference("User");
                                        ref.child(user.getNic()).setValue(user);


                                        //Set shared pref for register
//                                        SharedPreference preference=new SharedPreference();
//                                        preference.SaveBool(view.getContext(),true,SharedPreference.REGISTER);

                                        //Move to login frag
                                        LoginFragment fragment = new LoginFragment();
                                        FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
                                        trans.replace(R.id.nav_host_fragment_content_main, fragment);
                                        trans.addToBackStack(null);
                                        trans.commit();

                                        Toast.makeText(getActivity().getApplicationContext(),"Account Created Successfully!",Toast.LENGTH_LONG).show();

                                    }
                                    catch(Exception ex){

                                        Toast.makeText(getActivity().getApplicationContext(),"Failed to Create an Account",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    else{
                        Toast.makeText(getActivity().getApplicationContext(),"Passwords dose not match",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

        txtLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction trans =getActivity().getSupportFragmentManager().beginTransaction();
                LoginFragment fragment = new LoginFragment();
                trans.replace(R.id.nav_host_fragment_content_main, fragment);
                trans.addToBackStack(null);
                trans.commit();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        // TODO: Use the ViewModel
    }

    private boolean checkValid() {
        if (txtNIC.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"NIC cannot be blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (txtName.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Name cannot be blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (txtEmail.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Email cannot be blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (txtNum.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Number cannot be blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (txtPw.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Password cannot be blank",Toast.LENGTH_LONG).show();
            return false;
        }
        if (txtCpw.getText().toString().equals("")) {
            Toast.makeText(getActivity().getApplicationContext(),"Confirm password cannot be blank",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;

    }

}