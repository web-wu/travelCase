package com.innosen.travel.entity;

public class Message {
    private int Status;
    private String msg;
    private Object data;

    public Message() {
    }

    public Message(int status, String msg, Object data) {
        Status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "Status=" + Status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
