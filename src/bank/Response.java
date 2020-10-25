package bank;

import java.io.Serializable;

public class Response implements Serializable {
    private boolean state;
    private String msg;

    public Response(boolean state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Response{" +
                "state=" + state +
                ", msg='" + msg + '\'' +
                '}';
    }
}
