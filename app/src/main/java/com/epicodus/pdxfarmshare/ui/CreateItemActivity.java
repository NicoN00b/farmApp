package com.epicodus.pdxfarmshare.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.epicodus.pdxfarmshare.ConsoleActivity;
import com.epicodus.pdxfarmshare.Constants;
import com.epicodus.pdxfarmshare.R;
import com.epicodus.pdxfarmshare.models.Item;
import com.epicodus.pdxfarmshare.models.Utility;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateItemActivity extends AppCompatActivity{
    private static final String TAG = "SignUpActivity";

    @Bind(R.id.input_item) EditText itemText;
    @Bind(R.id.input_location) EditText locationText;
    @Bind(R.id.input_description) EditText descriptionText;
    @Bind(R.id.public_radio) RadioGroup publicChoice;
    @Bind(R.id.barter_radio) RadioGroup barterChoice;
    @Bind(R.id.btn_addItem) Button addItemButton;
    @Bind(R.id.itemImageView) ImageView mImage;

    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference mDatabase;
    String mName;
    String mAddress;
    String mDescription;
    private String mPublic;
    private String mBarter;
    private String pushId;
    ProgressDialog mProgressDialog;

    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    Button btnSelect;
    private String userTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);
        ButterKnife.bind(this);


        mAuth = FirebaseAuth.getInstance();

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProgressDialog();
                addItem();
                Intent intent = new Intent(CreateItemActivity.this, ConsoleActivity.class);
                startActivity(intent);
                finish();
            }
        });
        
    }


    public void addItem() {
        Log.d(TAG, "New Item");
        mName =itemText.getText().toString();

        mAddress =locationText.getText().toString();

        mDescription =descriptionText.getText().toString();

        Item item = new Item(mName, mAddress, mDescription, mPublic, mBarter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_ITEMS).child(uid);

        DatabaseReference pushRef = mDatabase.push();
        String pushId = pushRef.getKey();
        item.setPushId(pushId);
        pushRef.setValue(item);

//        mDatabase.child("items").setValue(item);

    }

    private void createProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Loading...");
        mProgressDialog.setMessage("Adding Item...");
        mProgressDialog.setCancelable(false);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.public_yes:
                if (checked)
                    mPublic = "Yes";
                break;
            case R.id.public_no:
                if (checked)
                    mPublic = "No";
                break;case R.id.barter_yes:
                if (checked)
                    mBarter = "Yes";
                break;
            case R.id.barter_no:
                if (checked)
                    mBarter = "No";
                break;
        }
    }

}
