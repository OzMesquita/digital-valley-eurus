package com.example.encontrosuniversitarios.helper;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;

import com.example.encontrosuniversitarios.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeHelper {
    private int width;
    private int height;

    public QRCodeHelper(int width,int height){
        this.width = width;
        this.height = height;
    }


    private Bitmap generateQRCodeBitMap(String code){
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(code, BarcodeFormat.QR_CODE,width,height);
            int height = bitMatrix.getHeight();
            int width = bitMatrix.getWidth();
            Bitmap bitmap = Bitmap.createBitmap(width,height,Bitmap.Config.RGB_565);
            for(int x=0;x<width;x++){
                for(int y=0;y<height;y++){
                    bitmap.setPixel(x,y,bitMatrix.get(x,y)? Color.BLACK:Color.WHITE);
                }
            }
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void generateUserQRCodeAlertDialog(Context context, String code){
        Bitmap userQRCodeBitmap = generateQRCodeBitMap(code);
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(userQRCodeBitmap);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.qr_code_title);
        builder.setView(imageView);
        builder.setNeutralButton(R.string.close,null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}