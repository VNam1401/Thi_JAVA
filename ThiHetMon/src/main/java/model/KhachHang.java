package model;

/*
  Tác giả: Nguyễn Văn Nam
 */
public class KhachHang {

    private String maso;
    private String hoten;
    private int loai;
    private double chisocu;
    private double chisomoi;

    public KhachHang() {
    }

    public KhachHang(String maso) {
        this.maso = maso;
    }

    public KhachHang(String maso, String hoten, int loai, double chisocu, double chisomoi) {
        this.maso = maso;
        this.hoten = hoten;
        this.loai = loai;
        this.chisocu = chisocu;
        this.chisomoi = chisomoi;
    }

    public String getMaso() {
        return maso;
    }

    public void setMaso(String maso) {
        this.maso = maso;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public double getChisocu() {
        return chisocu;
    }

    public void setChisocu(double chisocu) {
        this.chisocu = chisocu;
    }

    public double getChisomoi() {
        return chisomoi;
    }

    public void setChisomoi(double chisomoi) {
        this.chisomoi = chisomoi;
    }

    //sinh viên cài đặt xử lý cho các phương thức sau    
    public double getTieuThu() {
        return chisomoi - chisocu;
    }

    public double getTienTra() {
        double soKwh = getTieuThu();
        double tienTra = 0;
        if (loai == 1) { // Kinh doanh
            tienTra = soKwh * 4575;
        } else if (loai == 2) { // Sinh hoạt
            if (soKwh <= 50) {
                tienTra = soKwh * 1806;
            } else if (soKwh <= 100) {
                tienTra = soKwh * 1866;
            } else if (soKwh <= 200) {
                tienTra = soKwh * 2167;
            } else if (soKwh <= 300) {
                tienTra = soKwh * 2729;
            } else if ( soKwh <= 400) {
                tienTra = soKwh * 3050;
            } else {
                tienTra = soKwh * 3151;
            }
        }
        return tienTra * 1.08;
    }
    @Override
    public String toString() {
        return maso + ";" + hoten + ";" + getTieuThu() + ";" + getTienTra();
    }
}
