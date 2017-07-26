package info.androidhive.bukutamu.fragments;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import info.androidhive.bukutamu.R;
import info.androidhive.bukutamu.data.DatabaseHandler;
import info.androidhive.bukutamu.data.Registrasi;


public class RegFragment extends Fragment{

    File file;
    private static final int CAMERA_REQUEST = 101;
    private static final int PIC_CROP = 023;
    private ImageView idcard;
    private String picturePath;

    public RegFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_reg, container, false);

        idcard = (ImageView) v.findViewById(R.id.card);

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HH:mma");
        DateFormat nmfoto = new SimpleDateFormat("yyyyMMddHHmm");
        DateFormat tgl2 = new SimpleDateFormat("dd-MM-yyyy");
// you can get seconds by adding  "...:ss" to it
        date.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        final String datang = date.format(currentLocalTime);
        final String tglan = tgl2.format(currentLocalTime);
        final String namafoto = nmfoto.format(currentLocalTime);

        final String keluar = "12:00";

        final String tgl="";
        final String gambar="";
        //Inflate the button
        Button ulang = (Button) v.findViewById(R.id.ulang);
        Button simpan = (Button) v.findViewById(R.id.simpan);

        //Inflate the Edittext
        final EditText tamu = (EditText) v.findViewById(R.id.namatamu);
        final EditText ketemu = (EditText) v.findViewById(R.id.ketemu);
        final EditText unit = (EditText) v.findViewById(R.id.unit);
        final EditText tujuan = (EditText) v.findViewById(R.id.tujuan);
        final EditText usaha = (EditText) v.findViewById(R.id.perusahaan);
        final EditText hp = (EditText) v.findViewById(R.id.handphone);
        final EditText alamat = (EditText) v.findViewById(R.id.alamat);
        final EditText catatan = (EditText) v.findViewById(R.id.catatan);

        ulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tamu.setText("");
                ketemu.setText("");
                unit.setText("");
                tujuan.setText("");
                usaha.setText("");
                hp.setText("");
                alamat.setText("");
                catatan.setText("");
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrasi reg = new Registrasi(tamu.getText().toString(),ketemu.getText().toString(),unit.getText().toString(),
                        tujuan.getText().toString(),usaha.getText().toString(),hp.getText().toString(),alamat.getText().toString()
                        ,catatan.getText().toString(),datang,keluar,tglan,picturePath);

                DatabaseHandler db = new DatabaseHandler(getActivity());
                db.regTamu(reg);

                List<Registrasi> registrasi = db.getData();
                for (Registrasi rg : registrasi) {
                    String log = "Id: "+rg.getID()+
                            " ,Nama Tamu: " + rg.getTamu() +
                            " ,Ketemu: " + rg.getSiapa() +
                            " ,Unit: " + rg.getUnit() +
                            " ,Tujuan: " + rg.getTujuan() +
                            " ,Perusahaan: " + rg.getUsaha() +
                            " ,Phone: " + rg.getHp() +
                            " ,Alamat: " + rg.getAlamat() +
                            " ,Catatan: " + rg.getCatatan() +
                            " ,Datang: " + rg.getJmDtg() +
                            " ,Keluar: " + rg.getjmKeluar() +
                            " ,Tgldtg: " + rg.getTgldtg() +
                            " ,Gambar: " + rg.getGambar();
                    // Writing Contacts to log
                    Log.d("Name: ", log);
                }
            }
        });

        idcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                    file = new File(Environment.getExternalStorageDirectory() + File.separator + "/Pictures/BK_" + namafoto + ".jpg");

                    getActivity().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    intent.putExtra("return-data", true);

                    startActivityForResult(intent, CAMERA_REQUEST);
                }catch (ActivityNotFoundException anfe){
                    //display an error message
                    String errorMessage = "Whoops - your device doesn't support capturing images!";
                    Toast toast = Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        return v;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if(requestCode == CAMERA_REQUEST){
                performCrop();
            }
            else if(requestCode == PIC_CROP){
                //get the returned data
                Bundle extras = data.getExtras();
                //get the cropped bitmap
                Bitmap thePic = extras.getParcelable("data");

                picturePath = file.getAbsolutePath();

                try {
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    thePic.compress(Bitmap.CompressFormat.JPEG, 80, bytes);

                    file.createNewFile();
                    FileOutputStream out = new FileOutputStream(file);
                    out.write(bytes.toByteArray());
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //retrieve a reference to the ImageView
                //ImageView picView = (ImageView)findViewById(R.id.picture);
                //display the returned cropped image
                idcard.setImageBitmap(thePic);
            }
        }

        try {

        }catch (Exception e) {
            Toast.makeText(getActivity(), "Maaf gagal mengambil foto.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void performCrop(){
        try{
            //call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(Uri.fromFile(file), "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            //indicate output X and Y
            cropIntent.putExtra("outputX", 120);
            cropIntent.putExtra("outputY", 120);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);
        } catch (ActivityNotFoundException anfe){
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
