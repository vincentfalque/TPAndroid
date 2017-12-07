package io.falque.vincent.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ValueEventListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    EditText       mInputEditText;
    ImageButton    mSendButton;
    MessageAdapter mMessageAdapter;
    User user;

    DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mInputEditText = findViewById(R.id.inputEditText);
        mSendButton = findViewById(R.id.sendButton);

        user = UserStorage.getUser(this);

        if(user.getNom().equals(UserStorage.USER_UNDEFINED) && user.getEmail().equals(UserStorage.USER_UNDEFINED)) {
            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivity(intent);
            this.finish();
        }

        mMessageAdapter = new MessageAdapter(new ArrayList<Message>());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mMessageAdapter);

        connectAndListenToFirebase();

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mInputEditText.getText().toString().isEmpty()){
                    return;
                }
                DatabaseReference newData = mDatabaseReference.push();
                newData.setValue(
                    new Message(mInputEditText.getText().toString(), user.getNom(), user.getEmail(), 0L));
                mInputEditText.setText("");
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDatabaseReference.removeEventListener(this);
    }

    private void connectAndListenToFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabaseReference = database.getReference("chat/messages");

        mDatabaseReference.addValueEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Log.d(TAG, "dataChange : " + dataSnapshot);
        List<Message> items = new ArrayList<>();
        for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
            items.add(messageSnapshot.getValue(Message.class));
        }
        mMessageAdapter.setData(items);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                UserStorage.deleteUserInfo(this);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                this.startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
