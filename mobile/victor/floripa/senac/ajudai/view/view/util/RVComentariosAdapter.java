package victor.floripa.senac.ajudai.view.view.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.model.object.Comentario;

public class RVComentariosAdapter extends RecyclerView.Adapter<RVComentariosAdapter.PersonViewHolder>{

    List<Comentario> comentariosList;
    Activity activity;
    ComentarioOnClickListener eventoOnClickListener;

    public RVComentariosAdapter(Activity activity, List<Comentario> comentariosList) {
        this.activity = activity;
        this.comentariosList = comentariosList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //iniciarLista();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_comentarios, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    public void addAll(List<Comentario> lista){
        comentariosList.addAll(lista);
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull final PersonViewHolder holder, final int i) {

        holder.nome.setText(comentariosList.get(i).getNome());
        holder.descricao.setText(comentariosList.get(i).getComentario());
        holder.estrelas.setRating(comentariosList.get(i).getEstrelas());
    }

    public Comentario getItem(int pos){
        return this.comentariosList.get(pos);
    }

    @Override
    public int getItemCount() {
        return comentariosList.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        View view;
        TextView nome;
        TextView descricao;
        RatingBar estrelas;

        PersonViewHolder(View view) {
            super(view);
            this.view = view;
            cv = (CardView)view.findViewById(R.id.cvComentarios);
            nome = (TextView)view.findViewById(R.id.tvNome_Comentario);
            descricao = (TextView)view.findViewById(R.id.tvDescricao_Comentario);
            estrelas = (RatingBar) view.findViewById(R.id.rb_comentarios);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface ComentarioOnClickListener {
        void OnClickComentario(View view, int position);
    }
}
