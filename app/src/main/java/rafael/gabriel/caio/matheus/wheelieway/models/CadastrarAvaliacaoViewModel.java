package rafael.gabriel.caio.matheus.wheelieway.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CadastrarAvaliacaoViewModel extends AndroidViewModel {
    public CadastrarAvaliacaoViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Método que cria uma requisição HTTP para cadastrar uma nova avaliação junto ao servidor web.
     *
     * @param fotoUsuario   foto do usuário
     * @param nomeUsuario   nome do usuário
     * @param descricao     descrição do comentário
     * @param fotoAvaliacao foto do estabelecimento avaliado
     * @return true se o produto foi cadastrado junto ao servidor, false caso contrário
     */
    public LiveData<Boolean> cadastrarAvaliacao(Integer fotoUsuario, String nomeUsuario, String descricao, Integer fotoAvaliacao) {

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
                boolean b = wheelieWay.cadastrarAvaliacao(fotoUsuario, nomeUsuario, descricao, fotoAvaliacao);

                // Aqui postamos o resultado da operação dentro do LiveData. Quando fazemos isso,
                // quem estiver observando o LiveData será avisado de que o resultado está disponível.
                result.setValue(b);
            }
        });

        return result;
    }
}