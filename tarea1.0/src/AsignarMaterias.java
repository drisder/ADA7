import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AsignarMaterias {
    
     public static void main(String[] args) throws IOException {
        Scanner teclado = new Scanner(System.in);
        int contador = 0;
        int XContador = 0;
        String respuesta = "";
	int bandera = 0;
        String archivo1 = "maestros.CSV"; 
        String archivo2 = "materias.CSV";
        String archivo3 = "pdf final.CSV";
   
        try{
        	contador = leerArchivo1(archivo1);
                XContador= leerArchivo2(archivo2); 
	} catch(FileNotFoundException ex){
		System.out.println("Archivo no encontrado");
		System.exit(0);
	}
        
        int materias[] = new int[contador];
        int Maestro[] = new int [XContador];
        
        while(!"3".equals(respuesta)){
	 
            System.out.println("ELIJA UNA OPCION");
            System.out.println("1. Asignar Materias a los maestros");
            System.out.println("2. Generar archivo");
            System.out.println("3. Salir");
           
            respuesta = teclado.nextLine();
            System.out.println("\n");
            if ("1".equals(respuesta)){
                materias = asignarMaterias(archivo1, contador);
                Maestro = AsignarMaestros(archivo2, XContador);
		bandera = 1;
            } else if ("2".equals(respuesta)){
                if(bandera == 0){
                    System.out.println("Por favor registre las asignaturas antes de generar el archivo");
                    System.out.println("\n");
                    teclado.nextLine();
                } else{
                    generarArchivo(archivo2, archivo3, Maestro);
                }
            } else if ("3".equals(respuesta)){
                System.exit(0);
            } else{
                System.out.println("Instruccion invalida, presione enter para continuar");
                System.out.println("\n");
                teclado.nextLine();
            }
        }
    }
    
    public static int leerArchivo1(String archivo) throws FileNotFoundException, IOException{
        String cadena;
        int contador = 0;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            contador++;
        }
        b.close();
        return contador;
    }
    public static int leerArchivo2(String archivo) throws FileNotFoundException, IOException{
        String cadenaAsig;
        int contadorr = 0;
        FileReader g = new FileReader(archivo);
        BufferedReader c = new BufferedReader(g);
        while((cadenaAsig = c.readLine())!=null) {
            contadorr++;
        }
        c.close();
        return contadorr;
    }
    public static int[] asignarMaterias(String archivo, int contador) throws FileNotFoundException, IOException{
        Scanner teclado = new Scanner(System.in);
        int contador2 = 0;
        String cadena;
        int claves[] = new int[contador];
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            String texto[] = cadena.split(",");
            System.out.println("MAESTROS");
            System.out.print(texto[0]+", " + texto[1]+", " + texto[2] +", " + texto[3]);
	    int numero = -1;
	    System.out.println("\n");
            claves[contador2] = numero;
            contador2++;
        }
        b.close();
        return claves;
    }
    
    public static int[] AsignarMaestros(String archivo, int contador) throws FileNotFoundException, IOException{
        Scanner teclado = new Scanner(System.in);
        int XContador2=0;
        String cadenaAsig;
        int Maestro[] = new int[contador];
        FileReader g = new FileReader(archivo);
        BufferedReader c = new BufferedReader(g);
        while((cadenaAsig = c.readLine())!=null) {
            String texto[] = cadenaAsig.split(",");
            System.out.println("ASIGNATURA");
            System.out.println(texto[0] + ", " + texto[1] + ", " + texto[2] + "\n  Inserte La Matricula Del Maestro: ");
	    int numero = -1;
	    String respuesta = teclado.nextLine();
            numero = Integer.valueOf(respuesta);
            Maestro[XContador2] = numero;
            XContador2++;
        }
        c.close();
        return Maestro;
    }
    
    public static void generarArchivo(String archivo2, String archivo3, int Maestro[]) throws IOException{
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(archivo3));
        FileReader g = new FileReader(archivo2);
        BufferedReader c = new BufferedReader(g);
        String cadena;
        int contador = 0;
        while((cadena = c.readLine())!=null) {
            System.out.println("\n");
            String texto[] = cadena.split(",");
            bw.write(texto[0] +","+ texto[1] +","+texto[2] +","+ "Clave Maestro:"+ Maestro[contador]);
            bw.newLine();
            contador++;
        }
        c.close();
        bw.close();
        System.out.println("Archivo generado");
        System.exit(0);
    }
    
   
    
}