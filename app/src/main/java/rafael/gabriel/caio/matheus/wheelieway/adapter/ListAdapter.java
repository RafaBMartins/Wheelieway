package rafael.gabriel.caio.matheus.wheelieway.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;

import rafael.gabriel.caio.matheus.wheelieway.adapter.MyViewHolder;

public class ListAdapter extends PagingDataAdapter<ImageData, rafael.gabriel.caio.matheus.wheelieway.adapter.MyViewHolder> {

    public ListAdapter(@NonNull DiffUtil.ItemCallback<ImageData> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ImageData imageData = getItem(position);

        TextView tvName = holder.itemView.findViewById(R.id.tvName);
        tvName.setText(imageData.fileName);

        TextView tvDate = holder.itemView.findViewById(R.id.tvDate);
        tvDate.setText("Data: " + new SimpleDateFormat("HH:mmdd/MM/yyyy").format(imageData.date));

        TextView tvSize = holder.itemView.findViewById(R.id.tvSize);
        tvSize.setText("Tamanho: " + String.valueOf(imageData.size));

        Bitmap thumb = imageData.thumb;
        ImageView imageView = holder.itemView.findViewById(R.id.imThumb2);
        imageView.setImageBitmap(thumb);
    }
}
