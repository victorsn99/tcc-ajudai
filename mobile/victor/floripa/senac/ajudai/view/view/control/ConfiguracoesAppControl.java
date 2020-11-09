package victor.floripa.senac.ajudai.view.view.control;

import android.app.Activity;
import android.content.Intent;

import victor.floripa.senac.ajudai.view.view.view.MainActivity;

public class ConfiguracoesAppControl {

    Activity activity;

    public ConfiguracoesAppControl(Activity activity) {
        this.activity = activity;
    }

    public ConfiguracoesAppControl() {
    }

    public void telaInicial (){
        Intent it = new Intent(activity, MainActivity.class);
        activity.startActivity(it);
    }
}
