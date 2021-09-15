package com.yhhl.yhyq.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtil {
    public static void saveImage(Context context, View view){
        Bitmap bitmap = createViewBitmap(view);
        String filePath = context.getExternalFilesDir(null).getPath() + File.separator + "YunHeYiQi";
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file1 = new File(file, fileName);
        try {//保存到本地
            FileOutputStream out = new FileOutputStream(file1);
            boolean isSuccess = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            //其次把文件插入到系统图库
            MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, fileName, null);
            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess){
                ToastUtil.showToast("保存成功");
            }else {
                ToastUtil.showToast("图片保存失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }
}
