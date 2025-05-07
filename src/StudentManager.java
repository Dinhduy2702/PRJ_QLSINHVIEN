import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentManager {
    public static Scanner sc = new Scanner(System.in);
    public void menuchinh(){
        System.out.println("===== MENU CHÍNH =====");
        System.out.println("1. Quản lý sinh viên (CRUD)");
        System.out.println("2. Tìm kiếm");
        System.out.println("3. Sắp xếp");
        System.out.println("4. Thống kê");
        System.out.println("5. Lưu file");
        System.out.println("0. Thoát");
    }
    public void menuCRUD(){
        System.out.println("===== MENU CRUD =====");
        System.out.println("1. Thêm sinh viên");
        System.out.println("2. Hiển thị danh sách sinh viên");
        System.out.println("3. Cập nhập thông tin sinh viên");
        System.out.println("4. Xóa sinh viên");
        System.out.println("5. Xóa toàn bộ danh sách");
        System.out.println("0. Trở về menu chính");
    }
    public void menutimkiem(){
        System.out.println("===== MENU TÌM KIẾM =====");
        System.out.println("1. Tìm kiếm theo mã sinh viên");
        System.out.println("2. Tìm kiếm theo tên sinh viên");
        System.out.println("3. Tìm kiếm theo điểm sinh viên");
        System.out.println("0. Trở về menu chính");
    }
    public void menuSapxep(){
        System.out.println("===== MENU SẮP XẾP =====");
        System.out.println("1. Sắp xếp tăng dần theo điểm");
        System.out.println("2. Sắp xếp giảm dần theo điểm");
        System.out.println("3. Sắp xếp theo tên a -> z");
        System.out.println("3. Sắp xếp theo tên z -> a");
        System.out.println("0. Trở về menu chính");
    }
    public void menuThongke(){
        System.out.println("===== MENU THỐNG KÊ =====");
        System.out.println("1. Tính điểm trung bình toàn bộ sinh viên");
        System.out.println("2. Tính điểm trung bình sinh viên có điểm cao nhất");
        System.out.println("3. Tính điểm trung bình sinh viên có điểm thấp nhất");
        System.out.println("0. Trở về menu chính");
    }
    public void loadformFile(String filename, List<Sinhvien> DSSV){
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    // Tách từng phần theo định dạng mô tả
                    String MaSV = line.replaceAll(".*Mã sinh viên: (\\d+).*", "$1");
                    String TenSV = line.replaceAll(".*Tên sinh viên: ([^,]+).*", "$1");
                    String Diem = line.replaceAll(".*Điểm: ([0-9.]+).*", "$1");
                    double score = Double.parseDouble(Diem);
                    DSSV.add(new Sinhvien(MaSV,TenSV,score));
                } catch (Exception e) {
                    System.out.println("Dòng lỗi: " + line);
                }
            }
        }catch (IOException e){
            System.out.println("Không thể đọc file.");
        }
    }
    public void savetoFile(String filename, List<Sinhvien> DSSV){
        try(PrintWriter pw = new PrintWriter(new FileWriter(filename))){
            for(Sinhvien sv : DSSV){
                pw.println(sv.toString());
            }
        }catch (IOException e){
            System.out.println("Không thể ghi file.");
        }
    }
    public Sinhvien addSV(List<Sinhvien> DSSV){
         String MaSV;
         double diem;
        while (true) {
            System.out.print("Nhap ma sinh vien: ");
            MaSV = sc.nextLine();
            final String stringma = MaSV;
            boolean ktra = DSSV.stream().anyMatch(sv -> sv.getMaSV().equalsIgnoreCase(stringma));
            if (!ktra) break;
            System.out.println("Mã sinh viên đã tồn tại. Vui lòng nhập lại.");
        }
        System.out.print("Nhap ten sinh vien: ");
        String TenSV = sc.nextLine();
        while (true) {
            System.out.print("Nhap diem cua sinh vien: ");
            diem = Main.sc.nextDouble();
            if(diem >= 0 && diem <= 10) break;
            System.out.println("Điểm thanh điểm từ 0 -> 10, vui lòng nhập lại");
        }

        return new Sinhvien(MaSV, TenSV, diem);
    }


    public void updateSV(List<Sinhvien> DSSV,String MaSV){
        Sinhvien updateSinhvien = DSSV.stream().filter(sv -> sv.getMaSV().equalsIgnoreCase(MaSV)).findFirst().orElse(null);
        double DiemMoi;
        if(updateSinhvien == null){
            System.out.println("❌ KHÔNG TÌM THẤY SINH VIÊN CÓ MÃ " + MaSV);
            return;
        }
        System.out.print("Nhap ten sinh vien: ");
        String TenMoi  = sc.nextLine();
        updateSinhvien.setTenSV(TenMoi);
        while (true) {
            System.out.print("Nhap diem cua sinh vien: ");
             DiemMoi = sc.nextDouble();
            if(DiemMoi >= 0 && DiemMoi <= 10) break;
            System.out.println("Điểm thanh điểm từ 0 -> 10, vui lòng nhập lại");
        }
        updateSinhvien.setDiem(DiemMoi);
        System.out.println("Sinh viên có mã "+ MaSV +" đã được cập nhập thành công.");
    }
    public void deleteSV(List<Sinhvien> DSSV,String MaSV){
    boolean isRemoved = DSSV.removeIf(sv -> sv.getMaSV().equalsIgnoreCase(MaSV));
    if(!isRemoved){
        System.out.println("❌KHÔNG TÌM THẤY SINH VIÊN CÓ MÃ " + MaSV);
        return;
    }
        System.out.println("Sinh viên có mã "+ MaSV +" đã được xóa thành công.");
    }

    public void timkiemSV(List<Sinhvien> DSSV,String MaSV, int chon,  String TenSV, double Diem){
        if(chon ==1){ DSSV.stream().filter(sv -> sv.getMaSV().equalsIgnoreCase(MaSV)).findFirst().ifPresentOrElse(System.out::println, () -> System.out.println("❌Không tìm thấy sinh viên có mã: "+MaSV)); return;}
        if(chon ==2){  DSSV.stream().filter(sv -> sv.getTenSV().equalsIgnoreCase(TenSV)).findFirst().ifPresentOrElse(System.out::println, () -> System.out.println("❌Không tìm thấy sinh viên có tên: "+TenSV));return;}
        List<Sinhvien>DSSinhvien =  DSSV.stream().filter(sv -> sv.getDiem() == Diem).collect(Collectors.toUnmodifiableList());
        if(DSSinhvien.isEmpty()){
            System.out.println("❌Không tìm thấy sinh viên có điểm: "+Diem);
            return;
        }
        DSSinhvien.forEach(System.out::println);
    }
    public double thongke(List<Sinhvien> DSSV, int chon){
        if (chon ==1) {
            double tongdiem = DSSV.stream().mapToDouble(sv -> sv.getDiem()).sum();
            int count = DSSV.size();
            return tongdiem / count;
        }
        if (chon ==2) {
            double diemMax = DSSV.stream().map(sv -> sv.getDiem()).max(Double::compareTo).orElse(-1.0);
           List<Sinhvien> SVdiemMax = DSSV.stream().filter(sv -> sv.getDiem() == diemMax).toList();
           double tongdiemMaxTB= SVdiemMax.stream().mapToDouble(sv -> sv.getDiem()).sum();
           return tongdiemMaxTB/SVdiemMax.size();
        }
        if (chon ==3) {
            double diemMin = DSSV.stream().map(sv -> sv.getDiem()).min(Double::compareTo).orElse(11.0);
            List<Sinhvien> SVdiemMin = DSSV.stream().filter(sv -> sv.getDiem() == diemMin).toList();
            double tongdiemMinTB= SVdiemMin.stream().mapToDouble(sv -> sv.getDiem()).sum();
            return tongdiemMinTB/SVdiemMin.size();
        }
        return 0;
    }
    public void hienthi(List<Sinhvien> DSSV){
        DSSV.forEach(System.out::println);
    }
}
