package production.jattbrand.musicapp.ui.main;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import production.jattbrand.musicapp.R;
import production.jattbrand.musicapp.modals.BasicSongDetail;
import production.jattbrand.musicapp.modals.SongDetail;

public class HomeHorizontalAdapter extends RecyclerView.Adapter<HomeHorizontalAdapter.ListsViewHolder> {

    private Listener listener;
    private String TAG = "ListsAdapter";
    private Context context;

    public interface Listener {
        void onCLick(View v, int position, BasicSongDetail songDetail);
    }

    ArrayList<BasicSongDetail> dataArrayList;

    public HomeHorizontalAdapter(Context context, ArrayList<BasicSongDetail> ar, Listener listener) {
        this.context = context;
        this.dataArrayList = ar;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeHorizontalAdapter.ListsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.song_card, parent, false);
        return new HomeHorizontalAdapter.ListsViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ListsViewHolder holder, int position) {

        if (dataArrayList != null) {
            BasicSongDetail songData = dataArrayList.get(holder.getAdapterPosition());

            if (listener != null) {
                holder.root.setOnClickListener(v -> listener.onCLick(v, position, songData));
            }
            holder.title.setText(songData.getName());
            holder.subtitle.setText(songData.getArtist());
            Glide.with(context)
                    .load(songData.getCover())
                    .into(holder.cover);
        } else {
            //display all caught
            holder.title.setText("You are All Caught !!");
            holder.title.setGravity(Gravity.CENTER);
            holder.title.setPadding(0, 20, 0, 20);
        }
    }

    @Override
    public int getItemCount() {
        return dataArrayList != null ? dataArrayList.size() : 1;
    }

    class ListsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_title)
        public TextView title;
        @BindView(R.id.card_subtitle)
        public TextView subtitle;
        @BindView(R.id.rootView)
        public View root;
        @BindView(R.id.card_cover)
        public ImageView cover;

        public ListsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
