package rafael.gabriel.caio.matheus.wheelieway.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import rafael.gabriel.caio.matheus.wheelieway.R;
import rafael.gabriel.caio.matheus.wheelieway.activities.HomeActivity;
import rafael.gabriel.caio.matheus.wheelieway.models.ComentarioItem;
import rafael.gabriel.caio.matheus.wheelieway.util.ImageCache;

public class ComentarioListAdapter extends PagingDataAdapter<ComentarioItem, rafael.gabriel.caio.matheus.wheelieway.adapter.MyViewHolder> {

    Fragment estabelecimentoFragment;

    HomeActivity homeActivity;

    public ComentarioListAdapter(Fragment estabelecimentoFragment, @NonNull DiffUtil.ItemCallback<ComentarioItem> diffCallback) {
        super(diffCallback);
        this.estabelecimentoFragment = estabelecimentoFragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_estabelecimento, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ComentarioItem comentarioItem = getItem(position);

        TextView tvNome = holder.itemView.findViewById(R.id.tvNomeUsuarioComentarioItem);
        tvNome.setText(comentarioItem.nome);

        TextView tvDescricao = holder.itemView.findViewById(R.id.tvDescricaoAvaliacaoComentarioItem);
        tvDescricao.setText(comentarioItem.descricao);

        TextView tvData = holder.itemView.findViewById(R.id.tvComentarioItemData);
        tvData.setText(comentarioItem.data);

        int w = (int) estabelecimentoFragment.getResources().getDimension(R.dimen.thumb_width);
        int h = (int) estabelecimentoFragment.getResources().getDimension(R.dimen.thumb_height);;
        ImageView imgComentario = holder.itemView.findViewById(R.id.imvComentarioItem);
        ImageCache.loadImageUrlToImageView(homeActivity, comentarioItem.imgFotoUsuario, imgComentario, w, h);

        TextView tvNota = holder.itemView.findViewById(R.id.tvComentarioItemNota);
        tvNota.setText(comentarioItem.nota);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                homeActivity.startViewComentarioActivity(comentarioItem.id);
            }
        });
    }
}
