package victor.floripa.senac.ajudai.view.view.view;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.control.AdicionarComentarioControl;

public class AdicionarComentarioActivity extends AppCompatActivity {

    AdicionarComentarioControl control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_comentario);

        control = new AdicionarComentarioControl(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Adicionar comentários");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
               control.cancelar();
                break;
            default:break;
        }
        return true;
    }

    public void enviarComentario(View v){
        control.cadastrar();
    }
}
