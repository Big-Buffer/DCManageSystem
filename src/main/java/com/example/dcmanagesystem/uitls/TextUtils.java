package com.example.dcmanagesystem.uitls;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TextUtils {

    public static String timeFormat(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
