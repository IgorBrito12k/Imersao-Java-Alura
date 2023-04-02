import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP r buscar os top 250 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        var <String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //System.out.println(body);

        //extrair só os dados que interessam (titulo, poster, classiicação)
        var parser = new JsonParse();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        //exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {

            
            File diretorio = new File("figurinhas/");
            diretorio.mkdir();

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            double classificacao1 = Double.parseDouble(filme.get("imDbRating"));

            String textoFigurinha;
            if (classificacao1 >= 9.0) {
                textoFigurinha = "Topzera";
            } else {
                textoFigurinha = "hmmm...";
            }

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "imaersãofigurinhas/" + titulo + ".png";

            var geradora = new GeradoraDeFigurinhas();
            geradora.cria(inputStream, nomeArquivo, textoFigurinha);

            // System.out.println("\u001b[1mTitulo: \u001b[1m" + filme.get("title"));
            // System.out.println("\u001b[1mPoster: \u001b[1m" + filme.get("image"));
            // System.out.println("\u001b[30m\u001b[45mClassificacao: \u001b[m");
            // double classificacao = Double.parseDouble(filme.get("imDbRating"));
            // int numeroEstrelinhas = (int) classificacao;
            // for (int n = 1; n <= numeroEstrelinhas; n++) {
            //     System.out.print("⭐");
            // }
            // System.out.println("\n");

            System.out.println(titulo);
            System.out.println();
        }
    }
}       
