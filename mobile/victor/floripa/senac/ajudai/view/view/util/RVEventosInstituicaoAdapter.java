package victor.floripa.senac.ajudai.view.view.util;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.model.object.Evento;
import victor.floripa.senac.ajudai.view.view.model.object.InstituicaoDTO;

public class RVEventosInstituicaoAdapter extends RecyclerView.Adapter<RVEventosInstituicaoAdapter.PersonViewHolder>{

    List<Evento> eventoList;
    Activity activity;
    Drawable dIcone;
    EventoOnClickListener eventoOnClickListener;
    ArrayAdapter<InstituicaoDTO> adapterInstituicoesDTO;
    List<InstituicaoDTO> instituicaoDTOList;

    public RVEventosInstituicaoAdapter(Activity activity, List<Evento> eventoList, EventoOnClickListener eventoOnClickListener) {
        this.activity = activity;
        this.eventoList = eventoList;
        this.eventoOnClickListener = eventoOnClickListener;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //iniciarLista();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_evento_na_instituicao, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    public void addAll(List<Evento> lista){
        eventoList.addAll(lista);
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final PersonViewHolder holder, final int i) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataInicio = sdf.format(eventoList.get(i).getDataInicio());
        String dataFim = sdf.format(eventoList.get(i).getDataFim());

        holder.nome.setText(eventoList.get(i).getNome());
        holder.descricao.setText(eventoList.get(i).getDescricao());
        holder.dataInicio.setText(dataInicio);
        holder.dataFim.setText(dataFim);

        eventoOnClickListener = new RVEventosInstituicaoAdapter.EventoOnClickListener() {
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
        TextView dataInicio;
        TextView dataFim;

        PersonViewHolder(View view) {
            super(view);
            this.view = view;
            cv = (CardView)view.findViewById(R.id.cvEventoInstituicao);
            nome = (TextView)view.findViewById(R.id.tvNome_EventoInstituicao);
            descricao = (TextView)view.findViewById(R.id.tvDescricao_EventoInstituicao);
            dataInicio = (TextView) view.findViewById(R.id.tvDataInicio_EventoInstituicao);
            dataFim = (TextView) view.findViewById(R.id.tvDataInicio_EventoInstituicao);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface EventoOnClickListener {
        void OnClickEvento(View view, int position);
    }
}
