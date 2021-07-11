import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("大家好:), <不良代码>，欢迎访问 4399.cm, 我们爱玩小游戏");
        //不加链条
        new HTMLFilter().doFilter(msg);
        new FaceFilter().doFilter(msg);
        System.out.println(msg);
        //加链条
        FilterChain fc = new FilterChain();
//        fc.add(new HTMLFilter());
//        fc.add(new FaceFilter());
        fc.add(new HTMLFilter()).add(new FaceFilter());
        fc.doFilter(msg);
        System.out.println(msg);


    }

}

class Msg {
    String msg;

    public Msg(String msg) {
        this.msg = msg;
    }

    public Msg() {

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}

interface Filter {
    void doFilter(Msg m);
}

class FaceFilter implements Filter {

    @Override
    public void doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace(":)", "----");
        m.setMsg(r);
    }
}

class HTMLFilter implements Filter {
    @Override
    public void doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace("<", "{");
        m.setMsg(r);
    }
}

class FilterChain {
    List<Filter> filters = new ArrayList<>();

    public FilterChain add(Filter filter) {
        filters.add(filter);
        return this;
    }

    public void doFilter(Msg msg) {
        for (Filter f : filters) {
            f.doFilter(msg);
        }
    }
}
