package rafael.gabriel.caio.matheus.wheelieway.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rafael.gabriel.caio.matheus.wheelieway.models.EstabelecimentoItem;

public class EstabelecimentoComparator extends DiffUtil.ItemCallback<EstabelecimentoItem>{
    @Override
    public boolean areItemsTheSame(@NonNull EstabelecimentoItem oldItem, @NonNull EstabelecimentoItem newItem) {
        return oldItem.id.equals(newItem.id);
    }

    @Override
    public boolean areContentsTheSame(@NonNull EstabelecimentoItem oldItem, @NonNull EstabelecimentoItem newItem) {
        return oldItem.id.equals(newItem.id) &&
                oldItem.nome.equals(newItem.nome) &&
                oldItem.distancia.equals(newItem.distancia) &&
                oldItem.tipoEstabelecimento.equals(newItem.tipoEstabelecimento) &&
                oldItem.nota.equals(newItem.nota) &&
                oldItem.selo.equals(newItem.selo);
    }
}
