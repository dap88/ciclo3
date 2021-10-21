package mintic.edu.proyecto28.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * 
 */
public class LectorCSV {
    private char separador;
    private char comillas;

    public LectorCSV(char separador, char comillas) {
        this.separador = separador;
        this.comillas = comillas;
    }
    
	// Métodos
	/**
	 * Lee un CSV que no contiene el mismo caracter que el separador en su texto
	 * y sin comillas que delimiten los campos
	 * @param path Ruta donde está el archivo
	 * @throws IOException 
	 */
	public List<Producto> leerCSVSimple(String path) throws IOException {
            // Abro el .csv en buffer de lectura
            BufferedReader bufferLectura = new BufferedReader(new FileReader(path));
            String linea = bufferLectura.readLine();// Leo una línea del archivo
            List<Producto> productos = new ArrayList<>(); // Crea una lista tipo Producto
            ProductoDAO productoDAO = new ProductoDAO(); // Crear un objeto tipo ProductoDAO
            boolean creado;
            try {
                while (linea != null) {
                    // Separa la línea leída con el separador definido previamente
                    String[] campos = linea.split(String.valueOf(this.separador));
                    Producto pro = new Producto(); // Instanciamos un objeto tipo Proveedor
                    pro.setCodigo(Integer.parseInt(campos[0]));
                    pro.setNombre(campos[1]);
                    pro.setNitProveedor(Integer.parseInt(campos[2]));
                    pro.setPrecioCompra(Double.parseDouble(campos[3]));
                    pro.setIva(Double.parseDouble(campos[4]));
                    pro.setPrecioVenta(Double.parseDouble(campos[5]));
                    // Tenemos un objeto tipo Producto
                    // Llamar al Método que inserte el objeto en la BDs (ProductoDAO)
                    creado = productoDAO.agregarProducto(pro);
                    if(creado){
                        System.out.println("Producto Agregado a la BDs: " + pro.toString());
                    }else{
                        System.out.println("No se pudó insertar el Producto");
                    }
                    productos.add(pro); // Agregarlo al ArrayList
                    System.out.println(Arrays.toString(campos));
                    // Vuelvo a leer del fichero
                    linea = bufferLectura.readLine();
                }
                // CIerro el buffer de lectura
                if (bufferLectura != null) {
                    bufferLectura.close();
                }

            } catch (Exception e) {
                System.out.println("Error: "+e);
            }
            return productos;
	}
    
}
