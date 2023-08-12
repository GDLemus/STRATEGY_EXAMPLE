package ejemplostrategy;

public class TarjetaCredito {
    private int monto;
    private String numero;
    private String fecha;
    private String cvv;

    public TarjetaCredito(String numero, String fecha, String cvv) {
        this.monto = 100_000;
        this.numero = numero;
        this.fecha = fecha;
        this.cvv = cvv;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public int getMonto() {
        return monto;
    }
}
