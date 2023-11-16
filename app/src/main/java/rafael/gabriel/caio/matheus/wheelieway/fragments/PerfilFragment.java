package rafael.gabriel.caio.matheus.wheelieway.fragments;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import rafael.gabriel.caio.matheus.wheelieway.R;
import rafael.gabriel.caio.matheus.wheelieway.models.Perfil;
import rafael.gabriel.caio.matheus.wheelieway.util.Config;
import rafael.gabriel.caio.matheus.wheelieway.util.ImageCache;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters

    public PerfilFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance() {
        PerfilFragment fragment = new PerfilFragment();

        return fragment;
    }
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String email = Config.getLogin(getContext());

        PerfilViewModel perfilViewModel = new ViewModelProvider(this).get(PerfilViewModel.class);

        LiveData<Perfil> perfil = PerfilViewModel.getPerfilDetailsLD(pid);

        perfil.observe(this, new Observer<Perfil>() {
            @Override
            public void onChanged(Perfil perfil) {

                // product contém os detalhes do produto que foram entregues pelo servidor web
                if(perfil != null) {

                    // aqui nós obtemos a imagem do produto. A imagem não vem logo de cara. Primeiro
                    // obtemos os detalhes do produto. Uma vez recebidos os campos de id, nome, preço,
                    // descrição, criado por, usamos o id para obter a imagem do produto em separado.
                    // A classe ImageCache obtém a imagem de um produto específico, guarda em um cache
                    // o seta em um ImageView fornecido.
                    ImageView imvProductPhoto = findViewById(R.id.imvProductPhoto);
                    int imgHeight = (int) PerfilFragment.this.getResources().getDimension(R.dimen.img_height);
                    ImageCache.loadImageUrlToImageView(PerfilFragment.this, perfil.img, imvProductPhoto, -1, imgHeight);

                    // Abaixo nós obtemos os dados do produto e setamos na interfa de usuário
                    TextView tvName = findViewById(R.id.tvNomeUsuarioPerfil);
                    tvName.setText(perfil.nome);

                }
                else {
                    Toast.makeText(PerfilFragment.this, "Não foi possível obter os detalhes do pefil", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }*/
}