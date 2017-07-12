/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
/**
 *
 * @author Vitor_Wind
 */
public class Clientes implements Serializable{
        
    //ATRIBUTOS
    private String nome;
    private int dia, mes, ano;
    private double peso;
    private boolean sexo;
    private int matricula;    
    
    public Clientes(){
        this("", 0, 0, 0, 0.0, false, 0);
    }
    
    public Clientes(String nome, int dia, int mes, int ano, double peso, boolean sexo, int matricula){
        this.nome = nome;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.peso = peso;
        this.sexo = sexo;
        this.matricula = matricula;
    }
    
    //**************** Set's & Get's **************************
      
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPeso() {
        return peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }

    public boolean isSexo() {
        return sexo;
    }
    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public int getMatricula() {
        return matricula;
    }
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
      
    //Sobrescrita do método geral de objeto, só para gerar um log de OK
    @Override
    public String toString() {
        //int idade = 2015 - ano;
        //String retornaSexo = sexo == true?"Masculino":"Feminino";
        String valores = 
                         ", nome= "+nome+
                         ", dia= "+dia+
                         ", mes= "+mes+
                         ", ano= "+ano+
                         ", peso= "+peso+
                         ", sexo= "+sexo+
                         ", matricula= "+matricula;            
        return valores;
    }
}
