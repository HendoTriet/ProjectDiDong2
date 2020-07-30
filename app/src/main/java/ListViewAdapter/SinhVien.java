package ListViewAdapter;

public class SinhVien {
    private String sStt,
            sHoTen,
            sKhoa,
            sMaSV,
            sGioiTinh,
            sMonHoc,
            sDiem;

    public SinhVien ( String sStt , String sHoTen , String sKhoa , String sMaSV , String sGioiTinh , String sMonHoc , String sDiem ) {
        this.sStt = sStt;
        this.sHoTen = sHoTen;
        this.sKhoa = sKhoa;
        this.sMaSV = sMaSV;
        this.sGioiTinh = sGioiTinh;
        this.sMonHoc = sMonHoc;
        this.sDiem = sDiem;
    }

    public String getsStt ( ) {
        return sStt;
    }

    public void setsStt ( String sStt ) {
        this.sStt = sStt;
    }

    public String getsHoTen ( ) {
        return sHoTen;
    }

    public void setsHoTen ( String sHoTen ) {
        this.sHoTen = sHoTen;
    }

    public String getsKhoa ( ) {
        return sKhoa;
    }

    public void setsKhoa ( String sKhoa ) {
        this.sKhoa = sKhoa;
    }

    public String getsMaSV ( ) {
        return sMaSV;
    }

    public void setsMaSV ( String sMaSV ) {
        this.sMaSV = sMaSV;
    }

    public String getsGioiTinh ( ) {
        return sGioiTinh;
    }

    public void setsGioiTinh ( String sGioiTinh ) {
        this.sGioiTinh = sGioiTinh;
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

    public SinhVien ( ) {
    }

    @Override
    public String toString ( ) {
        return "SinhVien{" +
                "sHoTen='" + sHoTen + '\'' +
                ", sKhoa='" + sKhoa + '\'' +
                ", sMaSV='" + sMaSV + '\'' +
                ", sGioiTinh='" + sGioiTinh + '\'' +
                ", sMonHoc='" + sMonHoc + '\'' +
                ", sDiem='" + sDiem + '\'' +
                '}';
    }
}
