package victor.floripa.senac.ajudai.view.view.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.control.Tabs.DescricaoControlTab;
import victor.floripa.senac.ajudai.view.view.view.ui.main.PageViewModel;

public class DescricaoTab extends Fragment {
    Activity activity;
    private PageViewModel pageViewModel;
    DescricaoControlTab descricaoControlTab;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab_descricao, container, false);
            descricaoControlTab = new DescricaoControlTab(root, this.getActivity());
        return root;
    }

    public DescricaoTab (Activity activity) {
        this.activity = activity;
    }
    public DescricaoTab () {

    }




}
