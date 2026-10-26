import java.util.ArrayList;
import java.util.List;

class PhanSo {
    private int tu;
    private int mau;

    public PhanSo() {
        this(0, 1);
    }

    public PhanSo(int tu, int mau) {
        if (mau == 0) {
            throw new IllegalArgumentException("Mẫu số không được bằng 0");
        }
        this.tu = tu;
        this.mau = mau;
        chuanHoa();
    }

    public int getTu() {
        return tu;
    }

    public int getMau() {
        return mau;
    }

    public void setTu(int tu) {
        this.tu = tu;
        chuanHoa();
    }

    public void setMau(int mau) {
        if (mau == 0) {
            throw new IllegalArgumentException("Mẫu số không được bằng 0");
        }
        this.mau = mau;
        chuanHoa();
    }

    @Override
    public String toString() {
        return mau == 1 ? String.valueOf(tu) : tu + "/" + mau;
    }

    public PhanSo cong(PhanSo b) {
        return new PhanSo(tu * b.mau + b.tu * mau, mau * b.mau);
    }

    public PhanSo tru(PhanSo b) {
        return new PhanSo(tu * b.mau - b.tu * mau, mau * b.mau);
    }

    public PhanSo nhan(PhanSo b) {
        return new PhanSo(tu * b.tu, mau * b.mau);
    }

    public PhanSo chia(PhanSo b) {
        if (b.tu == 0) {
            throw new ArithmeticException("Không thể chia cho phân số 0");
        }
        return new PhanSo(tu * b.mau, mau * b.tu);
    }

    public int soSanh(PhanSo b) {
        return Integer.compare(tu * b.mau, b.tu * mau);
    }

    public void rutGon() {
        chuanHoa();
    }

    public boolean laSoNguyen() {
        return mau == 1;
    }

    private void chuanHoa() {
        if (mau < 0) {
            tu = -tu;
            mau = -mau;
        }
        int u = ucln(tu, mau);
        tu /= u;
        mau /= u;
    }

    private static int ucln(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a == 0 ? 1 : a;
    }
}

public class Bai1 {

    public static void main(String[] args) {
        System.out.println("===== Phần 1: Kiểm thử lớp PhanSo =====");
        PhanSo p1 = new PhanSo(2, 4);
        PhanSo p2 = new PhanSo(-3, 6);
        PhanSo p3 = new PhanSo();
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        System.out.println("p3 = " + p3);
        System.out.println("p1 + p2 = " + p1.cong(p2));
        System.out.println("p1 - p2 = " + p1.tru(p2));
        System.out.println("p1 * p2 = " + p1.nhan(p2));
        System.out.println("p1 / p2 = " + p1.chia(p2));
        System.out.println("So sánh p1 với p2: " + p1.soSanh(p2));

        System.out.println("\n===== Phần 2: Xử lý tập phân số =====");
        List<PhanSo> ds = new ArrayList<>();
        ds.add(new PhanSo(1, 2));
        ds.add(new PhanSo(3, 1));
        ds.add(new PhanSo(-5, 4));
        ds.add(new PhanSo(7, 7));
        ds.add(new PhanSo(2, 3));
        ds.add(new PhanSo(10, 5));

        System.out.println("Danh sách: " + ds);
        System.out.println("Phân số lớn nhất: " + timPhanSoLonNhat(ds));
        System.out.println("Tổng các phân số: " + tinhTong(ds));
        System.out.println("Số phân số không phải số nguyên: " + demKhongPhaiSoNguyen(ds));
    }

    static PhanSo timPhanSoLonNhat(List<PhanSo> ds) {
        if (ds.isEmpty()) {
            throw new IllegalArgumentException("Danh sách rỗng");
        }
        PhanSo max = ds.get(0);
        for (PhanSo p : ds) {
            if (p.soSanh(max) > 0) {
                max = p;
            }
        }
        return max;
    }

    static PhanSo tinhTong(List<PhanSo> ds) {
        PhanSo tong = new PhanSo(0, 1);
        for (PhanSo p : ds) {
            tong = tong.cong(p);
        }
        return tong;
    }

    static int demKhongPhaiSoNguyen(List<PhanSo> ds) {
        int dem = 0;
        for (PhanSo p : ds) {
            if (!p.laSoNguyen()) {
                dem++;
            }
        }
        return dem;
    }
}