import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        List<Sinhvien> DSSV = new ArrayList<>();
        String filename = "students.txt";
        studentManager.loadformFile(filename, DSSV);
        while (true) {
            studentManager.menuchinh();
            System.out.print("Chọn chức năng: ");
            int chon = sc.nextInt();
            switch (chon) {
                case 1:
                    while (true) {
                        System.out.println();
                        studentManager.menuCRUD();
                        System.out.print("Chọn chức năng: ");
                        int choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.println();
                                System.out.println("Nhập thông tin sinh viên thứ " + (DSSV.size() + 1));
                                Sinhvien addSV = studentManager.addSV(DSSV);
                                DSSV.add(addSV);
                                System.out.println("✅ Đã thêm sinh viên.");
                                break;
                            case 2:
                                System.out.println();
                                System.out.println("DANH SACH SINH VIEN");
                                System.out.println("Thông tin sinh viên thứ " + DSSV.size());
                                studentManager.hienthi(DSSV);
                                break;
                            case 3:
                                System.out.println();
                                System.out.print("Nhap ma sinh vien can sua: ");
                                String MaSVsua = sc.next();
                                studentManager.updateSV(DSSV, MaSVsua);
                                break;
                            case 4:
                                System.out.println();
                                System.out.print("Nhap ma sinh vien can xoa: ");
                                String MaSVxoa = sc.next();
                                studentManager.deleteSV(DSSV, MaSVxoa);
                                break;
                            case 5:
                                System.out.println();
                                DSSV.clear();
                                System.out.print("ĐÃ XÓA TOÀN BỘ DANH SÁCH");
                                break;
                        }
                        if(choice == 0) break;
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println();
                        studentManager.menutimkiem();
                        System.out.print("Chọn chức năng: ");
                        int choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                sc.nextLine();
                                System.out.print("Nhập mã sinh viên cần tìm: ");
                                String  MaSV = sc.nextLine();
                                studentManager.timkiemSV(DSSV, MaSV, choice, "",0);
                                break;
                            case 2:
                                sc.nextLine();
                                System.out.print("Nhập tên sinh viên cần tìm: ");
                                String  TenSV = sc.nextLine();
                                studentManager.timkiemSV(DSSV,"",choice, TenSV,0);
                                break;
                            case 3:
                                sc.nextLine();
                                System.out.print("Nhập điểm sinh viên cần tìm: ");
                                double  diem = sc.nextDouble();
                                studentManager.timkiemSV(DSSV,"",choice, "",diem);
                                break;
                        }
                        if(choice == 0) break;
                    }
                    break;
                case 3:
                    while (true) {
                        System.out.println();
                        studentManager.menuSapxep();
                        System.out.print("Chọn chức năng: ");
                        int choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.println();
                                System.out.println("DANH SACH SINH VIEN SAU KHI SAP XEP TANG DAN");
                                List<Sinhvien> DSSapxeptang = DSSV.stream().sorted(Comparator.comparingDouble(Sinhvien::getDiem)).collect(Collectors.toList());
                                DSSapxeptang.forEach(System.out::println);
                                break;
                            case 2:
                                System.out.println();
                                System.out.println("DANH SACH SINH VIEN SAU KHI SAP XEP GIAM DAN");
                                List<Sinhvien> DSSapxepgiam = DSSV.stream().sorted(Comparator.comparingDouble(Sinhvien::getDiem).reversed()).collect(Collectors.toList());
                                DSSapxepgiam.forEach(System.out::println);
                                break;
                            case 3:
                                System.out.println();
                                System.out.println("DANH SACH SINH VIEN SAU KHI SAP XEP THEO TEN A -> Z");
                                List<Sinhvien> DSSapxeptenA = DSSV.stream().sorted(Comparator.comparing(Sinhvien::getTenSV)).collect(Collectors.toList());
                                DSSapxeptenA.forEach(System.out::println);
                                break;
                            case 4:
                                System.out.println();
                                System.out.println("DANH SACH SINH VIEN SAU KHI SAP XEP THEO TEN Z -> A");
                                List<Sinhvien> DSSapxeptenZ = DSSV.stream().sorted(Comparator.comparing(Sinhvien::getTenSV)).collect(Collectors.toList());
                                DSSapxeptenZ.forEach(System.out::println);
                                break;
                        }
                        if(choice == 0) break;
                    }
                    break;
                case 4:
                    while (true) {
                        System.out.println();
                        studentManager.menuThongke();
                        System.out.print("Chọn chức năng: ");
                        int choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Điểm trung bình của toàn bộ sinh viên là: "+studentManager.thongke(DSSV, choice));
                            break;
                        case 2:
                            System.out.println("Điểm trung bình của sinh viên có điểm cao nhất là: "+studentManager.thongke(DSSV, choice));
                            break;
                        case 3:
                            System.out.println("Điểm trung bình của sinh viên có điểm thấp nhất là: "+studentManager.thongke(DSSV, choice));
                            break;
                    }
                    if(choice == 0) break;
                    }
                    break;
                case 5:
                    studentManager.savetoFile(filename,DSSV);
                    System.out.println("Đã lưu và thoát.");
                    return;
                case 0:
                    System.out.println("👋 Thoát chương trình.");
                    return;
                default:
                    System.out.println("⚠️ Lựa chọn không hợp lệ.");
            }
        }
    }
}