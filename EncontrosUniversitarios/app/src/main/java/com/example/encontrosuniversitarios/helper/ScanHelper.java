package com.example.encontrosuniversitarios.helper;

import android.app.Activity;
import com.google.zxing.integration.android.IntentIntegrator;

public class ScanHelper {
    private int camera;
    private Activity activity;
    private String prompt;

    private IntentIntegrator intentIntegrator;
    public ScanHelper(int camera,Activity activity,String prompt){
        this.camera = camera;
        this.activity = activity;
        this.prompt = prompt;
        createScanCode();
    }

    private void createScanCode(){
        intentIntegrator = new IntentIntegrator(activity);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        intentIntegrator.setPrompt(prompt);
        intentIntegrator.setCameraId(camera);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setBarcodeImageEnabled(true);
    }

    public void showScan(){
        intentIntegrator.initiateScan();
    }
}
