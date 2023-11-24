package rafael.gabriel.caio.matheus.wheelieway.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import rafael.gabriel.caio.matheus.wheelieway.R;
import rafael.gabriel.caio.matheus.wheelieway.models.CadastrarEstabelecimentoViewModel;
import rafael.gabriel.caio.matheus.wheelieway.util.Util;

public class CadastrarEstabelecimentoActivity extends AppCompatActivity {
    static int RESULT_TAKE_PICTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_estabelecimento);

        // obtenção do ViewModel
        CadastrarEstabelecimentoViewModel cadastrarEstabelecimentoViewModel = new ViewModelProvider(this).get(CadastrarEstabelecimentoViewModel.class);

        // O ViewModel guarda o local da última foto escolhida pelo usuário.
        // Aqui, verificamos se já existe uma foto selecionada pelo usuário. Se sim, nós setamos
        // essa foto no ImageView.
        String currentPhotoPath = cadastrarEstabelecimentoViewModel.getCurrentPhotoPath();
        if(!currentPhotoPath.isEmpty()) {
            ImageView imvEstabelecimentoCadastrarAvaliacao = findViewById(R.id.imvEstabelecimentoCadastrarEstabelecimento);
            // aqui carregamos a foto que está guardada dentro do arquivo currentPhotoPath dentro
            // de um objeto do tipo Bitmap. A imagem é carregada e sofre uma escala pra ficar
            // exatamente do tamanho do ImageView
            Bitmap bitmap = Util.getBitmap(currentPhotoPath, imvEstabelecimentoCadastrarAvaliacao.getWidth(), imvEstabelecimentoCadastrarAvaliacao.getHeight());
            imvEstabelecimentoCadastrarAvaliacao.setImageBitmap(bitmap);
        }

        // Quando o usuário clicar no botão adicionar...
        Button btnProximoCadastrarEstabelecimento = findViewById(R.id.btnProximoCadastrarEstabelecimento);
        btnProximoCadastrarEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Aqui nós desabilitamos o botão adicionar. Fazemos isso para impedir o usuário de
                // apertar esse botão várias vezes e, assim, cadastrar o mesmo produto de forma
                // repetida.
                v.setEnabled(false);

                // Abaixo, verificamos se o usuário preencheu todos os campos necessários. Caso não
                // exibimos uma mensagem toast para o usuário indicando qual campo ele precisa
                // preencher, habilitamos novamente o botão de adicionar e retornamos.
                EditText etInserirNomeCadastrarEstabelecimento = findViewById(R.id.etInserirNomeCadastrarEstabelecimento);
                String nome = etInserirNomeCadastrarEstabelecimento.getText().toString();
                if(nome.isEmpty()) {
                    Toast.makeText(CadastrarEstabelecimentoActivity.this, "O campo Nome do Produto não foi preenchido", Toast.LENGTH_LONG).show();
                    v.setEnabled(true);
                    return;
                }

                EditText etBairroCadastrarEstabelecimento = findViewById(R.id.etBairroCadastrarEstabelecimento);
                String bairro = etBairroCadastrarEstabelecimento.getText().toString();
                if(bairro.isEmpty()) {
                    Toast.makeText(CadastrarEstabelecimentoActivity.this, "O campo Preço do Produto não foi preenchido", Toast.LENGTH_LONG).show();
                    v.setEnabled(true);
                    return;
                }

                EditText etNumeroCadastrarEstabelecimento = findViewById(R.id.etNumeroCadastrarEstabelecimento);
                String numero = etNumeroCadastrarEstabelecimento.getText().toString();
                if(numero.isEmpty()) {
                    Toast.makeText(CadastrarEstabelecimentoActivity.this, "O campo Descrição do Produto não foi preenchido", Toast.LENGTH_LONG).show();
                    v.setEnabled(true);
                    return;
                }

                String id = "";

                String distancia = "";

                String nota = "";

                String selo = "";

                String tipoEstabelecimento = "";

                String estado = "";

                String cidade = "";

                String tipoLogradouro = "";

                String logradouro = "";

                String latitude = "";

                String longitude = "";

                String currentPhotoPath = cadastrarEstabelecimentoViewModel.getCurrentPhotoPath();
                if(currentPhotoPath.isEmpty()) {
                    Toast.makeText(CadastrarEstabelecimentoActivity.this, "O campo Foto do Produto não foi preenchido", Toast.LENGTH_LONG).show();
                    v.setEnabled(true);
                    return;
                }

                // Neste ponto, já verificamos que todos os campos foram preenchidos corretamente.
                // Antes enviar esses dados ao servidor, nós fazemos uma escala na imagem escolhida
                // para o produto. Fazemos isso porque a câmera do celular produz imagens muito grandes,
                // com resolução muito mais alta do que aquela que realmente precisamos. Logo, na
                // prática, o que fazemos aqui é diminuir o tamanho da imagem antes de enviá-la ao
                // servidor. Isso garante que será usado menos recurso de rede e de banco de dados
                // no servidor.
                //
                // A imagem é escalada de forma que sua altura fique em 300dp (tamanho do ImageView
                // que exibe os detalhes de um produto. A largura vai possuir
                // um tamanho proporcional ao tamamnho original.
                try {
                    int h = (int) getResources().getDimension(R.dimen.img_height);
                    Util.scaleImage(currentPhotoPath, -1, 2*h);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return;
                }

                // O ViewModel possui o método addProduct, que envia os dados do novo produto para o
                // servidor web.O servidor web recebe esses dados e cadastra um novo produto. Se o
                // produto foi cadastrado com sucesso, a app recebe o valor true. Se não o servidor
                // retorna o valor false.
                //
                // O método de addProduct retorna um LiveData, que na prática é um container que avisa
                // quando o resultado do servidor chegou.
                LiveData<Boolean> resultLD = cadastrarEstabelecimentoViewModel.cadastrarEstabelecimento(id, currentPhotoPath, nome, distancia, nota, tipoEstabelecimento, selo, estado, cidade, bairro, tipoLogradouro, logradouro, numero, latitude, longitude);

                // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado indicando
                // se o cadastro do produto deu certo ou não será guardado dentro do LiveData. Neste momento o
                // LiveData avisa que o resultado chegou chamando o método onChanged abaixo.
                resultLD.observe(CadastrarEstabelecimentoActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        // aBoolean contém o resultado do cadastro do produto. Se aBoolean for true, significa
                        // que o cadastro do produto foi feito corretamente. Indicamos isso ao usuário
                        // através de uma mensagem do tipo toast e finalizamos a Activity. Quando
                        // finalizamos a Activity, voltamos para a tela home, que mostra a lista de
                        // produtos.
                        if(aBoolean == true) {
                            Toast.makeText(CadastrarEstabelecimentoActivity.this, "Estabelecimento adicionado com sucesso", Toast.LENGTH_LONG).show();
                            // indica que a Activity terminou com resultado positivo e a finaliza
                            setResult(RESULT_OK);
                            finish();
                        }
                        else {
                            // Se o cadastro não deu certo, apenas continuamos na tela de cadastro e
                            // indicamos com uma mensagem ao usuário que o cadastro não deu certo.
                            // Reabilitamos também o botão de adicionar, para permitir que o usuário
                            // tente realizar uma nova adição de produto.
                            v.setEnabled(true);
                            Toast.makeText(CadastrarEstabelecimentoActivity.this, "Ocorreu um erro ao adicionar o estabelecimento", Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

        // Quando o usuário clica no ImageView que mostra a imagem do produto a ser cadastrado,
        // nós exibimos um menu que permite que ele escolha uma imagem tanto via câmera ou via
        // galeria.
        ImageView imvEstabelecimentoCadastrarEstabelecimento = findViewById(R.id.imvEstabelecimentoCadastrarEstabelecimento);
        imvEstabelecimentoCadastrarEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchGalleryOrCameraIntent();
            }
        });
    }

    /**
     * Esse método exibe um pequeno menu de opções que permite que o usuário escolha de onde virá
     * a imagem do produto: câmera ou galeria.
     */
    private void dispatchGalleryOrCameraIntent() {

        // Primeiro, criamos o arquivo que irá guardar a imagem.
        File f = null;
        try {
            f = createImageFile();
        } catch (IOException e) {
            Toast.makeText(CadastrarEstabelecimentoActivity.this, "Não foi possível criar o arquivo", Toast.LENGTH_LONG).show();
            return;
        }

        // Se o arquivo foi criado com sucesso...
        if(f != null) {

            // setamos o endereço do arquivo criado dentro do ViewModel
            CadastrarEstabelecimentoViewModel addProductViewModel = new ViewModelProvider(this).get(CadastrarEstabelecimentoViewModel.class);
            addProductViewModel.setCurrentPhotoPath(f.getAbsolutePath());

            // Criamos e configuramos o INTENT que dispara a câmera
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri fUri = FileProvider.getUriForFile(CadastrarEstabelecimentoActivity.this, "com.example.produtos.fileprovider", f);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fUri);

            // Criamos e configuramos o INTENT que dispara a escolha de imagem via galeria
            Intent galleryIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            galleryIntent.setType("image/*");

            // Criamos o INTENT que gera o menu de escolha. Esse INTENT contém os dois INTENTS
            // anteriores e permite que o usuário esolha entre câmera e galeria de fotos.
            Intent chooserIntent = Intent.createChooser(galleryIntent, "Pegar imagem de...");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] { cameraIntent });
            startActivityForResult(chooserIntent, RESULT_TAKE_PICTURE);
        }
        else {
            Toast.makeText(CadastrarEstabelecimentoActivity.this, "Não foi possível criar o arquivo", Toast.LENGTH_LONG).show();
            return;
        }


    }

    /**
     * Método que cria um arquivo vazio, onde será guardada a imagem escolhida. O arquivo é
     * criado dentro do espaço interno da app, no diretório PICTURES. O nome do arquivo usa a
     * data e hora do momento da criação do arquivo. Isso garante que sempre que esse método for
     * chamado, não haverá risco de sobrescrever o arquivo anterior.
     */
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File f = File.createTempFile(imageFileName, ".jpg", storageDir);
        return f;
    }

    /**
     * Esse método é chamado depois que o usuário escolhe a foto
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_TAKE_PICTURE) {

            // Pegamos o endereço do arquivo vazio que foi criado para guardar a foto escolhida
            CadastrarEstabelecimentoViewModel addProductViewModel = new ViewModelProvider(this).get(CadastrarEstabelecimentoViewModel.class);
            String currentPhotoPath = addProductViewModel.getCurrentPhotoPath();

            // Se a foto foi efetivamente escolhida pelo usuário...
            if(resultCode == RESULT_OK) {
                ImageView imvEstabelecimentoCadastrarEstabelecimento = findViewById(R.id.imvEstabelecimentoCadastrarEstabelecimento);

                // se o usuário escolheu a câmera, então quando esse método é chamado, a foto tirada
                // já está salva dentro do arquivo currentPhotoPath. Entretanto, se o usuário
                // escolheu uma foto da galeria, temos que obter o URI da foto escolhida:
                Uri selectedPhoto = data.getData();
                if(selectedPhoto != null) {
                    try {
                        // carregamos a foto escolhida em um bitmap
                        Bitmap bitmap = Util.getBitmap(this, selectedPhoto);
                        // salvamos o bitmao dentro do arquivo currentPhotoPath
                        Util.saveImage(bitmap, currentPhotoPath);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return;
                    }
                }

                // Carregamos a foto salva em currentPhotoPath com a escala correta e setamos no ImageView
                Bitmap bitmap = Util.getBitmap(currentPhotoPath, imvEstabelecimentoCadastrarEstabelecimento.getWidth(), imvEstabelecimentoCadastrarEstabelecimento.getHeight());
                imvEstabelecimentoCadastrarEstabelecimento.setImageBitmap(bitmap);
            }
            else {
                // Se a imagem não foi escolhida, deletamos o arquivo que foi criado para guardá-la
                File f = new File(currentPhotoPath);
                f.delete();
                addProductViewModel.setCurrentPhotoPath("");
            }
        }
    }
}
