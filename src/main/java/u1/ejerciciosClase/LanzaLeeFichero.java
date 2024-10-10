package u1.ejerciciosClase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LanzaLeeFichero {
    public static void main(String[] args) {
        //Este programa lanza el programa LeeFichero (nos hemos creado un ejecutable / artefacto)
        // y es LeeFichero el que lee del fichero, LanzaLeeFichero solo redirecciona E/S.

        System.out.print("Qué fichero quieres leer: ");
        Scanner sc = new Scanner(System.in);
        String f = sc.nextLine();

        //Ejecutar LeeFichero (debe ser un artefacto compilado) con el argumento de ejecución y redireccionar las E/S que necesitemos
        // Aquí no se puede usar ni File, ni FileReader.

        String[] proceso = {"java", "-jar", "./out/artifacts/LeeFichero/LeeFichero.jar", f};
        ProcessBuilder pb = new ProcessBuilder(proceso);
        try {
            //inheritIO redirecciona los 3 streams del subproceso: .in, .out, .err. a la consola actual
            // Si hacemos inheritIO NO hacemos la redirección de p.getInputStream(), etc.
            // Y al revés lo mismo, si redireccionamos p.getInputStream(), etc no podemos hacer inheritIO
            //pb.inheritIO();

            //La alternativa al inheritIO
            Process p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String linea = "";
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
