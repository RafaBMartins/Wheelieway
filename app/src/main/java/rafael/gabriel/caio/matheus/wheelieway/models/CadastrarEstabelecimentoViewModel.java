package rafael.gabriel.caio.matheus.wheelieway.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CadastrarEstabelecimentoViewModel extends AndroidViewModel {

    String currentPhotoPath = "";

    public String getCurrentPhotoPath() {
        return currentPhotoPath;
    }

    public void setCurrentPhotoPath(String currentPhotoPath) {
        this.currentPhotoPath = currentPhotoPath;
    }
    public CadastrarEstabelecimentoViewModel(@NonNull Application application) {super(application);}

    /**
     * Método que cria uma requisição HTTP para cadastrar um novo produto junto ao servidor web.
     * @param nome nome do estabelecimento
     * @param fotoEstabelecimento foto do estabelecimento
     * @param tipoEstabelecimento tipo do estabelecimento
     * @return true se o produto foi cadastrado junto ao servidor, false caso contrário
     */
    public LiveData<Boolean> cadastrarEstabelecimento(String fotoEstabelecimento, String nome, String tipoEstabelecimento, String selo, String estado, String cidade, String bairro, String tipoLogradouro, String logradouro, String numero, String latitude, String longitude) {

        // Cria um container do tipo MutableLiveData (um LiveData que pode ter seu conteúdo alterado).
        MutableLiveData<Boolean> result = new MutableLiveData<>();

        // Cria uma nova linha de execução (thread). O android obriga que chamadas de rede sejam feitas
        // em uma linha de execução separada da principal.
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Executa a nova linha de execução. Dentro dessa linha, iremos realizar as requisições ao
        // servidor web.
        executorService.execute(new Runnable() {

            /**
             * Tudo o que colocármos dentro da função run abaixo será executada dentro da nova linha
             * de execução.
             */
            @Override
            public void run() {

                // Criamos uma instância de ProductsRepository. É dentro dessa classe que estão os
                // métodos que se comunicam com o servidor web.
                WheelieWayRepository wheelieWay = new WheelieWayRepository(getApplication());

                // O método login envia os dados de autenticação ao servidor. Ele retorna
                // um booleano indicando true caso o login tenha sido feito com sucesso e false
                // em caso contrário
                boolean b = wheelieWay.cadastrarEstabelecimento(fotoEstabelecimento, nome, tipoEstabelecimento, estado, cidade, bairro, tipoLogradouro, logradouro, numero, latitude, longitude);

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                result.postValue(b);
            }
        });
        return result;
    }

}
