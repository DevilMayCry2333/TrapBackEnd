package cn.huihongcloud.entity.common;

/**
 * Created by 钟晖宏 on 2018/7/14
 */
public class Result {

    private String message;
    private Object data;
    private Boolean error;

    private Result(String message, Object data, Boolean error) {
        this.message = message;
        this.data = data;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public static class PermissionDenied extends Result{

        public PermissionDenied() {
            super("权限不足", null, true);
        }

    }

    public static class Ok extends Result {

        public Ok(String message, Object data) {
            super(message, data, false);
        }

        public Ok() {
            super("成功", null, false);
        }

    }

    public static class Failed extends Result {

        public Failed(String message, Object data) {
            super(message, data, true);
        }

        public Failed() {
            super("失败", null, true);
        }

    }

    public static Ok ok() {
        return new Ok();
    }

    public static Ok ok(String message, Object data) {
        return new Ok(message, data);
    }

    public static Ok ok(Object data) {
        return new Ok("成功", data);
    }

    public static Failed failed() {
        return new Failed();
    }

    public static Failed failed(String msg) {
        return new Failed(msg, null);
    }

}
