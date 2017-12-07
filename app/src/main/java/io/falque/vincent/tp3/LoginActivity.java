package io.falque.vincent.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();

    EditText mNameEditText;
    EditText mEmailEditText;
    Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mNameEditText = findViewById(R.id.nameEditText);
        mEmailEditText = findViewById(R.id.emailEditText);
        mSubmitButton = findViewById(R.id.sendButton);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mNameEditText.getText()) && (!TextUtils.isEmpty(mEmailEditText.getText()))) {
                    UserStorage.saveUserInfo(LoginActivity.this, mNameEditText.getText().toString(), mEmailEditText.getText().toString());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(intent);
                }
            }
        });
    }
}
