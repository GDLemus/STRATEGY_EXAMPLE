package ejemplostrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PagoTarjetaCredito implements InterfazPago {
    private final BufferedReader LECTOR = new BufferedReader(new InputStreamReader(System.in));
    private TarjetaCredito tarjeta;

    @Override
    public void recopilarDetallesPago() {
        try {
            System.out.print("Ingrese el número de la tarjeta: ");
            String numero = LECTOR.readLine();
            System.out.print("Ingrese la fecha de vencimiento de la tarjeta 'mm/aa': ");
            String fecha = LECTOR.readLine();
            System.out.print("Ingrese el código CVV: ");
            String cvv = LECTOR.readLine();
            tarjeta = new TarjetaCredito(numero, fecha, cvv);

            // Validar número de tarjeta de crédito...

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean realizarPago(double monto) {
        if (tarjetaPresente()) {
            System.out.println("Realizando pago de $" + monto + " usando Tarjeta de Crédito.");
            tarjeta.setMonto((int) (tarjeta.getMonto() - monto));
            return true;
        } else {
            return false;
        }
    }

    private boolean tarjetaPresente() {
        return tarjeta != null;
    }
}
