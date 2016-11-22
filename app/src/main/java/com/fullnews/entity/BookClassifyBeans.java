package com.fullnews.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/19 0019.
 */

public class BookClassifyBeans {

    /**
     * name : 玄幻
     * bookCount : 271312
     */

    private List<MaleBean> male;
    /**
     * name : 古代言情
     * bookCount : 194419
     */

    private List<FemaleBean> female;
    /**
     * name : 传记名著
     * bookCount : 884
     */

    private List<PressBean> press;

    public List<MaleBean> getMale() {
        return male;
    }

    public void setMale(List<MaleBean> male) {
        this.male = male;
    }

    public List<FemaleBean> getFemale() {
        return female;
    }

    public void setFemale(List<FemaleBean> female) {
        this.female = female;
    }

    public List<PressBean> getPress() {
        return press;
    }

    public void setPress(List<PressBean> press) {
        this.press = press;
    }

    public static class MaleBean {
        private String name;
        private int bookCount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBookCount() {
            return bookCount;
        }

        public void setBookCount(int bookCount) {
            this.bookCount = bookCount;
        }
    }

    public static class FemaleBean {
        private String name;
        private int bookCount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBookCount() {
            return bookCount;
        }

        public void setBookCount(int bookCount) {
            this.bookCount = bookCount;
        }
    }

    public static class PressBean {
        private String name;
        private int bookCount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBookCount() {
            return bookCount;
        }

        public void setBookCount(int bookCount) {
            this.bookCount = bookCount;
        }
    }
}
