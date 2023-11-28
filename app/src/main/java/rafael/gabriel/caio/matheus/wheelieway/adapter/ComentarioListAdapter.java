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
import rafael.gabriel.caio.matheus.wheelieway.models.ComentarioItem;
import rafael.gabriel.caio.matheus.wheelieway.models.EstabelecimentoItem;
import rafael.gabriel.caio.matheus.wheelieway.util.ImageCache;

public class ComentarioListAdapter extends PagingDataAdapter<ComentarioItem, rafael.gabriel.caio.matheus.wheelieway.adapter.MyViewHolder> {

    Fragment estabelecimentoFragment;

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
/*
        ComentarioItem comentarioItem = getItem(position);

        TextView tvNome = holder.itemView.findViewById(R.id.);
        tvNome.setText(estabelecimentoItem.nome);

        TextView tvDistancia = holder.itemView.findViewById(R.id.tvDistanciaEstabelecimentoItem);
        tvDistancia.setText(estabelecimentoItem.distancia);

        int w = (int) homeActivity.getResources().getDimension(R.dimen.thumb_width);
        int h = (int) homeActivity.getResources().getDimension(R.dimen.thumb_height);;
        ImageView imgComentario = holder.itemView.findViewById(R.id.imvComentarioItem);
        ImageCache.loadImageUrlToImageView(homeActivity, comentarioItem.imgEstabelecimento, imgEstabelecimento, w, h);

        TextView tvNota = holder.itemView.findViewById(R.id.tvNotaEstabelecimentoItem);
        tvNota.setText(comentarioItem.nota);
    }*/
    }
}
