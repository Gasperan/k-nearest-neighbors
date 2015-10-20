package GUI;

import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import kNN.*;

public class VentanaPrincipal extends JFrame implements ActionListener{
	
	
	//Constantes para definir comandos
	static final String SALIR = "Salir"; 
	static final String ABRIRARCHIVO1 = "Abrir Archivo Entramiento";
	static final String ABRIRARCHIVO2 = "Abrir Archivo Clasificacion";
	static final String GUARDAR = "Guardar";
	static final String CLASIFICAR = "Clasificar";
	
	//componentes de la ventana
	private JMenuBar barraSuperior;
	private JMenu menuArchivo, menuEntrenar, menuClasificar;
	private JMenuItem abrirEntrenamiento, abrirClasificacion, salir;
	private JFileChooser fileChooser;
	private JButton examinarArchivoEntrenamiento, examinarArchivoClasificacion, examinarArchivoFinal, clasificar;
	private TextField rutaArchivoEntrenamiento, rutaArchivoClasificacion, rutaArchivoFinal, k;
	private Label titulo1, titulo2, titulo3;
	
	
	//componentes del sistema
	File archivoEntrenamiento, archivoClasificacion;
	String rutaArchivoResultado;
	int valork = 0;
	
	NearestNeighbors nn;
	String[] etiquetas = null;
	
	public VentanaPrincipal(){
		//define el tamanio de la ventana
		this.setSize(510, 320);
		
		//establece que al presionar cerrar termine la ejecucion
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//define el titulo de la ventana
		this.setTitle("k - Nearest Neighbors");
		
		//desactiva el maximizado de la ventana
        this.setResizable(false);
		
        //crea y agrega la barra superior
        this.crearBarraHerramientas();
        
        fileChooser = new JFileChooser();       
        
        setLayout(null);
        
        //label de entrenamiento
        titulo1 = new Label("Seleccione el Archivo de Entrenamiento");
        titulo1.setBounds(40, 25, 300,25);
        this.add(titulo1);
        
        //label de clasificacion
        titulo2 = new Label("Seleccione el Archivo de Clasificación");
        titulo2.setBounds(40, 80, 300,25);
        this.add(titulo2);
        
        //label de guardado
        titulo3 = new Label("Seleccione Carpeta de Destino");
        titulo3.setBounds(40, 135, 300,25);
        this.add(titulo3);
        
        
        //field archivo entrenamiento
        this.rutaArchivoEntrenamiento = new TextField( "",30 );
        this.rutaArchivoEntrenamiento.setBounds(40,50,300, 25);
        this.rutaArchivoEntrenamiento.setEditable(false);
        this.add(this.rutaArchivoEntrenamiento);
    
        //field archivo clasificacion
        this.rutaArchivoClasificacion = new TextField( "",30 );
        this.rutaArchivoClasificacion.setBounds(40,105,300, 25);
        this.rutaArchivoClasificacion.setEditable(false);
        this.add(this.rutaArchivoClasificacion);
        
        //field archivo final
        this.rutaArchivoFinal = new TextField("",30 );
        this.rutaArchivoFinal.setBounds(40,160,300, 25);
        this.rutaArchivoFinal.setEditable(false);
        this.add(this.rutaArchivoFinal);
       
        
        //agrega el boton de archivo de entrenamiento
        this.examinarArchivoEntrenamiento = new JButton("Examinar");
        this.examinarArchivoEntrenamiento.setBounds(380,50,100,25);
        this.examinarArchivoEntrenamiento.setActionCommand(ABRIRARCHIVO1);
        this.add(this.examinarArchivoEntrenamiento);
        
        //agrega el boton de archivo de clasificacion
        this.examinarArchivoClasificacion = new JButton("Examinar");
        this.examinarArchivoClasificacion.setBounds(380,105,100,25);
        this.examinarArchivoClasificacion.setActionCommand(ABRIRARCHIVO2);
        this.add(this.examinarArchivoClasificacion);
        
        //agrega boton de ruta archivo generado
        this.examinarArchivoFinal = new JButton("Examinar");
        this.examinarArchivoFinal.setBounds(380,160,100,25);
        this.examinarArchivoFinal.setActionCommand(GUARDAR);
        this.add(this.examinarArchivoFinal);
        
        //agrega boton clasificar
        this.clasificar = new JButton("Clasificar");
        this.clasificar.setBounds(210,210,90,35);
        this.clasificar.setActionCommand(CLASIFICAR);
        this.add(this.clasificar);
        
        //anade el metodo para escuchar los eventos
        this.anadeListener();
        
		//hace visible el frame
		this.setVisible(true);
	}
	
