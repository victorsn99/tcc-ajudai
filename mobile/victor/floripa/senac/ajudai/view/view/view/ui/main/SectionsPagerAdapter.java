package victor.floripa.senac.ajudai.view.view.view.ui.main;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import victor.floripa.senac.ajudai.R;
import victor.floripa.senac.ajudai.view.view.view.ComentarioTab;
import victor.floripa.senac.ajudai.view.view.view.ContatosTab;
import victor.floripa.senac.ajudai.view.view.view.DescricaoTab;
import victor.floripa.senac.ajudai.view.view.view.EventosTab;
import victor.floripa.senac.ajudai.view.view.view.GaleriaTab;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,
            R.string.tab_text_3, R.string.tab_text_4, R.string.tab_text_5};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if (position == 0){
            DescricaoTab descricaoTab = new DescricaoTab((Activity) mContext);
            return descricaoTab;
        } else if (position == 1){
            ComentarioTab comentarioTab = new ComentarioTab();
            return comentarioTab;
        } else if (position == 2){
            EventosTab eventosTab = new EventosTab();
            return eventosTab;
        } else if (position == 3){
            GaleriaTab galeriaTab = new GaleriaTab();
            return galeriaTab;
        } else if (position == 4){
            ContatosTab contatosTab = new ContatosTab();
            return contatosTab;
        }
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return TAB_TITLES.length;
    }




}