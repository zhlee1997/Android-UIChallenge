package com.example.uichallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText edtTxtName, edtTxtEmail, edtTxtPassword, edtTxtRepeatPassword;
    private Button btnPickImage, btnRegister;
    private TextView txtWarnName, txtWarnEmail, txtWarnPassword, txtWarnRepeatPassword;
    private Spinner countriesSpinner;
    private RadioGroup rgGender;
    private CheckBox agreementCheck;
    private ConstraintLayout parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Yet to upgrade", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegister();

            }
        });
    }

    private void initRegister() {
        Log.d(TAG, "initRegister: Started");

        if (validateData()) {
            if (agreementCheck.isChecked()) {
                showSnackBar();
            } else {
                Toast.makeText(MainActivity.this, "You need to agree to the agreement", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBar() {
        Log.d(TAG, "showSnackBar: Started");
        txtWarnName.setVisibility(View.GONE);
        txtWarnEmail.setVisibility(View.GONE);
        txtWarnPassword.setVisibility(View.GONE);
        txtWarnRepeatPassword.setVisibility(View.GONE);

        String name = edtTxtName.getText().toString();
        String email = edtTxtEmail.getText().toString();
        String country = countriesSpinner.getSelectedItem().toString();
        String gender = "";

        switch (rgGender.getCheckedRadioButtonId()){
            case R.id.rbMale:
                gender = "Male";
                break;
            case R.id.rbFemale:
                gender = "Female";
                break;
            case R.id.rbOther:
                gender = "Other";
                break;
            default:
                gender = "Unknown";
                break;
        }

        String snackText = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Country: " + country + "\n" +
                "Gender: " + gender;

        Snackbar
                .make(parent,snackText, Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edtTxtName.setText("");
                        edtTxtEmail.setText("");
                        edtTxtPassword.setText("");
                        edtTxtRepeatPassword.setText("");
                    }
                })
                .show();
    }

    private boolean validateData() {
        Log.d(TAG, "validateDate: Started");
        if (edtTxtName.getText().toString().equals("")){
            txtWarnName.setVisibility(View.VISIBLE);
            txtWarnName.setText("Enter Your Name");
            return false;
        }

        if (edtTxtEmail.getText().toString().equals("")){
            txtWarnEmail.setVisibility(View.VISIBLE);
            txtWarnEmail.setText("Enter Your Email");
            return false;
        }

        if (edtTxtPassword.getText().toString().equals("")){
            txtWarnPassword.setVisibility(View.VISIBLE);
            txtWarnPassword.setText("Enter Your Password");
            return false;
        }

        if (edtTxtRepeatPassword.getText().toString().equals("")){
            txtWarnRepeatPassword.setVisibility(View.VISIBLE);
            txtWarnRepeatPassword.setText("Re-Enter Your Password");
            return false;
        }

        if (!edtTxtRepeatPassword.getText().toString().equals(edtTxtPassword.getText().toString())){
            txtWarnRepeatPassword.setVisibility(View.VISIBLE);
            txtWarnRepeatPassword.setText("Password doesn't match");
            return false;
        }

        return true;
    }

    private void initViews() {
        Log.d(TAG, "initViews: Started");

        edtTxtName = findViewById(R.id.txtName);
        edtTxtEmail = findViewById(R.id.txtEmail);
        edtTxtPassword = findViewById(R.id.txtPassword);
        edtTxtRepeatPassword = findViewById(R.id.txtPasswordRepeat);

        btnPickImage = findViewById(R.id.btnPickImage);
        btnRegister = findViewById(R.id.btnRegister);

        txtWarnName = findViewById(R.id.warnName);
        txtWarnEmail = findViewById(R.id.warnEmail);
        txtWarnPassword = findViewById(R.id.warnPassword);
        txtWarnRepeatPassword = findViewById(R.id.warnPasswordRepeat);

        countriesSpinner = findViewById(R.id.spinnerCountry);
        rgGender = findViewById(R.id.rgGender);
        agreementCheck = findViewById(R.id.cbAgree);
        parent = findViewById(R.id.parent);
    }
}