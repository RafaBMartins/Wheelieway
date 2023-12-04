package rafael.gabriel.caio.matheus.wheelieway.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import rafael.gabriel.caio.matheus.wheelieway.R;
import rafael.gabriel.caio.matheus.wheelieway.activities.HomeActivity;
import rafael.gabriel.caio.matheus.wheelieway.util.ImageCache;

public class ComentarioFotosAdapter extends RecyclerView.Adapter {

    HomeActivity homeActivity;

    List<String> fotoPaths;

    public ComentarioFotosAdapter(List<String> fotoPaths) {
        this.fotoPaths = fotoPaths;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_foto_cadastro_comentario, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int w = (int) homeActivity.getResources().getDimension(R.dimen.thumb_width);
        int h = (int) homeActivity.getResources().getDimension(R.dimen.thumb_height);
        ImageView item_foto_cadastro_comentario = holder.itemView.findViewById(R.id.fotoCadastroComentario);
        // somente agora o a imagem é obtida do servidor. Caso a imagem já esteja salva no cache da app,
        // não baixamos ela de novo
        ImageCache.loadImageUrlToImageView(homeActivity, holder.toString(), item_foto_cadastro_comentario, w, h);
    }

    @Override
    public int getItemCount() {
        return fotoPaths.size();
    }
}
