package arsi0001.hs_kl.androfirequiz.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import arsi0001.hs_kl.androfirequiz.R;

public class SliderAdapter extends PagerAdapter{

    private Context _context;
    private LayoutInflater _layoutInflater;

    public SliderAdapter(Context context){
        this._context = context;
    }

    //Arrays
    public int[] slide_images = {
            R.drawable.group_10,
            R.drawable.group_11,
            R.drawable.group_12
    };

    //Arrays
    public String[] slide_ueberschriften = {
            "EAT",
            "SLEEP",
            "CODE"
    };

    public String[] slide_beschreibungen = {
            "Seite 1 ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam",
            "Seite 2 ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam",
            "Seite 3 ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam"
    };


    @Override
    public int getCount() {
        return slide_ueberschriften.length;
    }

    @Override
    public boolean isViewFromObject( View view, Object object) {
        return view == (RelativeLayout) object;
    }

    //Dient zur Erstellung des Slide-Effektes
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        _layoutInflater = (LayoutInflater) _context.getSystemService(_context.LAYOUT_INFLATER_SERVICE);
        View view = _layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView= (ImageView) view.findViewById(R.id.slide_image);
        TextView slideUeberschrift= (TextView) view.findViewById(R.id.slide_ueberschrift);
        TextView slideBeschreibung = (TextView) view.findViewById(R.id.slide_beschreibung);

        slideImageView.setImageResource(slide_images[position]);
        slideUeberschrift.setText(slide_ueberschriften[position]);
        slideBeschreibung.setText(slide_beschreibungen[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((RelativeLayout)object);
    }

}
