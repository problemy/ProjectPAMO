package com.example.smartarrium;
/*
* Listeer interface informs imageanalyzer when qr code is found
* */
public interface QRCodeFoundListener {
        void onQRCodeFound(String qrCode);
        void qrCodeNotFound();
}