	private void crearBarraHerramientas(){
		barraSuperior = new JMenuBar();
	
		//creamos el primer menu y sus items
		menuArchivo = new JMenu("Archivo");
		
		salir = new JMenuItem("Salir");
		salir.setActionCommand(SALIR);
		menuArchivo.add(salir);
		
		
		
		//segundo menu
		menuEntrenar = new JMenu("Entrenar");
		
		abrirEntrenamiento = new JMenuItem("Cargar Datos");
		abrirEntrenamiento.setActionCommand(ABRIRARCHIVO1);
		menuEntrenar.add(abrirEntrenamiento);
		
		//tercer menu
		menuClasificar = new JMenu("Clasificar");
		
		abrirClasificacion = new JMenuItem("Cargar Datos");
		abrirClasificacion.setActionCommand(ABRIRARCHIVO2);
		menuClasificar.add(abrirClasificacion);
		
		
		//anade componentes a la barra
		barraSuperior.add(menuArchivo);
		barraSuperior.add(menuEntrenar);
		barraSuperior.add(menuClasificar);
		
		setJMenuBar(barraSuperior);
		
	}
	
	
	/**
	 * 
	 * Anade los listener de cada objeto
	 * */
	private void anadeListener(){
		abrirEntrenamiento.addActionListener(this);
		abrirClasificacion.addActionListener(this);
		salir.addActionListener(this);
		examinarArchivoClasificacion.addActionListener(this);
		examinarArchivoEntrenamiento.addActionListener(this);
		examinarArchivoFinal.addActionListener(this);
		clasificar.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		String item = e.getActionCommand();
		
		switch(item){
			case "Abrir Archivo Entramiento":
				archivoEntrenamiento = obtenerArchivo();
				if(archivoEntrenamiento!=null){
					this.rutaArchivoEntrenamiento.setText(archivoEntrenamiento.getAbsolutePath());
					
				}else{
					
				}
				
				break;
			case "Abrir Archivo Clasificacion":
				archivoClasificacion = obtenerArchivo();
				if(archivoClasificacion!=null){
					this.rutaArchivoClasificacion.setText(archivoClasificacion.getAbsolutePath());
					
				}else{
					
				}
				break;
			case "Guardar":
				this.rutaArchivoResultado = obtenerRutaDeGuardado();
				if(rutaArchivoResultado!=null){
					this.rutaArchivoFinal.setText(rutaArchivoResultado);
				}else{
					
				}
				
				break;
				
			case "Clasificar":
				try{
					PrintStream out = new PrintStream(new FileOutputStream(rutaArchivoResultado+".log"));
					System.setOut(out);
					String valorkString = JOptionPane.showInputDialog("Ingrese el valor de k");
					if(!valorkString.equalsIgnoreCase("auto"))
						valork = Integer.parseInt(valorkString);
					inicializarNearestNeighbors(valork);
					entrenamiento(archivoEntrenamiento);
					clasificar(archivoClasificacion,rutaArchivoResultado);
				}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null,"Por favor ingrese un número");
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null,"Error en el archivo");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,"No se pudo crear el archivo");
				}
				break;
				
			case "Salir":
				this.salir();
				break;
				
			default:
				break;
		}
		
	}
	
	private void clasificar(File f, String r) throws IOException{
		File archivoGuardado = new File(r);
		archivoGuardado.createNewFile();
		
		//Creamos los objetos para escribir en el archivo creado
		FileWriter fw = new FileWriter(archivoGuardado);
		PrintWriter pw = new PrintWriter(fw);
		
		Scanner scan = new Scanner(f);
		String linea = "";
		String[] etiquetas = null;
		String[] aux;
		double[] valores;
		//String labelTarget = null;
		String target;
	
		while(scan.hasNextLine()){
			linea = scan.nextLine();
			
			aux = linea.split(";");
			valores = new double[aux.length];
			for(int j=0;j<aux.length;j++)
				valores[j] = Double.parseDouble(aux[j]);
				
			Punto tupla = new Punto(etiquetas,valores);
			target = nn.getClass(tupla);
			for(int i = 0;i<tupla.getFeatures().length;i++)
				pw.print(tupla.getFeatures()[i]+";");
			pw.print(target+"\n");
			
				/*
				System.out.print("labels: ");
				for(int k2=0;k2<tupla.getLabels().length;k2++){
					System.out.print(tupla.getLabels()[k2]+" ");
				}
				System.out.println("valores: ");
				for(int k2=0;k2<tupla.getFeatures().length;k2++){
					System.out.print(tupla.getFeatures()[k2]+" ");
				}
				System.out.println(tupla.getTarget());*/
		}
			//System.out.println(labelTarget);
		scan.close();
		pw.close();
		fw.close();
	}
	
	private String obtenerRutaDeGuardado(){
		fileChooser.setFileHidingEnabled(true);
		int resultado = fileChooser.showSaveDialog(null);
		
		if(resultado == JFileChooser.APPROVE_OPTION){
			return fileChooser.getSelectedFile().getAbsolutePath();
		}else{
			if(resultado == JFileChooser.ERROR_OPTION){
				System.err.println("Save command cancelled by user.");
				return null;
			}else{
				if(resultado == JFileChooser.CANCEL_OPTION){
					System.err.println("Save command cancelled by user.");
					return null;
				}
				
			}
		}
		return null;
	}
	
	/**
	 * metodo que retorna un archivo ya sea de 
	 * entrenamiento o de clasificacion
	 * */
	private File obtenerArchivo(){
		fileChooser.setFileHidingEnabled(true);
		int resultado = fileChooser.showOpenDialog(null);
		
		if(resultado == JFileChooser.APPROVE_OPTION){
			return fileChooser.getSelectedFile();
		}else{
			if(resultado == JFileChooser.ERROR_OPTION){
				System.err.println("Save command cancelled by user.");
				return null;
			}else{
				if(resultado == JFileChooser.CANCEL_OPTION){
					System.err.println("Save command cancelled by user.");
					return null;
				}
				
			}
		}
		return null;
	}
	
	private void inicializarNearestNeighbors(int k){
		nn = new NearestNeighbors(k);
	}
	
	private void entrenamiento(File f) throws FileNotFoundException{
		Scanner scan = new Scanner(f);
		String linea = "";
		String[] aux;
		double[] valores;
		String labelTarget = null;
		String target;
		int i=0;
		while(scan.hasNextLine()){
			linea = scan.nextLine();
			//la primera linea contiene las etiquetas
			if(i==0){
				aux = linea.split(";");
				etiquetas = Arrays.copyOfRange(aux,0,aux.length-1);
				labelTarget = aux[aux.length-1];
			}else{
				aux = linea.split(";");
				valores = new double[aux.length-1];
				for(int j=0;j<aux.length-1;j++)
					valores[j] = Double.parseDouble(aux[j]);
				target = aux[aux.length-1];
				Punto tupla = new Punto(etiquetas,valores,labelTarget,target);
				nn.training(tupla);
				/*
				System.out.print("labels: ");
				for(int k2=0;k2<tupla.getLabels().length;k2++){
					System.out.print(tupla.getLabels()[k2]+" ");
				}
				System.out.println("valores: ");
				for(int k2=0;k2<tupla.getFeatures().length;k2++){
					System.out.print(tupla.getFeatures()[k2]+" ");
				}
				System.out.println(tupla.getTarget());*/
			}
			//System.out.println(labelTarget);
			i++;
		}
		scan.close();
	}
	
	/**
	 * metodo para salir
	 * del programa
	 * */
	private void salir(){
		setVisible(false);
		System.exit(0);
	}
	
	
}
