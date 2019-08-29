package com.example.encontrosuniversitarios;

import com.example.encontrosuniversitarios.model.QRCodeValidator;

import org.junit.Assert;
import org.junit.Test;

public class QRCodeTeste {

    private String descriptor = QRCodeValidator.QRCODE_DESCRIPTOR;

    @Test
    public void validateNullQRCodeTest() {
        QRCodeValidator qrCodeValidator = new QRCodeValidator();
        boolean result = qrCodeValidator.validateQRCode(null);
        Assert.assertFalse(result);
        Assert.assertNull(qrCodeValidator.getIdUsuario());
        Assert.assertNull(qrCodeValidator.getNomeUsuario());
    }

    @Test
    public void validateEmptyQRCodeTest() {
        QRCodeValidator qrCodeValidator = new QRCodeValidator();
        boolean result = qrCodeValidator.validateQRCode("");
        Assert.assertFalse(result);
        Assert.assertNull(qrCodeValidator.getIdUsuario());
        Assert.assertNull(qrCodeValidator.getNomeUsuario());
    }

    @Test
    public void validateOneFieldQRCodeTest() {
        QRCodeValidator qrCodeValidator = new QRCodeValidator();
        boolean result = qrCodeValidator.validateQRCode("descriptor");
        Assert.assertFalse(result);
        Assert.assertNull(qrCodeValidator.getIdUsuario());
        Assert.assertNull(qrCodeValidator.getNomeUsuario());
    }

    @Test
    public void validateDescriptorRoomIdQRCodeTest() {
        QRCodeValidator qrCodeValidator = new QRCodeValidator();
        boolean result = qrCodeValidator.validateQRCode("descriptor-userId");
        Assert.assertFalse(result);
        Assert.assertNull(qrCodeValidator.getIdUsuario());
        Assert.assertNull(qrCodeValidator.getNomeUsuario());
    }

    @Test
    public void validateDescriptorRoomIdUsernameQRCodeWithWrongDescriptorTest() {
        QRCodeValidator qrCodeValidator = new QRCodeValidator();
        boolean result = qrCodeValidator.validateQRCode("descriptor-userId-username");
        Assert.assertFalse(result);
        Assert.assertNull(qrCodeValidator.getIdUsuario());
        Assert.assertNull(qrCodeValidator.getNomeUsuario());
    }

    @Test
    public void validateDescriptorRoomIdUsernameQRCodeWithRoomIdNotANumberTest() {
        QRCodeValidator qrCodeValidator = new QRCodeValidator();
        boolean result = qrCodeValidator.validateQRCode(descriptor+"-userId-username");
        Assert.assertFalse(result);
        Assert.assertNull(qrCodeValidator.getIdUsuario());
        Assert.assertNull(qrCodeValidator.getNomeUsuario());
    }

    @Test
    public void validateRightQRCode() {
        QRCodeValidator qrCodeValidator = new QRCodeValidator();
        boolean result = qrCodeValidator.validateQRCode(descriptor+"-2-Matheus Gomes");
        Assert.assertTrue(result);
        Assert.assertNotNull(qrCodeValidator.getIdUsuario());
        Assert.assertNotNull(qrCodeValidator.getNomeUsuario());
    }
}
