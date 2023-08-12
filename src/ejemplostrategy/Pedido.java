package ejemplostrategy;

public class Pedido {
    private int costoTotal = 0;
    private boolean cerrado = false;

    public void procesarPedido(InterfazPago estrategia) {
        estrategia.recopilarDetallesPago();
        // Aquí podríamos recopilar y almacenar datos de pago desde la estrategia.
    }

    public void setCostoTotal(int costo) {
        this.costoTotal += costo;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public boolean estaCerrado() {
        return cerrado;
    }

    public void cerrarPedido() {
        cerrado = true;
    }
}
