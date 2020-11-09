package victor.floripa.senac.ajudai.view.view.view.ui.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import victor.floripa.senac.ajudai.R;

public class GaleriaImagensAdapter extends PagerAdapter {
    private Context context;
    private int[] imagens = new int[] { R.drawable.compartilhar, R.drawable.perfil_botao, R.drawable.estrela };

    public GaleriaImagensAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imagens.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup pager, int position, Object object) {
        ((ViewPager) pager).removeView((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup pager, int position) {
        ImageView imagem = new ImageView(context);
        imagem.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imagem.setImageResource(imagens[position]);
        ((ViewPager) pager).addView(imagem, 0);
        return imagem;
    }
}
