package utfpr.http;

import java.awt.List;
import java.util.ArrayList;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashSet;
public class JsonToObjetos {
    
    ClienteHttp cliente = new ClienteHttp();//Objeto para acessar a url e devolver a string
 
    String temp;  // Uma string temporaria, como os dois métodos vão usar, deixei fora deles
    public ArrayList<Estados> buscarEstados(){
            ArrayList <Estados> lista_estados_brasil = new ArrayList<>(); // Um array list de estado para armazenas os estados
            String baseUrlEstados = "https://servicodados.ibge.gov.br/api/v1/localidades/estados"; //Deixai como base o url
            try{
            temp = cliente.buscaDados(baseUrlEstados); //Agora sim, fazendo a consulta e colocando na variável
            
            JSONArray lista_estados_json = new JSONArray(temp); //Instanciando o array de objetos json com a string retornada da API
                for(int i = 0; i<lista_estados_json.length(); i++){//Iterando por todo ele
                    JSONObject estado_json = lista_estados_json.getJSONObject(i);//para cada estado
                    
                    Estados estado = new Estados();//cria o objeto estado
                    estado.setId(estado_json.getInt("id"));//define id
                    estado.setSigla(estado_json.getString("sigla"));//define sigla
                    estado.setNome(estado_json.getString("nome"));//define nome
                    
                    lista_estados_brasil.add(estado);//Adiciona no ArrayList
                }
            return lista_estados_brasil;//No fim, retorna o array list
            }catch(Exception e){
                System.err.println("Erro ao processar o JSON");
                e.printStackTrace();//Mostra o erro que aconteceu
                return new ArrayList<>();//Retorna um array vazio
            }
}
    
    
    
public ArrayList<Municipio> buscarMunicipio(int id){
        
            ArrayList <Municipio> lista_municipio_por_estado = new ArrayList<>();
        
            String baseUrlMunicipio = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + id + "/municipios"; //Acessa a URL da API onde tem os estados
            temp = cliente.buscaDados(baseUrlMunicipio); // Pego a String e coloco numa String temporaria
            try{
            JSONArray lista_municipios = new JSONArray(temp); //Crio um array de JSON e passo a string inteira para ele
                for(int i = 0; i<lista_municipios.length(); i++){ //Itero por todo o array JSON, pegando cada chave e cadastrando os valores
                    JSONObject municipio_json = lista_municipios.getJSONObject(i); //Pego o municio
                    
                    Municipio municipio = new Municipio(); //Crio o objeto dele
                    municipio.setId(municipio_json.getInt("id"));//Defino o ID
                    municipio.setNome(municipio_json.getString("nome"));//Defino o nome
                    
                    //Para acessar a região, preciso ir mais "adentro" do JSON
                    JSONObject json_microrregiao = municipio_json.getJSONObject("microrregiao");//Acessando a microrregiao
                    JSONObject json_mesorregiao = json_microrregiao.getJSONObject("mesorregiao");//Acessando a mesorregiao
                    JSONObject json_UF = json_mesorregiao.getJSONObject("UF"); //Acessando a UF
                    municipio.setSigla(json_UF.getString("sigla"));//Aqui queremos a sigla da região, que fica na UF
                    JSONObject json_regiao = json_UF.getJSONObject("regiao");// Quando finalmente "chegamos" na região, podemos pegar os dados dela;
                    municipio.setRegiao(json_regiao.getString("nome"));
                    
                    lista_municipio_por_estado.add(municipio); //Adiciona o Municipio na lista
       
 
                }
            return lista_municipio_por_estado;
            }catch(Exception e){
                System.err.println("Erro ao processar o JSON");
                e.printStackTrace();//Mesma coisa do método acima
                return new ArrayList<>();
            }
} 
}
