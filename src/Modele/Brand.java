package Modele;

public class Brand {
    // 扑克牌的类型 ：
    private Type type;
    // 扑克牌的大小
    private Integer num;

    // 直接创建一个构造器用来 创建每一张扑克牌
    public Brand(Type type, Integer num) {
        super();
        this.type = type;
        this.num = num;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Brand [type=" + type + ", num=" + num + "]";
    }
}
