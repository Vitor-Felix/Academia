/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import entidades.Clientes;
import java.util.ArrayList;

//tratamento de arquivos:
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.EOFException;

import java.io.File;
/**
 *
 * @author Vitor_Wind
 */
public class RegistradorClientes {
    
    private static RegistradorClientes instancia;//para se auto instanciar a si mesmo 
    // contado              r de clientes
    private int contadorMatricula; 
    // guarda os dados de CLIENTES
    private ArrayList<Clientes> listaDeClientes;
    
    private ObjectOutputStream output;
    private ObjectInputStream input;
    
    
    public void gravaArquivo(Clientes c, int indexGravacao){//escrever no arquivo
          try
        {
           String nomeDoArquivo = "cl"+ indexGravacao;
           output = new ObjectOutputStream( 
                new FileOutputStream("../Arquivo/"+nomeDoArquivo+".ser"));
        } // end try
        catch ( IOException ioException )
        {
            System.err.println(
             "Erro ao abrir o arquivo" );
        }
          
        try{
            output.writeObject(c);  
        }
        catch(IOException ioException){
            System.err.println("Erro ao escrever no arquivo");
        }
        
        try{
            output.close();
        }
        catch(IOException ioException){
            System.err.println("Erro ao fechar o arquivo");
        }
    } // end method agravaArquivo
    
    public Clientes leArquivo(int indexArquivo){

        Clientes c = new Clientes();
        
        try // open file
      {
         String nomeDoArquivo = "cl"+ (indexArquivo+1);   
         input = new ObjectInputStream(
            new FileInputStream("../Arquivo/"+nomeDoArquivo+".ser") );
      } // end try
      catch ( IOException ioException )
      {
         System.err.println( "Erro ao abrir o arquivo em leArquivo" );
      } 
        
        //++++++++++++++++++++++++++++++++++++++++++++
//String nome, int dia, int mes, int ano, double peso, boolean sexo, int matricula     
      System.out.printf( "%-10s%-12s%-12s%-10s%-12s%-12s%10s\n", "nome",
         "dia", "mes", "ano", "peso", "sexo", "matricula" );

      try // input the values from the file
      { 
            c = (Clientes)input.readObject();

            // display record contents
            System.out.printf( "%-10s%-12s%-12s%-10s%-12s%-12s%10s\n",
               c.getNome(), c.getDia(), c.getMes(),
               c.getAno(), c.getPeso(), c.isSexo(),c.getMatricula());
      } // end try
      catch( EOFException endOfFileException )
      {
         System.err.println( "Erro de fim de arquivo(aquele que retornava)" );
      } // end catch
      catch( ClassNotFoundException classNotFoundException )
      {
         System.err.println( "Unable to create object." );
      } // end catch
      catch( IOException ioException )
      {
         System.err.println( "Erro durante a leitura em leArquivo." );
      }
      
       try // close file and exit
      {
        input.close();
      } // end try
      catch ( IOException ioException )
      {
         System.err.println( "Error closing file." );
         System.exit( 1 );
      } 
       return c;
    }
   
    private void numeroDeMatricula(){//*****************************************8**********
        File pasta = new File("../Arquivo");
        int contador = 0;
        if(pasta.isDirectory()){
            String diretorio[] = pasta.list();
            for(String s: diretorio){
                contador++;
            }
            System.out.println("Contador de Arquivos:"+contador);
        }else{System.out.println("Contador de arquivos falhou!");} 
        
        setContadorMatricula(contador);
    }
    // retorna a instancia do próprio Registrador Clientes
    public static RegistradorClientes getInstance() {
        
        if (instancia == null) {
            
            instancia = new RegistradorClientes();
        }
        
        return instancia;
    }

    // construtor private
    private RegistradorClientes() {
        numeroDeMatricula();
        loadClientesParaTestes();
    }
    
    public void setContadorMatricula(int matricula){
        this.contadorMatricula = matricula;
    }
    public int getContadorMatricula() {
        return contadorMatricula;
    }
        
    public void inserirCliente(Clientes c) {
        int primeiraMatricula;
        System.out.println("Matricula antes de somar: " + getContadorMatricula());
        setContadorMatricula((getContadorMatricula()+1));
        primeiraMatricula = getContadorMatricula();
        System.out.println("Matricula depois de somar: "
                + getContadorMatricula());
        gravaArquivo(c, primeiraMatricula);
        System.out.println("Passou por Grava Arquivo");
        listaDeClientes.add(c);
    }
    
    
    public ArrayList<Clientes> obterListaClientes() {
        
        return listaDeClientes;
    }

    private void loadClientesParaTestes() { 
                
        listaDeClientes = new ArrayList<Clientes>();
        for (int i = 0; i < getContadorMatricula(); i++) {
            Clientes c; 
            c = leArquivo(i);
            c = new Clientes(
                    c.getNome(),  // nome 
                    c.getDia(), // dia
                    c.getMes(), // mes
                    c.getAno(), // ano
                    c.getPeso(), // peso
                    c.isSexo(), //sexo
                    c.getMatricula()         //matricula atual
                );
            System.out.println("Adcionado o numero: " + (i+1));
            listaDeClientes.add(c);
        }          
    }
 
    public void modificarCliente(Clientes c) {
        //o primeiro índice é zero
        if (listaDeClientes.contains(c)) {            
            int index = listaDeClientes.indexOf(c);
            gravaArquivo(c, (index + 1));
            System.out.println("Esse é o index gerado: " + index);
            listaDeClientes.set(index, c);           
        }       
    }
    
}
