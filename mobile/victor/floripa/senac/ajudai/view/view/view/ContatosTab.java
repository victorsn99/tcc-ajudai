package victor.floripa.senac.ajudai.view.view.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.control.Tabs.ContatosControlTab;
import victor.floripa.senac.ajudai.view.view.view.ui.main.PageViewModel;

public class ContatosTab extends Fragment {
    private PageViewModel pageViewModel;
    ContatosControlTab contatosControlTab;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab_contatos, container, false);
        contatosControlTab = new ContatosControlTab(root, this.getActivity());

        return root;
    }
}
