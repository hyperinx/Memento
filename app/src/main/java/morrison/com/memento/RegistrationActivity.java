package morrison.com.memento;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import morrison.com.memento.Additional.HttpParser;

public class RegistrationActivity extends Activity {

    private Button register, loginButton;
    private EditText userLogin, userEmail, userPassword;
    private String userLoginHolder, userEmailHolder, userPasswordHolder;
    private String finalResult;
    private final String httpURL = "http://memento-service.000webhostapp.com/php/UserRegistration.php";
    private Boolean checkEditText;
    private ProgressDialog progressDialog;
    private HashMap<String, String> hashMap = new HashMap<>();
    private HttpParser httpParser = new HttpParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Assign Id'S
        userLogin = (EditText) findViewById(R.id.editTextUserLogin);
        userEmail = (EditText) findViewById(R.id.editTextUserEmail);
        userPassword = (EditText) findViewById(R.id.editTextUserPassword);

        register = (Button) findViewById(R.id.submit);
        loginButton = (Button) findViewById(R.id.loginButton);

        //Adding Click Listener on button.
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                checkEditTextIsEmptyOrNot();

                if (checkEditText) {
                    // If EditText is not empty and checkEditText = True then this block will execute.
                    registerUser(userLoginHolder, userEmailHolder, userPasswordHolder);
                } else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(RegistrationActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomStartActivity.finishActivity(RegistrationActivity.this);
            }
        });
    }

    public void checkEditTextIsEmptyOrNot() {

        userLoginHolder = userLogin.getText().toString();
        userEmailHolder = userEmail.getText().toString();
        userPasswordHolder = userPassword.getText().toString();

        if (TextUtils.isEmpty(userLoginHolder)
                || TextUtils.isEmpty(userEmailHolder)
                || TextUtils.isEmpty(userPasswordHolder)) {
            checkEditText = false;

        } else {

            checkEditText = true;
        }
    }

    public void registerUser(final String userLogin, final String userEmail, final String userPassword) {

        class userRegistrationClass extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(RegistrationActivity.this, "Loading Data", null, true, true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {
                super.onPostExecute(httpResponseMsg);
                progressDialog.dismiss();

                if (httpResponseMsg.equals("Registration Successfully")) {

                    CustomStartActivity.finishActivity(RegistrationActivity.this);

                }
                Toast.makeText(RegistrationActivity.this, httpResponseMsg, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("user_login", params[0]);
                hashMap.put("user_email", params[1]);
                hashMap.put("user_password", params[2]);
                finalResult = httpParser.postRequest(hashMap, httpURL);

                return finalResult;
            }
        }

        userRegistrationClass userRegistrationClass = new userRegistrationClass();
        userRegistrationClass.execute(userLogin, userEmail, userPassword);
    }
}
