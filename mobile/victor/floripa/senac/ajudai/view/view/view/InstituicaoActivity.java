package victor.floripa.senac.ajudai.view.view.view;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.control.InstituicaoControl;
import victor.floripa.senac.ajudai.view.view.control.Tabs.ComentarioControlTab;
import victor.floripa.senac.ajudai.view.view.control.Tabs.DescricaoControlTab;
import victor.floripa.senac.ajudai.view.view.model.object.Instituicao;
import victor.floripa.senac.ajudai.view.view.util.Constantes;
import victor.floripa.senac.ajudai.view.view.view.ui.main.SectionsPagerAdapter;

public class InstituicaoActivity extends AppCompatActivity {

    SectionsPagerAdapter sectionsPagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    Resources resources;
    DescricaoTab descricaoTab;
    ComentarioTab comentarioTab;
    EventosTab eventosTab;
    GaleriaTab galeriaTab;
    ContatosTab contatosTab;
    DescricaoControlTab descricaoControlTab;
    ComentarioControlTab comentarioControlTab;
    InstituicaoControl instituicaoControl;
    public static String TITULO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instituicao);
        resources = getResources();
        inicializar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //galeriaTab.atualizar();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        comentarioTab.atualizar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                startActivity(new Intent(this, MainActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }

    private void inicializar(){
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        instituicaoControl = new InstituicaoControl(this);

        comentarioControlTab = new ComentarioControlTab();

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        setupViewPager(viewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        setTitle(TITULO);



        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        descricaoTab = new DescricaoTab();
        comentarioTab = new ComentarioTab();
        eventosTab = new EventosTab();
        galeriaTab = new GaleriaTab();
        contatosTab = new ContatosTab();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(descricaoTab, "Descrição");
        adapter.addFrag(comentarioTab, "Comentários");
        adapter.addFrag(eventosTab, "Eventos");
        adapter.addFrag(galeriaTab, "Galeria");
        adapter.addFrag(contatosTab, "Contatos");
        viewPager.setAdapter(adapter);
    }

    public void enviaMensagemParaOFragment(Instituicao instituicao, Fragment fragment){
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constantes.Params.PRM_INSTITUICAO, instituicao);
        fragment.setArguments(bundle);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public DescricaoTab getDescricaoTab() {
        return descricaoTab;
    }

    public void setDescricaoTab(DescricaoTab descricaoTab) {
        this.descricaoTab = descricaoTab;
    }

    public void cadastrarComentario (View root){
        comentarioTab.cadastrarComentario();
    }

    public void irParaTelaEvento (View root){
        eventosTab.irParaTelaEvento(root);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        comentarioTab.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show();
    }
}
