package com.fullnews.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

public class BookHomeBeans {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private PhBean ph;

        public PhBean getPh() {
            return ph;
        }

        public void setPh(PhBean ph) {
            this.ph = ph;
        }

        public static class PhBean {
            /**
             * id : 2494145
             * bookname : 近身特工
             * author : 16350
             * book_info : 重生了，却发现重生在一个窝囊废身上，一不小心还成为了与恶魔同行与死神共舞的特工，任务任君选择，刺探情报、追捕嫌犯、扑灭犯罪、保护美女……严小开有些纠结：我是选择保护美女？保护美女？还是保护美女？
             * book_cover : 201603241840578437.jpg
             * size : 4891385
             * num_click : 9915364
             * classname : 重生
             */

            private List<BookListBean> book_list;

            public List<BookListBean> getBook_list() {
                return book_list;
            }

            public void setBook_list(List<BookListBean> book_list) {
                this.book_list = book_list;
            }

            public static class BookListBean {
                private String id;
                private String bookname;
                private String author;
                private String book_info;
                private String book_cover;
                private String size;
                private String num_click;
                private String classname;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getBookname() {
                    return bookname;
                }

                public void setBookname(String bookname) {
                    this.bookname = bookname;
                }

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public String getBook_info() {
                    return book_info;
                }

                public void setBook_info(String book_info) {
                    this.book_info = book_info;
                }

                public String getBook_cover() {
                    return book_cover;
                }

                public void setBook_cover(String book_cover) {
                    this.book_cover = book_cover;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }

                public String getNum_click() {
                    return num_click;
                }

                public void setNum_click(String num_click) {
                    this.num_click = num_click;
                }

                public String getClassname() {
                    return classname;
                }

                public void setClassname(String classname) {
                    this.classname = classname;
                }
            }
        }
    }
}
