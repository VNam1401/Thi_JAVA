package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.KhachHang;
import model.QLKhachHang;

/*
  Tác giả: Nguyễn Văn Nam
 */
public class FrmQLKhachHang extends JFrame {

    private JTable tblKhachHang;
    private JButton btDocFile, btGhiFile, btNhapDuLieukhachHang;

    private JTextField txtMaSo, txtHoTen, txtLoaiHinh, txtChiSoCu, txtChiSoMoi, txtTieuThu, txtTienTra;

    private DefaultTableModel model;
    private JTextField txtMax, txtMin, txtTB;

    private JCheckBox chkSapXep;

    private static final String FILE_NHAP = "input.txt";

    private QLKhachHang qlkh = new QLKhachHang();

    public FrmQLKhachHang(String title) {
        super(title);
        createGUI();
        setSize(1200, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        processEvent();
    }

    private void createGUI() {

        //tạo JTable
        String[] columnNames = {"Mã số", "Họ tên", "Loại hình", "Chỉ số cũ (kWh)", "Chỉ số mới(kWh)", "Tiêu thụ(kWh)", "Tiền trả(đồng)"};
        model = new DefaultTableModel(null, columnNames);
        tblKhachHang = new JTable(model);
        //tạo thành phần quản lý cuộn cho Jtable
        JScrollPane scrollTable = new JScrollPane(tblKhachHang);
        //tạo các nút lệnh
        JPanel p1 = new JPanel();
        p1.add(btDocFile = new JButton("Nhập dữ liệu khách hàng"));
        p1.add(btGhiFile = new JButton("Xuất hóa đơn thanh toán"));

        //tạo các nút JTextField thống kê 
        JPanel p2 = new JPanel();
        p2.add(new JLabel("Mức tiêu thụ thấp nhất:"));
        p2.add(txtMin = new JTextField(10));

        p2.add(new JLabel("Mức tiêu thụ cao nhất:"));
        p2.add(txtMax = new JTextField(10));

        p2.add(new JLabel("Mức tiêu thụ trung bình:"));
        p2.add(txtTB = new JTextField(10));

        p2.add(chkSapXep = new JCheckBox("Sắp xếp theo loại hình"));
        //add các thành phần vào cửa sổ
        add(p1, BorderLayout.NORTH);
        add(scrollTable, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);
        // Bảng dữ liệu khách hàng
        tblKhachHang = new JTable();
        model = new DefaultTableModel();
        model.addColumn("Mã số");
        model.addColumn("Họ tên");
        model.addColumn("Loại hình");
        model.addColumn("Chỉ số cũ");
        model.addColumn("Chỉ số mới");
        model.addColumn("Số kWh tiêu thụ");
        model.addColumn("Tiền phải trả");
        tblKhachHang.setModel(model);
        JScrollPane scrollPane = new JScrollPane(tblKhachHang);
        scrollPane.setBounds(50, 110, 500, 150);
        add(scrollPane);

    }

    private void processEvent() {
        btDocFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    qlkh.GhiHoaDon("output.txt");
                    JOptionPane.showMessageDialog(null, "Thành Công");
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(null, "Lỗi xuất file output.txt");
                }
            }
        });
        //xu ly ghi danh sach sinh vien ra file
        btGhiFile.addActionListener((e) -> {

            if (qlkh.GhiHoaDon(FILE_NHAP)) {
                JOptionPane.showMessageDialog(this, "Ghi file thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Ghi file thât bại");
            }

        });
        chkSapXep.addItemListener((e) -> {
            if (chkSapXep.isSelected()) {
                qlkh.sapXepTheoLoaiHinh();
                loadDataToJTable();
            }
        });
    }

    private void loadDataToJTable() {
        model.setRowCount(0);  //xoá các dòng trong JTable        
        for (KhachHang kh : qlkh.getDsKhachHang()) {
            model.addRow(new Object[]{kh.getMaso(), kh.getHoten(), kh.getLoai(), kh.getChisocu(), kh.getChisomoi()});
        }
    }
}
