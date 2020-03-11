import Exceptions.failedCheckException;

/**
 * Класс - поле класса Route
 */

public class Location {
    private Long x; //Поле не может быть null
    private Long y; //Поле не может быть null
    private long z; //????
    private String name; //Длина строки не должна быть больше 867, Поле может быть null

    public Location(Long x, Long y, long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Long getX() {
        return x;
    }
    public static Checker<Long> XYZCheck = (Long L) -> {if (L!=null) return L; else throw new failedCheckException();};
    public static Checker<String> nameCheck = (String S) -> {if(S == null) return null; else if (S.length() <= 867) return S; throw new failedCheckException();};

    public Long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }
}
