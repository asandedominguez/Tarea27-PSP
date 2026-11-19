import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class Tarea27Servidor {
    public static void main (String[] args) {
        try (DatagramSocket dt = new DatagramSocket(5555)) {
           byte[] buffer = new byte[1024];
           while (true) {
               DatagramPacket td = new DatagramPacket(buffer, buffer.length);
               dt.receive(td);
               String texto = new String(td.getData(), 0, td.getLength(), StandardCharsets.UTF_8);
               System.out.println(td.getAddress() + ":" + td.getPort() + ":" + texto);

               String[] palabras = texto.trim().split("\\s+");
               String larga = "";
               for (String p : palabras) {
                   if (p.length() > larga.length()) {
                       larga = p;
                   }
               }

               String mensajeRespuesta = "La palabra más larga es: " + larga + " con: " + larga.length() + " caracteres";
               byte[] respuesta = mensajeRespuesta.getBytes(StandardCharsets.UTF_8);

               dt.send(new DatagramPacket(respuesta, respuesta.length, td.getAddress(), td.getPort()));

           }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
