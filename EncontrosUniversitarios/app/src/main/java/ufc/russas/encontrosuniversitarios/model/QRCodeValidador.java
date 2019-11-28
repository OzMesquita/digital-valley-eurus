package ufc.russas.encontrosuniversitarios.model;

public class QRCodeValidador {
    public static final String QRCODE_DESCRIPTOR = "EURUSSAS";
    private Integer idUsuario;
    private String nomeUsuario;

    public boolean validateQRCode(String qrcodeMessage) {
        try{
            if(qrcodeMessage != null && !qrcodeMessage.isEmpty()) {
                String []fields = qrcodeMessage.split("-");
                if(fields.length != 3) return false;
                if(!fields[0].equals(QRCODE_DESCRIPTOR)) return false;
                idUsuario = Integer.parseInt(fields[1]);
                nomeUsuario = fields[2];
                return true;
            }else{
                return false;
            }
        }catch (NumberFormatException ex){
            return false;
        }
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
}
