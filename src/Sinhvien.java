public class Sinhvien {
    private String MaSV;
    private String TenSV;
    private double Diem;

    public Sinhvien() {}

    public Sinhvien(String maSV, String tenSV, double diem) {
        MaSV = maSV;
        TenSV = tenSV;
        Diem = diem;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String maSV) {
        MaSV = maSV;
    }

    public String getTenSV() {
        return TenSV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }

    public double getDiem() {
        return Diem;
    }

    public void setDiem(double diem) {
        Diem = diem;
    }

    @Override
    public String toString() {
        return "Sinh viên {" +
                "Mã sinh viên: " + MaSV +
                ", Tên sinh viên: " + TenSV +
                ", Điểm: " + Diem+ ", Xếp loại: " + getRank() +
                '}';
    }

    public String getRank() {
        if (Diem >= 8) return "Giỏi";
        else if (Diem >= 6.5) return "Khá";
        else if (Diem >= 5) return "Trung bình";
        else return "Yếu";
    }
}
