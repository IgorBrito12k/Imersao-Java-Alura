import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB {
    
    public List<Conteudo> extraiConteudo(String json) {
        //extrair só os dados que interessam (titulo, poster, classiicação)
        var parser = new JsonParse();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        //popular a lista de conteudos
        for(Map<String, String> atributos : listaDeAtributos) {
            
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("image");
            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }
        return conteudos;
    }
}
