package rafael.gabriel.caio.matheus.wheelieway.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import rafael.gabriel.caio.matheus.wheelieway.R;
import rafael.gabriel.caio.matheus.wheelieway.models.RegistroViewModel;

public class RegistroActivity extends AppCompatActivity {

    RegistroViewModel registroViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
        registroViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);

        // Quando o usuário clicar no bptão cadastrar
        Button btnRegistrar =  findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Primeiro verificamos se o usuário digitou corretamente os dados de cadastro.
                // No nosso caso, apenas verificamos se o campo não está vazio no momento em que o
                // usuário clicou no botão cadastrar. Se o campo está vazio, exibimos uma mensagem para o
                // usuário indicando que ele não preencheu o campo e retornamos da função sem fazer
                // mais nada.
                EditText etNewNome =  findViewById(R.id.etNomeRegistro);
                final String newNome = etNewNome.getText().toString();
                if(newNome.isEmpty()) {
                    Toast.makeText(RegistroActivity.this, "Campo de nome não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etNewEmail =  findViewById(R.id.etEmailRegistro);
                final String newEmail = etNewEmail.getText().toString();
                if(newEmail.isEmpty()) {
                    Toast.makeText(RegistroActivity.this, "Campo de email não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etNewSenha =  findViewById(R.id.etSenhaRegistro);
                final String newSenha = etNewSenha.getText().toString();
                if(newSenha.isEmpty()) {
                    Toast.makeText(RegistroActivity.this, "Campo de senha não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etNewConfirmarSenha =  findViewById(R.id.etConfirmarSenhaRegistro);
                String newConfirmarSenha = etNewConfirmarSenha.getText().toString();
                if(newConfirmarSenha.isEmpty()) {
                    Toast.makeText(RegistroActivity.this, "Campo de checagem de senha não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                if(!newSenha.equals(newConfirmarSenha)) {
                    Toast.makeText(RegistroActivity.this, "Senha não confere", Toast.LENGTH_LONG).show();
                    return;
                }

                // O ViewModel possui o método register, que envia as informações para o servidor web.
                // O servidor web recebe as infos e cadastra um novo usuário. Se o usuário foi cadastrado
                // com sucesso, a app recebe o valor true. Se não o servidor retorna o valor false.
                //
                // O método de register retorna um LiveData, que na prática é um container que avisa
                // quando o resultado do servidor chegou.
                LiveData<Boolean> resultLD = registroViewModel.register(newNome, newEmail, newSenha);

                // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado indicando
                // se o cadastro deu certo ou não será guardado dentro do LiveData. Neste momento o
                // LiveData avisa que o resultado chegou chamando o método onChanged abaixo.
                resultLD.observe(RegistroActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        // aBoolean contém o resultado do cadastro. Se aBoolean for true, significa
                        // que o cadastro do usuário foi feito corretamente. Indicamos isso ao usuário
                        // através de uma mensagem do tipo toast e finalizamos a Activity. Quando
                        // finalizamos a Activity, voltamos para a tela de login.
                        if(aBoolean) {
                            Toast.makeText(RegistroActivity.this, "Novo usuario registrado com sucesso", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else {
                            // Se o cadastro não deu certo, apenas continuamos na tela de cadastro e
                            // indicamos com uma mensagem ao usuário que o cadastro não deu certo.
                            Toast.makeText(RegistroActivity.this, "Erro ao registrar novo usuário", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}