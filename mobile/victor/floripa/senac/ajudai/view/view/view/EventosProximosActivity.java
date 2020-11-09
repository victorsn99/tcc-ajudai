package victor.floripa.senac.ajudai.view.view.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.control.EventosProximosControl;

public class EventosProximosActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    EventosProximosControl control;
    SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_proximos);
        control = new EventosProximosControl(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setTitle("Próximos Eventos");
        iniciarComponentes();
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

    public void telaEvento(View view){
        control.telaEvento(view);
    }

    private void iniciarComponentes() {
        refreshLayout = findViewById(R.id.refresh_layout_eventos_proximos);
        refreshLayout.setOnRefreshListener(this);
    }


    @Override
    public void onRefresh() {
    //    control.atualizarPagina();
        refreshLayout.setRefreshing(false);
    }
}
