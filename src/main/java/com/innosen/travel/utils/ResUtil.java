package com.innosen.travel.utils;

import com.innosen.travel.entity.Message;

public class ResUtil {
    public static Message resMsg(int state, String info, Object resDate) {
        Message msg = new Message();
        msg.setStatus(state);
        msg.setMsg(info);
        msg.setData(resDate);
        return msg;
    }

    public static Message resMsg(int state, String info) {
        Message msg = new Message();
        msg.setStatus(state);
        msg.setMsg(info);
        return msg;
    }

}
