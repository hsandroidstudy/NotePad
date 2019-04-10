package dgsw.hs.kr.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MemoActivity extends AppCompatActivity {

    MemoDBHelper helper;

    EditText etTitle;
    EditText etContent;

    private final int ERROR_CODE = -999;
    int seqNum = ERROR_CODE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        helper =  new MemoDBHelper(this, "userdb", null, 1);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);

        Intent intent = getIntent();
        seqNum = intent.getIntExtra("seqNum", ERROR_CODE);
        if(seqNum != ERROR_CODE){
            UserBean user = helper.get(seqNum);
            etTitle.setText(user.getTitle());
            etContent.setText(user.getContent());
        }


        findViewById(R.id.btnSave).setOnClickListener(v -> {
            etTitle = findViewById(R.id.etTitle);
            etContent = findViewById(R.id.etContent);
            helper.insert(new UserBean(etTitle.getText().toString(), etContent.getText().toString(), System.currentTimeMillis()));
            setResult(RESULT_OK);
            finish();
        });

        findViewById(R.id.btnDelete).setOnClickListener(v -> {
            helper.delete(seqNum);
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}
