/*15. Programa que admeta les següents formes d'ús:

	java ex15

Sense paràmetres, mostrarà els permisos de lectura, escriptura i execució del directori actual per a l'usuari que execute el programa.

	java ex15 /ruta/al/fitxer/o/directori

Amb un paràmetre, mostrarà els permisos de lectura, escriptura i execució del directori indicat per a l'usuari que execute el programa (si s'indica un fitxer, es farà per al directori que el conté)

	java ex15 /ruta/al/fitxer/o/directori [c|d|l]

Quan s'afija com a segon paràmetre una c, d o l, aquests tindran el significat de [c]reate (crear el directori si no existeix), [d]elete (esborrar el directori, només si està buit) o [l]ist (llistar el contingut del directori).*/
import java.util.*;
import java.io.*;

public class esco {
	public static void main(String[] args) {

		File f=null;

		if (args.length==0) {
			f = new File("e15.class");

			f = dwgr(f);

			System.out.printf("Permisos:%nLectura:   %b%nEscritura: %b%nEjecución: %b%n", f.canRead(), f.canWrite(), f.canExecute());
		}

		else if (args.length==1)
			opcion2(args[0]);

		else {	// Opciones: (c)rear (d)borrar (l)istar

			f = new File(args[0]);

			if (args[1].equals("c"))
				crF(f);
			else if (args[1].equals("d"))
				dlF(f);
			else if(args[1].equals("l"))
				listF(f);
			else
				opcion2(args[0]);

		}
	}

	public static void opcion2(String s) {

		File f = new File(s);

		if (!f.exists())
			System.out.println("No existe el fichero o directorio "+f.getAbsolutePath());

		else {
			if (f.isFile())
			f = dwgr(f);	// Obtener el directorio en el que está ubicado el fichero

		System.out.printf("Permisos:%nLectura:   %b%nEscritura: %b%nEjecución: %b%n", f.canRead(), f.canWrite(), f.canExecute());
		}
	}

	public static File dwgr(File f) {

		String[] arr = f.getAbsolutePath().split("/");
		String path="";

		for (String s: arr)
			path+='/'+s;

		return new File(path);
	}

	public static void crF(File f) {
		
		String[] aux = f.getAbsolutePath().split("/");

		String path="";

		try {

			for (String s: aux) {
				path+='/'+s;
				f = new File(path);
				if (!f.exists()) {
					if (s.contains(""+'.')) {
						f.createNewFile();
						System.out.println("Se ha creado el fichero "+f.getAbsolutePath());
					}
					else {
						f.mkdir();
						System.out.println("Se ha creado el directorio "+f.getAbsolutePath());
					}
				}

				else
					System.out.println("Ya existe el directorio o fichero.");
			}

		} catch (IOException e) {
			System.out.println("BUGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
		}
	}

	public static void dlF(File f) {

		if (f.length()==0)
			if (f.delete())
				System.out.println("Se ha borrado el archivo o directorio "+f.getAbsolutePath());
		else
			System.out.println("El archivo o directorio no está vacío.");
	}

	public static void listF(File f) {

		if (f.isDirectory()) {
			String[] aux = f.list();

			for (String s: aux)
				System.out.println(s);
		}

		else {
			mostrar(f);
		}
	}

	public static void mostrar(File f) {

		try(BufferedReader bw = new BufferedReader(new FileReader(f))) {

			String s = "";

			while ((s=bw.readLine())!=null)
				System.out.println(s);

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
