package rafael.gabriel.caio.matheus.wheelieway.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import rafael.gabriel.caio.matheus.wheelieway.R;
import rafael.gabriel.caio.matheus.wheelieway.activities.HomeActivity;
import rafael.gabriel.caio.matheus.wheelieway.models.EstabelecimentoItem;
import rafael.gabriel.caio.matheus.wheelieway.util.ImageCache;

public class EstabelecimentoListAdapter extends PagingDataAdapter<EstabelecimentoItem, rafael.gabriel.caio.matheus.wheelieway.adapter.MyViewHolder> {

    HomeActivity homeActivity;
    public EstabelecimentoListAdapter(HomeActivity homeActivity, @NonNull DiffUtil.ItemCallback<EstabelecimentoItem> diffCallback) {
        super(diffCallback);
        this.homeActivity = homeActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.estabelecimento_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        EstabelecimentoItem estabelecimentoItem = getItem(position);

        TextView tvNome = holder.itemView.findViewById(R.id.tvEstabelecimentoItem);
        tvNome.setText(estabelecimentoItem.nome);

        TextView tvDistancia = holder.itemView.findViewById(R.id.tvDistanciaEstabelecimentoItem);
        tvDistancia.setText(estabelecimentoItem.distancia);

        int w = (int) homeActivity.getResources().getDimension(R.dimen.thumb_width);
        int h = (int) homeActivity.getResources().getDimension(R.dimen.thumb_height);
        ImageView imgEstabelecimento = holder.itemView.findViewById(R.id.imvEstabelecimentoItem);
        ImageCache.loadImageUrlToImageView(homeActivity, estabelecimentoItem.imgEstabelecimento, imgEstabelecimento, w, h);

        TextView tvNota = holder.itemView.findViewById(R.id.tvNotaEstabelecimentoItem);
        tvNota.setText(estabelecimentoItem.nota);

       holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                homeActivity.startViewEstabelecimentoActivity(estabelecimentoItem.id);
            }
        });
    }
}
