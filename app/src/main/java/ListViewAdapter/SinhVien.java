package ListViewAdapter;

public class SinhVien {
    private String sHoTen,sGioiTinh,sMaSV,sMonHoc,sDiem;

    public SinhVien ( ) {
    }


    public String getsGioiTinh ( ) {
        return sGioiTinh;
    }

    public void setsGioiTinh ( String sGioiTinh ) {
        this.sGioiTinh = sGioiTinh;
    }

    public SinhVien ( String sHoTen, String sGioiTinh , String sMaSV , String sMonHoc , String sDiem ) {
        this.sHoTen = sHoTen;
        this.sGioiTinh = sGioiTinh;
        this.sMaSV = sMaSV;
        this.sMonHoc = sMonHoc;
        this.sDiem = sDiem;
    }

    public String getsHoTen ( ) {
        return sHoTen;
    }

    public void setsHoTen ( String sHoTen ) {
        this.sHoTen = sHoTen;
    }

    public String getsMaSV ( ) {
        return sMaSV;
    }

    public void setsMaSV ( String sMaSV ) {
        this.sMaSV = sMaSV;
    }

    public String getsMonHoc ( ) {
        return sMonHoc;
    }

    public void setsMonHoc ( String sMonHoc ) {
        this.sMonHoc = sMonHoc;
    }

    public String getsDiem ( ) {
        return sDiem;
    }

    public void setsDiem ( String sDiem ) {
        this.sDiem = sDiem;
    }

    @Override
    public String toString ( ) {
        return "SinhVien{" +
                "sHoTen='" + sHoTen + '\'' +
                ", sGioiTinh='" + sGioiTinh + '\'' +
                ", sMaSV='" + sMaSV + '\'' +
                ", sMonHoc='" + sMonHoc + '\'' +
                ", sDiem='" + sDiem + '\'' +
                '}';
    }



}
