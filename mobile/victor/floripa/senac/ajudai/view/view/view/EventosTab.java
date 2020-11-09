package victor.floripa.senac.ajudai.view.view.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.control.Tabs.EventoControlTab;
import victor.floripa.senac.ajudai.view.view.view.ui.main.PageViewModel;

public class EventosTab extends Fragment {
    private PageViewModel pageViewModel;
    EventoControlTab controlTab;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab_eventos, container, false);
            controlTab = new EventoControlTab(this.getActivity(), root);

        return root;
    }

    public EventosTab() {
    }

    public void irParaTelaEvento (View v){
        controlTab.telaEvento(v);
    }
}
