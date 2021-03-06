package ufc.russas.encontrosuniversitarios.helper;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import ufc.russas.encontrosuniversitarios.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class QRCodeHelper {
    private int largura;
    private int altura;

    public QRCodeHelper(int largura, int altura){
        this.largura = largura;
        this.altura = altura;
    }


    private Bitmap generateQRCodeBitMap(String code){
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(code, BarcodeFormat.QR_CODE, largura, altura);
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

    public void generateUserQRCodeAlertDialog(final Context context, String code){
        final Bitmap userQRCodeBitmap = generateQRCodeBitMap(code);
        final String userName = code.split("-")[2];
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(userQRCodeBitmap);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.meu_codigo);
        builder.setView(imageView);
        builder.setPositiveButton(R.string.fechar,null);
//        builder.setPositiveButton(R.string.gerar_pdf, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                saveQRCodeAsPdf(userQRCodeBitmap,userName,context);
//            }
//        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void saveQRCodeAsPdf(Bitmap bitmap,String userName,Context context){
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(),bitmap.getHeight(),1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();
        bitmap = Bitmap.createScaledBitmap(bitmap,bitmap.getWidth(),bitmap.getHeight(),true);

        canvas.drawBitmap(bitmap,0,0,new Paint());
        document.finishPage(page);


        try {
            String targetPdf = userName+".pdf";

            if(!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
                Toast.makeText(context, "Não foi possível salvar o pdf", Toast.LENGTH_LONG).show();
            }else {
                File filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),targetPdf);
                document.writeTo(new FileOutputStream(filePath));
                Toast.makeText(context, "PDF salvo no armazenamento externo do dispositivo: " + userName + ".pdf", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                File file = new File(targetPdf);
                intent.setDataAndType(Uri.EMPTY.fromFile(file), "application/pdf");
            }
            //context.startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        document.close();
    }
}