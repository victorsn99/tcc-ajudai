package victor.floripa.senac.ajudai.view.view.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.control.Tabs.ComentarioControlTab;
import victor.floripa.senac.ajudai.view.view.view.ui.main.PageViewModel;

public class ComentarioTab extends Fragment {
    private PageViewModel pageViewModel;
    ComentarioControlTab comentarioControlTab;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab_comentarios, container, false);
        comentarioControlTab = new ComentarioControlTab(root, this.getActivity());
        return root;
    }

    public ComentarioTab() {
    }


    public void cadastrarComentario (){
        comentarioControlTab.telaCadastrarComentario();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        comentarioControlTab.onActivityResult(requestCode, resultCode, data);
    }

    public void atualizar() {
        comentarioControlTab.atualizar();
    }
}
