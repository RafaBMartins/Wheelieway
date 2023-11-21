package rafael.gabriel.caio.matheus.wheelieway.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import rafael.gabriel.caio.matheus.wheelieway.R;
import rafael.gabriel.caio.matheus.wheelieway.models.EstabelecimentoItem;

public class ListAdapter extends PagingDataAdapter<EstabelecimentoItem, rafael.gabriel.caio.matheus.wheelieway.adapter.MyViewHolder> {

    public ListAdapter(@NonNull DiffUtil.ItemCallback<EstabelecimentoItem> diffCallback) {
        super(diffCallback);
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
        tvNome.setText(estabelecimentoItem.fileName);

        TextView tvDistancia = holder.itemView.findViewById(R.id.tvDistanciaEstabelecimentoItem);
        tvDistancia.setText(estabelecimentoItem.fileName);

        Bitmap thumb1 = estabelecimentoItem.thumb;
        ImageView estabelecimentoImageView = holder.itemView.findViewById(R.id.imvEstabelecimentoItem);
        estabelecimentoImageView.setImageBitmap(thumb1);

        Bitmap thumb2 = estabelecimentoItem.thumb;
        ImageView tipoEstabelecimentoImageView = holder.itemView.findViewById(R.id.imvTipoEstabelecimentoItem);
        tipoEstabelecimentoImageView.setImageBitmap(thumb2);

        Bitmap thumb3 = estabelecimentoItem.thumb;
        ImageView seloEstabelecimentoImageView = holder.itemView.findViewById(R.id.imvSeloEstabelecimentoItem);
        seloEstabelecimentoImageView.setImageBitmap(thumb3);

        TextView tvNota = holder.itemView.findViewById(R.id.tvNotaEstabelecimentoItem);
        tvNota.setText(estabelecimentoItem.fileName);
    }
}
