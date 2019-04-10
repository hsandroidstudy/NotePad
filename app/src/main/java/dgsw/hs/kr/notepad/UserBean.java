package dgsw.hs.kr.notepad;

import android.support.annotation.NonNull;
import android.util.Log;

import java.time.LocalDateTime;

public class UserBean {
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    private int sequenceNumber;
    private String title;
    private String content;

    private long createdTime;

    public UserBean(){

    }
    public UserBean(int sequenceNumber, String title, String content, Long createdTime){
        this.sequenceNumber = sequenceNumber;
        this.title = title;
        this.content = content;
        this.createdTime = createdTime;
;    }
    public UserBean( String title, String content, Long createdTime){
        this.title = title;
        this.content = content;
        this.createdTime = createdTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }
}
