package dgsw.hs.kr.notepad;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MemoItemViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView createdTime;

    public MemoItemViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.textViewTitle);
        createdTime = itemView.findViewById(R.id.textViewTime);
    }
}
