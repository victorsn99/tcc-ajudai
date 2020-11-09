package victor.floripa.senac.ajudai.view.view.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.control.Tabs.GaleriaControlTab;
import victor.floripa.senac.ajudai.view.view.view.ui.main.PageViewModel;

public class GaleriaTab extends Fragment {
    Activity activity;
    private PageViewModel pageViewModel;
    GaleriaControlTab galeriaControlTab;
    SwipeRefreshLayout refreshLayout;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.tab_galeria, container, false);
        galeriaControlTab = new GaleriaControlTab(root, this.getActivity());
        activity = this.getActivity();
        return root;
    }



    public GaleriaTab(){
    }


    public void atualizar() {
        galeriaControlTab.atualizarPagina();
    }
}
