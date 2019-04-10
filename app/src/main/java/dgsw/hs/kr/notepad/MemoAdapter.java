package dgsw.hs.kr.notepad;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MemoAdapter extends RecyclerView.Adapter<MemoItemViewHolder> {

    private ArrayList<UserBean> userData;
    private ItemClickListener listener;

    @NonNull
    @Override
    public MemoItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_memo, viewGroup, false);
        return new MemoItemViewHolder(itemView);
    }

    public MemoAdapter(ArrayList<UserBean> userdata, ItemClickListener listener){
        this.userData = userdata;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull MemoItemViewHolder viewHolder, int i) {
        UserBean userBean = userData.get(i);
        viewHolder.title.setText(userBean.getTitle());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(userBean.getCreatedTime()));
        viewHolder.createdTime.setText(String.valueOf(dateString));
        final int index = i;
        viewHolder.itemView.setOnClickListener(v -> {
            listener.onItemClick(v, index);
        });
    }

    @Override
    public int getItemCount() {
        if(userData == null) {
            return 0;
        } else {
            return userData.size();
        }
    }
}
