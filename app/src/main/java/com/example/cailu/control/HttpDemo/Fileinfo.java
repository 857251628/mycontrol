package com.example.cailu.control.HttpDemo;

import java.io.Serializable;

/**
 * Created by cailu on 2016/7/28.
 *
 * 类实现了Serializable接口就可以使用Activity的intent 进行此对象的传递。
 */
public class Fileinfo implements Serializable {
    /*
    * 文件的实体类
    *
    * 一般需要包含以下几个属性。
    * id 编号
    * url 下载地址
    * filename 文件的名称
    * length 文件的长度
    * finished 文件下载时的进度
    * */
    private int id;
    private String url;
    private String Filename;
    private int length;
    private int finished;

    public Fileinfo() {
    }

    public Fileinfo(int id, String url, String filename, int length, int finished) {
        this.id = id;
        this.url = url;
        Filename = filename;
        this.length = length;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilename() {
        return Filename;
    }

    public void setFilename(String filename) {
        Filename = filename;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "Fileinfo{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", Filename='" + Filename + '\'' +
                ", length=" + length +
                ", finished=" + finished +
                '}';
    }
}
