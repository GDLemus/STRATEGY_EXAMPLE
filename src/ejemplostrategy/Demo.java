package ejemplostrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import ejemplostrategy.PagoPayPal;
import ejemplostrategy.PagoTarjetaCredito;
import ejemplostrategy.InterfazPago;
import ejemplostrategy.Pedido;


public class Demo {
    
    private static Map<Integer, Integer> precioEnProductos = new HashMap<>();
    private static BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
    private static Pedido pedido = new Pedido();
    private static InterfazPago estrategia;

    static {
        precioEnProductos.put(1, 2200);
        precioEnProductos.put(2, 1850);
        precioEnProductos.put(3, 1100);
        precioEnProductos.put(4, 890);
    }

    public static void main(String[] args) throws IOException {
        
        while (!pedido.estaCerrado()) {
            int costo;

            String eleccionContinuar;
            do {
                System.out.print("Por favor, seleccione un producto:" + "\n" +
                        "1 - Placa madre" + "\n" +
                        "2 - CPU" + "\n" +
                        "3 - HDD" + "\n" +
                        "4 - Memoria" + "\n");
                int eleccion = Integer.parseInt(lector.readLine());
                costo = precioEnProductos.get(eleccion);
                System.out.print("Cantidad: ");
                int cantidad = Integer.parseInt(lector.readLine());
                pedido.setCostoTotal(costo * cantidad);
                System.out.print("¿Desea continuar seleccionando productos? S/N: ");
                eleccionContinuar = lector.readLine();
            } while (eleccionContinuar.equalsIgnoreCase("S"));

            if (estrategia == null) {
                System.out.println("Por favor, seleccione un método de pago:" + "\n" +
                        "1 - PayPal" + "\n" +
                        "2 - Tarjeta de Crédito");
                String metodoPago = lector.readLine();

                // El cliente crea diferentes estrategias según la entrada del usuario,
                // configuración de la aplicación, etc.
                if (metodoPago.equals("1")) {
                    estrategia = new PagoPayPal();
                } else {
                    estrategia = new PagoTarjetaCredito();
                }
            }

            // El objeto Pedido delega la recopilación de detalles de pago a la estrategia,
            // ya que solo las estrategias saben qué detalles necesitan para procesar un
            // pago.
            pedido.procesarPedido(estrategia);

            System.out.print("Pagar " + pedido.getCostoTotal() + " unidades o Continuar comprando? P/C: ");
            String continuar = lector.readLine();
            if (continuar.equalsIgnoreCase("P")) {
                
                // Finalmente, la estrategia maneja el pago.
                
                if (estrategia.realizarPago(pedido.getCostoTotal())) {
                    System.out.println("El pago se ha realizado exitosamente.");
                } else {
                    System.out.println("FALLA. Por favor, verifique sus datos.");
                }
                pedido.cerrarPedido();
            }
        }
    }
}
