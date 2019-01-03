package com.kaelli.pdfjsandroid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by KaelLi on 2018/11/22.
 */
public class MainActivity extends AppCompatActivity {
    private PDFView pdfView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pdfView = findViewById(R.id.pdfView);

        pdfView.loadOnlinePDF("https://download.brother.com/welcome/docp000648/cv_pt3600_schn_sig_lad962001.pdf");
    }
}
