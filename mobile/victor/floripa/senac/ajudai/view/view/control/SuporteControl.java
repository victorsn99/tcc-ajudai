package victor.floripa.senac.ajudai.view.view.control;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.util.Properties;
import java.util.Random;

import javax.mail.Session;

import victor.floripa.senac.ajudai.R;

public class SuporteControl extends javax.mail.Authenticator{
    Activity activity;
    EditText emailLog;
    EditText senha;
    MultiAutoCompleteTextView texto;
    ImageView foto;
    EmailAttachment attachment = new EmailAttachment();
    Session session;

    public SuporteControl(Activity activity) {
        this.activity = activity;
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        emailLog = activity.findViewById(R.id.emailUsuario);
        senha = activity.findViewById(R.id.senhaEmail);
        texto = activity.findViewById(R.id.mensagemSuporte);
        foto = (ImageView) activity.findViewById(R.id.anexo);
    }

    public void enviar() {
        Random rnd = new Random(100000);
        Integer chamado = rnd.nextInt();

            Properties props = new Properties();
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.quitwait", "false");

            session = Session.getDefaultInstance(props, this);
        MultiPartEmail email = new MultiPartEmail();

        try {
            email.setDebug(true);
            email.setHostName("smtp.gmail.com");
            email.setAuthentication(emailLog.getText().toString(), senha.getText().toString());
            email.setSSL(true);
            email.addTo("victor.swoboda99@outlook.com", ""); //pode ser qualquer email
            email.setFrom(emailLog.getText().toString()); //será passado o email que você fará a autenticação
            email.setSubject("Suporte ajudai#" + chamado);
            email.setMsg(texto.getText().toString());
            email.send();


            final AlertDialog.Builder mensagem = new AlertDialog.Builder(activity);
            mensagem.setTitle("Mensagem");
            mensagem.setMessage("E-mail enviado com sucesso.");
            mensagem.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                }

            });
            mensagem.show();


        } catch (EmailException e) {
            final AlertDialog.Builder mensagem = new AlertDialog.Builder(activity);
            mensagem.setTitle("Mensagem");
            mensagem.setMessage("Algo deu errado! Verifique se as informações foram inseridas corretamente.");
            mensagem.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                }

            });
            mensagem.show();
        }
    }

    public void adicionarAnexo() {
        Intent intentGaleria = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        activity.startActivityForResult(intentGaleria, 10);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 10) {
                Uri imagemSelecionada = data.getData();
                //Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(imagemSelecionada));
                Drawable drawable = Drawable.createFromPath(String.valueOf(imagemSelecionada));
                foto.setImageDrawable(drawable);
                foto.setScaleType(ImageView.ScaleType.FIT_XY);

                attachment.setPath(String.valueOf(imagemSelecionada)); // Obtem o caminho do arquivo
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setDescription("Anexo");
                attachment.setName("Anexo"); // Obtem o nome do arquivo


            }
        }
    }
}
