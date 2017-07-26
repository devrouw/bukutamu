package info.androidhive.bukutamu.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import info.androidhive.bukutamu.R;
import info.androidhive.bukutamu.data.Registrasi;

/**
 * Created by Dell on 16-Jul-17.
 */

public class RefreshListAdapter extends ArrayAdapter {
    private Context context;
    private List<Registrasi> registrasi;
    private ViewHolder viewHolder;
    private int lastPosition;

    public RefreshListAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        registrasi = objects;
    }

    private class ViewHolder {
        TextView nomor;
        TextView nama;
        TextView tamu;
        TextView tgl;
        TextView jmDtg;
        TextView jmKeluar;
        ImageView foto;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder=null;
        if(convertView == null){
            LayoutInflater vi = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.fragment_list, null);

            holder=new ViewHolder();
            holder.nomor = (TextView) convertView.findViewById(R.id.nomor);
            holder.nama = (TextView) convertView.findViewById(R.id.name);
            holder.tamu = (TextView) convertView.findViewById(R.id.nama);
            holder.tgl = (TextView) convertView.findViewById(R.id.tgldtg);
            holder.jmDtg = (TextView) convertView.findViewById(R.id.jmdtg);
            holder.jmKeluar = (TextView) convertView.findViewById(R.id.jmplg);
            holder.foto = (ImageView) convertView.findViewById(R.id.image_view_refresh);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

//        Animation animation = AnimationUtils.loadAnimation(context, (position > lastPosition) ? R.anim.);

        Registrasi reg = registrasi.get(position);
        Bitmap bmImg = BitmapFactory.decodeFile(reg.getGambar());
        holder.nomor.setText(Integer.toString(reg.getID()));
        holder.nama.setText(reg.getTamu());
        holder.tamu.setText(reg.getSiapa());
        holder.tgl.setText(reg.getTgldtg());
        holder.jmDtg.setText(reg.getJmDtg());
        holder.jmKeluar.setText(reg.getjmKeluar());
        holder.foto.setImageBitmap(bmImg);
        return convertView;
    }
}
