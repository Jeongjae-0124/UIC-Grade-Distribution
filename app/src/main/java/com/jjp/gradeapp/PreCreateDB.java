package com.jjp.gradeapp;

import android.content.Context;
import android.renderscript.ScriptGroup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class PreCreateDB {

    public static void copyDB(Context context){
        String destPath="/data/data/"+context.getPackageName()+"/databases";
        String destPathWithFileName=destPath+"/gradedb.db";
        File fpath=new File(destPath);
        File fpathWithName=new File(destPathWithFileName);
        if(fpath.exists()) {
            fpath.mkdirs();
            try {
                rawCopy(context.getAssets().open("gradedb.db"), new FileOutputStream(destPathWithFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            if(!fpathWithName.exists()){
                try {
                    rawCopy(context.getAssets().open("gradedb.db"), new FileOutputStream(destPathWithFileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    private static void rawCopy(InputStream gradedb, FileOutputStream fileOutputStream) throws IOException {
        byte[]buffer= new byte[1024];
        int length;
        while((length=gradedb.read(buffer))>0){
            fileOutputStream.write(buffer,0,length);

        }
        gradedb.close();
        fileOutputStream.close();
    }
}
