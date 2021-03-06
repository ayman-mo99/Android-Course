package Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.finaltry.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class SignUP extends AppCompatActivity {
    private TextInputEditText firstName;
    private TextInputEditText lastName;
    private TextInputEditText email;
    private TextInputEditText password;
    private Button goLog;
    private Button signUp;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private StorageReference mFirebaseStorage;
    private ProgressDialog mProgressDialog;
    private ImageButton imageButton;
    private final static int GALARY_CODE =1;
    private Uri resultUri = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_u_p);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("Users");
        mFirebaseStorage = FirebaseStorage.getInstance().getReference().child("Profile");

        mAuth = FirebaseAuth.getInstance();
        firstName = (TextInputEditText)findViewById(R.id.first_name);
        lastName = (TextInputEditText)findViewById(R.id.last_name);
        email = (TextInputEditText)findViewById(R.id.Sign_email);
        password = (TextInputEditText)findViewById(R.id.Sign_password);
        signUp = (Button)findViewById(R.id.SignUp);
        goLog = (Button)findViewById(R.id.goLog);
        imageButton = (ImageButton)findViewById(R.id.Sign_image);

        mProgressDialog = new ProgressDialog(this);
        goLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUP.this,Login.class));
                finish();
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallary = new Intent();
                gallary.setAction(Intent.ACTION_GET_CONTENT);
                gallary.setType("image/*");
                startActivityForResult(gallary,GALARY_CODE);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });
    }
    private void createNewAccount(){
        String name = firstName.getText().toString().trim();
        String lname = lastName.getText().toString().trim();
        String em = email.getText().toString().trim();
        String pwt = password.getText().toString().trim();
        if(!TextUtils.isEmpty(name)&& !TextUtils.isEmpty(lname)
                && !TextUtils.isEmpty(em) && !TextUtils.isEmpty(pwt)){
            mProgressDialog.setMessage("Creating Acount...");
            mAuth.createUserWithEmailAndPassword(em,pwt)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if(authResult != null){
                                StorageReference imagePath = mFirebaseStorage.child("Profile")
                                        .child(resultUri.getLastPathSegment());
                                imagePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        imagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                String userid = mAuth.getCurrentUser().getUid();
                                                DatabaseReference currenUserDb = mDatabaseReference.child(userid);
                                                currenUserDb.child("firstname").setValue(name);
                                                currenUserDb.child("lastname").setValue(lname);
                                                currenUserDb.child("image").setValue(uri.toString());
                                                currenUserDb.child("admin").setValue(false);
                                                currenUserDb.child("id").setValue(authResult.getUser().getUid());

                                                mProgressDialog.dismiss();

                                                Intent intent = new Intent(SignUP.this,HomeActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);
                                            }
                                        });

                                    }
                                });

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignUP.this,"user already exist",Toast.LENGTH_LONG).show();
                }
            });
        }else{
            Toast.makeText(SignUP.this,"Enter allll data",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALARY_CODE && resultCode == RESULT_OK){
            Uri mImageUri = data.getData();
            CropImage.activity(mImageUri)
                    .setAspectRatio(1,1)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);
        }
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                resultUri = result.getUri();

                imageButton.setImageURI(resultUri);
            }else if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }
        }
    }
}