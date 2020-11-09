package victor.floripa.senac.ajudai.view.view.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.model.object.Midia;

public class GaleriaImagensAdapter extends RecyclerView.Adapter<GaleriaImagensAdapter.PersonViewHolder>{

    List<Midia> midiaList;
    Activity activity;
    Drawable drawable;
    RVComentariosAdapter.ComentarioOnClickListener eventoOnClickListener;

    public GaleriaImagensAdapter(Activity activity, List<Midia> midiaList) {
        this.activity = activity;
        this.midiaList = midiaList;
    }

    public GaleriaImagensAdapter() {
    }

    @NonNull
    @Override
    public GaleriaImagensAdapter.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //iniciarLista();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_galeria, parent, false);
        GaleriaImagensAdapter.PersonViewHolder pvh = new GaleriaImagensAdapter.PersonViewHolder(v);
        return pvh;
    }

    public void addAll(List<Midia> lista){
        midiaList.addAll(lista);
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final GaleriaImagensAdapter.PersonViewHolder holder, final int i) {

        carregarImagem(midiaList.get(i).getImagem());

        holder.imagem.setImageDrawable(drawable);
        holder.descricao.setText(midiaList.get(i).getDescricao());
    }

    public Midia getItem(int pos){
        return this.midiaList.get(pos);
    }

    @Override
    public int getItemCount() {
        return midiaList.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        View view;
        TextView descricao;
        ImageView imagem;

        PersonViewHolder(View view) {
            super(view);
            this.view = view;
            cv = (CardView)view.findViewById(R.id.cvGaleria);
            descricao = (TextView)view.findViewById(R.id.descricao_foto_galeria);
            imagem = (ImageView)view.findViewById(R.id.foto_galeria);
        }
    }


    public void carregarImagem(final String urlIcone) {
        final Target targetIcone = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d("tag", "on bitmap loaded");
                drawable = new BitmapDrawable(activity.getResources(), bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.d("tag", "on bitmap failed");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.d("tag", "on prepare load");
            }
        };
        Picasso.with(activity).load(urlIcone).placeholder(drawable).into(targetIcone);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface ComentarioOnClickListener {
        void OnClickComentario(View view, int position);
    }

}
