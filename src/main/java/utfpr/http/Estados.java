package utfpr.http;

import java.util.ArrayList;
//Classe estados, usada para criar objetos do tipo estado que vão guardar estas informações
public class Estados {
   private String nome;
   private int id;
   private String sigla;

   public Estados(String nome, int id, String sigla){
       this.nome = nome;
       this.id = id;
       this.sigla = sigla;
   }
   
   public Estados(){
}
    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
   
}
  
   
