package info.androidhive.bukutamu.data;

/**
 * Created by Dell on 15-Jul-17.
 */

public class Registrasi {
    int _id;
    String _tamu;
    String _siapa;
    String _unit;
    String _tujuan;
    String _usaha;
    String _hp;
    String _alamat;
    String _catatan;
    String _tgldtg;
    String _jmdtg;
    String _jmkeluar;
    String _gambar;

    public Registrasi(){

    }

    public Registrasi(int id, String tamu, String siapa, String unit, String tujuan, String usaha, String hp, String alamat,
                      String catatan, String jmdtg, String jmkeluar, String tgldtg, String gambar){
        this._id = id;
        this._tamu = tamu;
        this._siapa = siapa;
        this._unit = unit;
        this._tujuan = tujuan;
        this._usaha = usaha;
        this._hp = hp;
        this._alamat = alamat;
        this._catatan = catatan;
        this._tgldtg = tgldtg;
        this._jmdtg = jmdtg;
        this._jmkeluar = jmkeluar;
        this._gambar = gambar;
    }

    public Registrasi(String tamu, String siapa, String unit, String tujuan, String usaha, String hp, String alamat,
                      String catatan, String jmdtg, String jmkeluar, String tgldtg, String gambar){
        this._tamu = tamu;
        this._siapa = siapa;
        this._unit = unit;
        this._tujuan = tujuan;
        this._usaha = usaha;
        this._hp = hp;
        this._alamat = alamat;
        this._catatan = catatan;
        this._tgldtg = tgldtg;
        this._jmdtg = jmdtg;
        this._jmkeluar = jmkeluar;
        this._gambar = gambar;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getTamu(){
        return this._tamu;
    }

    public void setTamu(String tamu){
        this._tamu = tamu;
    }

    public String getSiapa(){
        return this._siapa;
    }

    public void setSiapa(String siapa){
        this._siapa = siapa;
    }

    public String getUnit(){
        return this._unit;
    }

    public void setUnit(String unit){
        this._unit = unit;
    }

    public String getTujuan(){
        return this._tujuan;
    }

    public void setTujuan(String tujuan){
        this._tujuan = tujuan;
    }

    public String getUsaha(){
        return this._usaha;
    }

    public void setUsaha(String usaha){
        this._usaha = usaha;
    }

    public String getHp(){
        return this._hp;
    }

    public void setHp(String hp){
        this._hp = hp;
    }

    public String getAlamat(){
        return this._alamat;
    }

    public void setAlamat(String alamat){
        this._alamat = alamat;
    }

    public String getCatatan(){
        return this._catatan;
    }

    public void setCatatan(String catatan){
        this._catatan = catatan;
    }

    public String getJmDtg(){
        return this._jmdtg;
    }

    public void setJmDtg(String jmDtg){
        this._jmdtg = jmDtg;
    }

    public String getjmKeluar(){
        return this._jmkeluar;
    }

    public void setjmKeluar(String jmKeluar){
        this._jmkeluar = jmKeluar;
    }

    public String getTgldtg(){
        return this._tgldtg;
    }

    public void setTgldtg(String tgldtg){
        this._tgldtg = tgldtg;
    }

    public String getGambar(){
        return this._gambar;
    }

    public void setGambar(String gambar){
        this._gambar = gambar;
    }

}
