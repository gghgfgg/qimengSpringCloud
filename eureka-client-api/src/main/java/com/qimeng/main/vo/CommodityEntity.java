package com.qimeng.main.vo;

public class CommodityEntity {
        /**
         * id : 2
         * mc : bb1
         * jf : 3
         * pic : bb
         */

        private int id;
        private String mc;
        private int jf;
        private String pic;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMc() {
            return mc;
        }

        public void setMc(String mc) {
            this.mc = mc;
        }

        public int getJf() {
            return jf;
        }

        public void setJf(int jf) {
            this.jf = jf;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        @Override
        public String toString() {
            return "id=" + id + " mc=" + id + " jf=" + jf + " pic=" + pic ;
        }
    }