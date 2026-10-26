import java.util.ArrayList;
import java.util.List;

class Point2D {
    private double x;
    private double y;

    public Point2D() {
        this(0, 0);
    }

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double khoangCach(Point2D other) {
        return Math.hypot(this.x - other.x, this.y - other.y);
    }

    public Point2D doiXung() {
        return new Point2D(-x, -y);
    }

    public void tinhTien(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public String toString() {
        return "Point2D [x=" + x + ", y=" + y + "]";
    }
}

class Point3D extends Point2D {
    private double z;

    public Point3D() {
        this(0, 0, 0);
    }

    public Point3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public double khoangCach(Point2D other) {
        double zOther = (other instanceof Point3D) ? ((Point3D) other).z : 0;
        double dz = this.z - zOther;
        double dxy = super.khoangCach(other);
        return Math.sqrt(dxy * dxy + dz * dz);
    }

    @Override
    public Point3D doiXung() {
        return new Point3D(-getX(), -getY(), -z);
    }

    public void tinhTien(double dx, double dy, double dz) {
        tinhTien(dx, dy);
        this.z += dz;
    }

    @Override
    public String toString() {
        return "Point3D [x=" + getX() + ", y=" + getY() + ", z=" + z + "]";
    }
}

public class Bai2 {

    public static void main(String[] args) {
        List<Point2D> ds = new ArrayList<>();
        ds.add(new Point2D(1.0, 5.0));
        ds.add(new Point2D(3.0, 7.0));
        ds.add(new Point3D(1.0, 5.0, 3.5));
        ds.add(new Point3D(3.0, 7.0, 2.0));

        double tong2D = 0;
        double tong3D = 0;
        for (Point2D p : ds) {
            if (p instanceof Point3D) {
                tong3D += p.khoangCach(new Point3D());
            } else {
                tong2D += p.khoangCach(new Point2D());
            }
        }
        System.out.println("Tổng khoảng cách các điểm 2D đến gốc: " + tong2D);
        System.out.println("Tổng khoảng cách các điểm 3D đến gốc: " + tong3D);

        List<Point2D> doiXung = new ArrayList<>();
        for (Point2D p : ds) {
            doiXung.add(p.doiXung());
        }
        ds.addAll(doiXung);

        System.out.println("\nDanh sách điểm (gốc + đối xứng):");
        for (Point2D p : ds) {
            System.out.println(p);
        }

        Point2D a = new Point2D(0, 0);
        a.tinhTien(2, -1);
        Point3D b = new Point3D(1, 1, 1);
        b.tinhTien(1, 2, 3);
        System.out.println("\nSau tịnh tiến: " + a + " ; " + b);
    }
}