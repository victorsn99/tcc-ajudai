package victor.floripa.senac.ajudai.view.view.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.control.MainControl;
import victor.floripa.senac.ajudai.view.view.model.object.Instituicao;
import victor.floripa.senac.ajudai.view.view.model.object.InstituicaoDTO;

public class RVInstituicoesAdapter extends RecyclerView.Adapter<RVInstituicoesAdapter.InstituicaoViewHolder>{

    Activity activity;
    InstituicoesOnClickListener onClickListener;
    List<Instituicao> instituicaoList;
    ImageView icone, destaque;
    Drawable dIcone, dDestaque;
    ArrayAdapter<InstituicaoDTO> adapterInstituicoesDTO;
    List<InstituicaoDTO> instituicaoDTOList;
    MainControl control = new MainControl();

    public RVInstituicoesAdapter(Activity activity, List<Instituicao> instituicaoList, InstituicoesOnClickListener onClickListener) {
        this.activity = activity;
        this.instituicaoList = instituicaoList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public InstituicaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //iniciarLista();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_main, parent, false);
        InstituicaoViewHolder ivh = new InstituicaoViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull final InstituicaoViewHolder holder, final int i) {


            carregarIcone(instituicaoList.get(i).getIcone());
            carregarDestaque(instituicaoList.get(i).getDestaque());

        holder.nome.setText(instituicaoList.get(i).getNome());
        holder.descricao.setText(instituicaoList.get(i).getDescricao());
        holder.icone.setImageDrawable(dIcone);
        holder.destaque.setImageDrawable(dDestaque);

        onClickListener = new InstituicoesOnClickListener() {
            @Override
            public void OnClickInstituicao(View view, int position) {
                onClickListener.OnClickInstituicao(holder.view, i);
            }
        };

        /*if (onClickListener != null){
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.OnClickInstituicao(holder.itemView, i);
                }
            });
        }*/

    }

    public void addAll(List<Instituicao> lista){
        instituicaoList.addAll(lista);
        this.notifyDataSetChanged();
    }

    public Instituicao getItem(int pos){
        return this.instituicaoList.get(pos);
    }

    @Override
    public int getItemCount() {
        return instituicaoList.size();
    }


    public static class InstituicaoViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        View view;
        TextView nome;
        TextView descricao;
        ImageView icone;
        ImageView destaque;

        InstituicaoViewHolder(View view) {
            super(view);
            this.view = view;
            cv = (CardView)view.findViewById(R.id.cvInstituicoes);
            nome = (TextView)view.findViewById(R.id.nomeInstituicao);
            descricao = (TextView)view.findViewById(R.id.descricaoInstituicao);
            icone = (ImageView)view.findViewById(R.id.icone);
            destaque = (ImageView)view.findViewById(R.id.fotoDestaque);
        }
    }

    public void carregarIcone(final String urlIcone) {
        final Target targetIcone = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d("tag", "on bitmap loaded");
                dIcone = new BitmapDrawable(activity.getResources(), bitmap);
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
        Picasso.with(activity).load(urlIcone).placeholder(dIcone).into(targetIcone);
    }

    public void carregarDestaque(final String urlDestaque) {
        final Target targetDestaque = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d("tag", "on bitmap loaded");
                dDestaque = new BitmapDrawable(activity.getResources(), bitmap);
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
        Picasso.with(activity).load(urlDestaque).placeholder(dDestaque).into(targetDestaque);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface InstituicoesOnClickListener {
        void OnClickInstituicao(View view, int position);
    }
}
