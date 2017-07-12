/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import entidades.Clientes;

/**
 *
 * @author Vitor_Wind
 */
public class ClientesController {
     
    // singleton
       
    public boolean cadastrarCliente(Clientes c) {
     
        boolean result = false;
        
        if (    c.getNome().length() > 0
                && c.getDia() != 0 
                && c.getMes() != 0
                && c.getAno() != 0
                && c.getPeso() != 0.0 //sexo não precisa ser verificado!
                ) {
            
            // tem que pegar matricula antes, para nao repetir adiciono logo aqui
            c.setMatricula(RegistradorClientes.getInstance().
                    getContadorMatricula() + 1);
            
            // insere no modelo de dados
            RegistradorClientes.getInstance().inserirCliente(c);
            
            System.out.println(c.toString());
            
            result = true;
        }
        
        
        return result;
    }

    public boolean atualizarCliente(Clientes c) {

        //*Clientes(String nome, int dia, int mes, int ano, double peso, boolean sexo, int matricula)
        boolean result = false;
        
        if (    c.getNome().length() > 0
                && c.getDia() != 0 
                && c.getMes() != 0
                && c.getAno() != 0
                && c.getPeso() != 0.0 //sexo não precisa ser verificado!
                && c.getMatricula() != 0) {                      

            RegistradorClientes.getInstance().modificarCliente(c);
            System.out.println("UPDATED -> " + c.toString());
            result = true;
        }        
        return result;
    }
}
