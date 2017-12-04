package io.falque.vincent.tp3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity  extends AppCompatActivity {

        public static final String TAG = LoginActivity.class.getSimpleName();

        EditText mNameEditText;
        EditText    mEmailEditText;
        Button mSubmitButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
        }
    }
}
