package morrison.com.memento;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import morrison.com.memento.Additional.HttpParser;
import morrison.com.memento.Additional.MultipartUtility;

public class AccountSettings extends AppCompatActivity {

    private ImageView userProfilePictureImageView;
    private Button applySettingsButton, cancelChangesButton;
    private EditText userNameEditText, userSurnameEditText,
            userLoginEditText, userEmailEditText,
            userPasswordEditText;

    public static final String UPLOAD_URL = "http://memento-service.000webhostapp.com/php/UserSaveSettings.php";
    public static final String UPLOAD_USER_AVATAR = "image";
    public static final String UPLOAD_USER_ID = "id";
    public static final String UPLOAD_USER_NAME = "user_name";
    public static final String UPLOAD_USER_PASSWORD = "user_password";
    public static final String UPLOAD_USER_EMAIL = "user_email";
    public static final String UPLOAD_USER_LOGIN = "user_login";
    public static final String UPLOAD_USER_BIRTH = "user_birth";

    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    private Uri filePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        userProfilePictureImageView =(ImageView) findViewById(R.id.user_profile_pic);
        applySettingsButton = (Button) findViewById(R.id.settings_apply_button);
        cancelChangesButton = (Button) findViewById(R.id.settings_cancel_button);
        userNameEditText = (EditText) findViewById(R.id.settings_user_name_field);
        userSurnameEditText = (EditText) findViewById(R.id.settings_user_surname_field);
        userLoginEditText = (EditText) findViewById(R.id.settings_user_login_field);
        userEmailEditText = (EditText) findViewById(R.id.settings_user_email_field);
        userPasswordEditText = (EditText) findViewById(R.id.settings_user_password_field);

        userProfilePictureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        applySettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        cancelChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomStartActivity.finishActivity(AccountSettings.this);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                uploadUserData();
                userProfilePictureImageView.setImageBitmap(bitmap);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void uploadUserData(){
        class UploadImage extends AsyncTask<Object,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(
                        AccountSettings.this,
                        "Saving...",
                        null,true,true);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Object... params) {
                Bitmap bitmap = (Bitmap) params[0];
                String id = (String) params[1];
                String userName = (String) params[2];
                String userPassword = (String) params[3];
                String userEmail = (String) params[4];
                String userLogin= (String) params[5];
                String userBirth= (String) params[6];

                String uploadImage = getStringImage(bitmap);
                HashMap<String,String> data = new HashMap<>();

                data.put(UPLOAD_USER_AVATAR, uploadImage);
                data.put(UPLOAD_USER_ID, id);
                data.put(UPLOAD_USER_NAME, userName);
                data.put(UPLOAD_USER_PASSWORD, userPassword);
                data.put(UPLOAD_USER_EMAIL, userEmail);
                data.put(UPLOAD_USER_LOGIN, userLogin);
                data.put(UPLOAD_USER_BIRTH, userBirth);

                String result = null;

                try {
                    MultipartUtility multipartUtility = new MultipartUtility(UPLOAD_URL, "UTF-8");

                    for(Map.Entry<String,String> entryMap : data.entrySet()) {
                        multipartUtility.addFormField(entryMap.getKey(), entryMap.getValue());

                    }
                        result = multipartUtility.finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("result:", result);

                return result;
            }
        }
        try {
            UploadImage ui = new UploadImage();

            ui.execute(bitmap,
                    new JSONObject(getIntent().getStringExtra("user_data")).get("id").toString(),
                    userNameEditText.getText().toString()+" "+userSurnameEditText.getText().toString(), "12345678",
                    userEmailEditText.getText().toString(), userLoginEditText.getText().toString(), ""
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
