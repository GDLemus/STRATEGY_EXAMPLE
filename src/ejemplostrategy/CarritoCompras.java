package ejemplostrategy;

public class CarritoCompras {
    
    private InterfazPago metodoPago;

    public void setMetodoPago(InterfazPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void procesarPedido(double total) {
        metodoPago.recopilarDetallesPago();
        if (metodoPago.realizarPago(total)) {
     
            System.out.println("Pedido procesado. Total: $" + total);
        } else {
            System.out.println("El pago no se pudo realizar. Por favor, verifique los datos.");
        }
    }
}
