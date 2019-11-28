package ufc.russas.encontrosuniversitarios;

import ufc.russas.encontrosuniversitarios.model.QRCodeValidador;

import org.junit.Assert;
import org.junit.Test;

public class QRCodeTeste {

    private String descriptor = QRCodeValidador.QRCODE_DESCRIPTOR;

    @Test
    public void validateNullQRCodeTest() {
        QRCodeValidador qrCodeValidador = new QRCodeValidador();
        boolean result = qrCodeValidador.validateQRCode(null);
        Assert.assertFalse(result);
        Assert.assertNull(qrCodeValidador.getIdUsuario());
        Assert.assertNull(qrCodeValidador.getNomeUsuario());
    }

    @Test
    public void validateEmptyQRCodeTest() {
        QRCodeValidador qrCodeValidador = new QRCodeValidador();
        boolean result = qrCodeValidador.validateQRCode("");
        Assert.assertFalse(result);
        Assert.assertNull(qrCodeValidador.getIdUsuario());
        Assert.assertNull(qrCodeValidador.getNomeUsuario());
    }

    @Test
    public void validateOneFieldQRCodeTest() {
        QRCodeValidador qrCodeValidador = new QRCodeValidador();
        boolean result = qrCodeValidador.validateQRCode("descriptor");
        Assert.assertFalse(result);
        Assert.assertNull(qrCodeValidador.getIdUsuario());
        Assert.assertNull(qrCodeValidador.getNomeUsuario());
    }

    @Test
    public void validateDescriptorRoomIdQRCodeTest() {
        QRCodeValidador qrCodeValidador = new QRCodeValidador();
        boolean result = qrCodeValidador.validateQRCode("descriptor-userId");
        Assert.assertFalse(result);
        Assert.assertNull(qrCodeValidador.getIdUsuario());
        Assert.assertNull(qrCodeValidador.getNomeUsuario());
    }

    @Test
    public void validateDescriptorRoomIdUsernameQRCodeWithWrongDescriptorTest() {
        QRCodeValidador qrCodeValidador = new QRCodeValidador();
        boolean result = qrCodeValidador.validateQRCode("descriptor-userId-username");
        Assert.assertFalse(result);
        Assert.assertNull(qrCodeValidador.getIdUsuario());
        Assert.assertNull(qrCodeValidador.getNomeUsuario());
    }

    @Test
    public void validateDescriptorRoomIdUsernameQRCodeWithRoomIdNotANumberTest() {
        QRCodeValidador qrCodeValidador = new QRCodeValidador();
        boolean result = qrCodeValidador.validateQRCode(descriptor+"-userId-username");
        Assert.assertFalse(result);
        Assert.assertNull(qrCodeValidador.getIdUsuario());
        Assert.assertNull(qrCodeValidador.getNomeUsuario());
    }

    @Test
    public void validateRightQRCode() {
        QRCodeValidador qrCodeValidador = new QRCodeValidador();
        boolean result = qrCodeValidador.validateQRCode(descriptor+"-2-Matheus Gomes");
        Assert.assertTrue(result);
        Assert.assertNotNull(qrCodeValidador.getIdUsuario());
        Assert.assertNotNull(qrCodeValidador.getNomeUsuario());
    }
}
