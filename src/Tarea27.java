import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Tarea27 {
    public static void main(String[] args) {
        try (DatagramSocket st = new DatagramSocket()){

            Scanner scanner = new Scanner(System.in);
            System.out.println("Dame varias palabras: ");
            String palabra = scanner.nextLine();

            for (int i = 0; i <3; i++) {

                try {
                    st.setSoTimeout(2000);
                    InetAddress server = InetAddress.getByName("localhost");

                    byte[] datos = palabra.getBytes(StandardCharsets.UTF_8);
                    st.send(new DatagramPacket(datos, datos.length, server, 5555));

                    byte[] espacio = new byte[1024];
                    DatagramPacket respuesta = new DatagramPacket(espacio, espacio.length);

                    try {
                        st.receive(respuesta);
                        System.out.println(new String(espacio, 0, respuesta.getLength(), StandardCharsets.UTF_8));
                        return;
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());

                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println("Servidor no disponible");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
