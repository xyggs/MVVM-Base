package com.android.base.bean;

/**
 * 定义事件Event：
 * 通过泛型<T>指定事件通信过程中的数据类型，code为事件码，使用的时候给不同的事件类型指定不同的code
 * Created by android on 2019/7/5
 */
public class Event<T> {
    private int code;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
