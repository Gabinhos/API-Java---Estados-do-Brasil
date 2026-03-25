
package utfpr.http;
//Municipio extends de estado, pois vai ter praticamente os mesmos atributos tirando a região, que é específicada aqui
public class Municipio extends Estados{
    private String regiao;

    public Municipio(){
    }
    
   public Municipio(String nome, int id, String sigla, String regiao){
       super(nome, id, sigla);
       this.regiao = regiao;
   }
    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String Região) {
        this.regiao = Região;
    }

    @Override
    public String toString() {
        return getNome();
    }
}
