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

import java.text.SimpleDateFormat;
import java.util.List;

import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.model.object.Evento;
import victor.floripa.senac.ajudai.view.view.model.object.Instituicao;
import victor.floripa.senac.ajudai.view.view.model.object.InstituicaoDTO;

public class RVEventosAdapter extends RecyclerView.Adapter<RVEventosAdapter.PersonViewHolder>{

    List<Evento> eventoList;
    Activity activity;
    Drawable dIcone;
    List<Instituicao> instituicaoList;
    EventoOnClickListener eventoOnClickListener;
    ArrayAdapter<InstituicaoDTO> adapterInstituicoesDTO;
    List<InstituicaoDTO> instituicaoDTOList;

    public RVEventosAdapter(Activity activity, List<Evento> eventoList, EventoOnClickListener eventoOnClickListener) {
        this.activity = activity;
        this.eventoList = eventoList;
        this.eventoOnClickListener = eventoOnClickListener;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //iniciarLista();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_evento, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    public void addAll(List<Evento> lista){
        eventoList.addAll(lista);
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final PersonViewHolder holder, final int i) {

        carregarIcone(eventoList.get(i).getInstituicao().getIcone());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String data = sdf.format(eventoList.get(i).getDataInicio());

        holder.nome.setText(eventoList.get(i).getNome());
        holder.descricao.setText(eventoList.get(i).getDescricao());
        holder.icone.setImageDrawable(dIcone);
        holder.dataInicio.setText(data);

        eventoOnClickListener = new RVEventosAdapter.EventoOnClickListener() {
            @Override
            public void OnClickEvento(View view, int position) {
                eventoOnClickListener.OnClickEvento(holder.view, i);
            }
        };
    }

    public Evento getItem(int pos){
        return this.eventoList.get(pos);
    }

    @Override
    public int getItemCount() {
        return eventoList.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        View view;
        TextView nome;
        TextView descricao;
        ImageView icone;
        TextView dataInicio;

        PersonViewHolder(View view) {
            super(view);
            this.view = view;
            cv = (CardView)view.findViewById(R.id.cvEventosProximos);
            nome = (TextView)view.findViewById(R.id.tvNome_EventosProximos);
            descricao = (TextView)view.findViewById(R.id.tvDescricao_EventosProximos);
            icone = (ImageView)view.findViewById(R.id.ivIcone_EventosProximos);
            dataInicio = (TextView) view.findViewById(R.id.tvDataInicio_EventosProximos);
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

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface EventoOnClickListener {
        void OnClickEvento(View view, int position);
    }
}
