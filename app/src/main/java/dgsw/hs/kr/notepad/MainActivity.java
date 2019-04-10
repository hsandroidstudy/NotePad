package dgsw.hs.kr.notepad;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemClickListener{

    private MemoDBHelper helper;
    private RecyclerView memoList;
    private MemoAdapter adapter;
    private ArrayList<UserBean> userData;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){
            userData.clear();
            userData = helper.getAll();
            adapter.notifyDataSetChanged();
        }
        else if(requestCode == 0 && resultCode == RESULT_CANCELED){
            userData.clear();
            userData = helper.getAll();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnWrite).setOnClickListener(v -> {
            startActivityForResult(new Intent(this, MemoActivity.class), 1);
        });

        helper =  new MemoDBHelper(this, "userdb", null, 1);

        userData = helper.getAll();

        adapter = new MemoAdapter(userData, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        memoList = findViewById(R.id.memoList);
        memoList.setLayoutManager(layoutManager);
        memoList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(this, MemoActivity.class);
        intent.putExtra("seqNum", helper.get(userData.get(position).getSequenceNumber()).getSequenceNumber());
        startActivityForResult(intent, 0);
    }
}
