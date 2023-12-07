package rafael.gabriel.caio.matheus.wheelieway.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import rafael.gabriel.caio.matheus.wheelieway.R;
import rafael.gabriel.caio.matheus.wheelieway.models.EstabelecimentoItem;
import rafael.gabriel.caio.matheus.wheelieway.models.ViewEstabelecimentoViewModel;
import rafael.gabriel.caio.matheus.wheelieway.util.ImageCache;

public class ViewEstabelecimentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estabelecimento_item);

        // Para obter os detalhes do produto, a app envia o id do produto ao servidor web. Este
        // último responde com os detalhes do produto referente ao pid.

        // O pid do produto é enviado para esta tela quando o produto é clicado na tela de Home.
        // Aqui nós obtemos o pid.
        Intent i = getIntent();
        String pid = i.getStringExtra("pid");

        // obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
        ViewEstabelecimentoViewModel viewEstabelecimentoViewModel = new ViewModelProvider(this).get(ViewEstabelecimentoViewModel.class);

        // O ViewModel possui o método getProductDetailsLD, que obtém os detalhes de um produto em
        // específico do servidor web.
        //
        // O método getProductDetailsLD retorna um LiveData, que na prática é um container que avisa
        // quando o resultado do servidor chegou. Ele guarda os detalhes de um produto que o servidor
        // entregou para a app.
        LiveData<EstabelecimentoItem> product = viewEstabelecimentoViewModel.getEstabelecimentoDetailsLD(pid);

        // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado contendo uma produto
        // será guardado dentro do LiveData. Neste momento o
        // LiveData avisa que o produto chegou chamando o método onChanged abaixo.
        product.observe(this, new Observer<EstabelecimentoItem>() {
            @Override
            public void onChanged(EstabelecimentoItem estabelecimento) {

                // product contém os detalhes do produto que foram entregues pelo servidor web
                if(estabelecimento != null) {

                    // aqui nós obtemos a imagem do produto. A imagem não vem logo de cara. Primeiro
                    // obtemos os detalhes do produto. Uma vez recebidos os campos de id, nome, preço,
                    // descrição, criado por, usamos o id para obter a imagem do produto em separado.
                    // A classe ImageCache obtém a imagem de um produto específico, guarda em um cache
                    // o seta em um ImageView fornecido.
                    ImageView imvEstabelecimentoItem = findViewById(R.id.imvEstabelecimentoItem);
                    int imgHeight = (int) ViewEstabelecimentoActivity.this.getResources().getDimension(R.dimen.img_height);
                    ImageCache.loadImageUrlToImageView(ViewEstabelecimentoActivity.this, estabelecimento.imgEstabelecimento, imvEstabelecimentoItem, -1, imgHeight);

                    // Abaixo nós obtemos os dados do produto e setamos na interfa de usuário
                    TextView tvNome = findViewById(R.id.tvEstabelecimentoItem);
                    tvNome.setText(estabelecimento.nome);

                    TextView tvNota = findViewById(R.id.tvNotaEstabelecimentoItem);
                    tvNota.setText(estabelecimento.nota);

                    /**Continuar a preencher esses campos depois de perguntar pro Daniel o que fazer com o ImageView de Selo e TipoEstabelecimento*/


                }
                else {
                    Toast.makeText(ViewEstabelecimentoActivity.this, "Não foi possível obter os detalhes do estabelecimento", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
