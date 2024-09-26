package model;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import util.FileHelper;

/*
  Tác giả: Nguyễn Văn Nam
 */
public class QLKhachHang {

    private ArrayList<KhachHang> dsKhachHang;

    public QLKhachHang() {
        dsKhachHang = new ArrayList<>();
    }

    public QLKhachHang(ArrayList<KhachHang> dsKhachHang) {
        this.dsKhachHang = dsKhachHang;
    }

    public ArrayList<KhachHang> getDsKhachHang() {
        return dsKhachHang;
    }

    public void setDsKhachHang(ArrayList<KhachHang> dsKhachHang) {
        this.dsKhachHang = dsKhachHang;
    }

    //sinh viên cải đặt cho các phương thức xử lý sau
    public void DocKhachHang(String filename) throws IOException {
        dsKhachHang.clear();
        ArrayList<String> input = FileHelper.readFileText(filename);
        for (String item : input) {
            String[] arr = item.split(";");
            KhachHang kh = new KhachHang();
            kh.setMaso(arr[0]);
            kh.setHoten(arr[1]);
            kh.setLoai(2);
            kh.setChisocu(3);
            kh.setChisomoi(4);
            dsKhachHang.add(kh);
        }
    }

    public boolean GhiHoaDon(String filename) throws IOException {
        BufferedWriter wr = new BufferedWriter(new FileWriter(filename));
        for (KhachHang kh : dsKhachHang) {
            wr.write(kh.toString());
            wr.newLine();
        }
        wr.close();
        return false;
    }

    public void sapXepTheoLoaiHinh() {
        dsKhachHang.sort(Comparator.comparingInt(KhachHang::getLoai));
    }

    public double getTieuThuThapNhat() {
        return dsKhachHang.stream().mapToDouble(KhachHang::getTieuThu).min().orElse(0);
    }

    public double getTieuThuTrungBinh() {
        return dsKhachHang.stream().mapToDouble(KhachHang::getTieuThu).average().orElse(0);
    }

}
