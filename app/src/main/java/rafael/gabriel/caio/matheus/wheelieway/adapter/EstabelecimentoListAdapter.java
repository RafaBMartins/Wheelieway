package rafael.gabriel.caio.matheus.wheelieway.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;

import java.text.DecimalFormat;

import rafael.gabriel.caio.matheus.wheelieway.R;
import rafael.gabriel.caio.matheus.wheelieway.activities.HomeActivity;
import rafael.gabriel.caio.matheus.wheelieway.models.EstabelecimentoItem;
import rafael.gabriel.caio.matheus.wheelieway.util.ImageCache;

public class EstabelecimentoListAdapter extends PagingDataAdapter<EstabelecimentoItem, MyViewHolder> {

    HomeActivity homeActivity;

    DecimalFormat numberFormat = new DecimalFormat("#.00");
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

        ImageView imgEstabelecimento = holder.itemView.findViewById(R.id.imvEstabelecimentoItem);
        ImageCache.loadImageUrlToImageView(homeActivity, estabelecimentoItem.imgEstabelecimento, imgEstabelecimento, 100, 100);

        TextView tvNota = holder.itemView.findViewById(R.id.tvNotaEstabelecimentoItem);
        tvNota.setText(estabelecimentoItem.nota);

        ConstraintLayout constraintLayout = holder.itemView.findViewById(R.id.clEstabelecimentoItem);
       constraintLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                homeActivity.startViewEstabelecimentoActivity(estabelecimentoItem.id);
            }
        });
    }
}
