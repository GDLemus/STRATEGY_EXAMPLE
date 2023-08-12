package ejemplostrategy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PagoPayPal implements InterfazPago {
    private static final Map<String, String> BASE_DATOS = new HashMap<>();
    private final BufferedReader LECTOR = new BufferedReader(new InputStreamReader(System.in));
    private String correoElectronico;
    private String contrasena;
    private boolean inicioSesion;

    static {
        BASE_DATOS.put("amanda1985", "amanda@ya.com");
        BASE_DATOS.put("qwerty", "john@amazon.eu");
    }

    @Override
    public void recopilarDetallesPago() {
        try {
            while (!inicioSesion) {
                System.out.print("Ingrese el correo electrónico del usuario: ");
                correoElectronico = LECTOR.readLine();
                System.out.print("Ingrese la contraseña: ");
                contrasena = LECTOR.readLine();
                if (verificar()) {
                    System.out.println("Verificación de datos exitosa.");
                } else {
                    System.out.println("¡Correo electrónico o contraseña incorrectos!");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean verificar() {
        setInicioSesion(correoElectronico.equals(BASE_DATOS.get(contrasena)));
        return inicioSesion;
    }

    @Override
    public boolean realizarPago(double monto) {
        if (inicioSesion) {
            System.out.println("Realizando pago de $" + monto + " usando PayPal.");
            return true;
        } else {
            return false;
        }
    }

    private void setInicioSesion(boolean inicioSesion) {
        this.inicioSesion = inicioSesion;
    }
}

