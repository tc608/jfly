package com.lxyer.config;

/**
 * Created by Lxyer at 2017/9/10 15:35.
 */
public class E {

    public enum DynamicAttr {
        //table,id_k,gk, cate
        NEWS("dyna_attr", "contentId", "tid", 1),
        USER("dyna_attr", "userId", "tid", 3)
        ;
        private String table;
        private String id_k;
        private String gk;
        private int cate;
        DynamicAttr(String table, String id_k, String gk, int cate) {
            this.table = table;
            this.id_k = id_k;
            this.gk = gk;
            this.cate = cate;
        }
        public String table(){
            return this.table;
        }
        public String id_k(){
            return this.id_k;
        }
        public String gk(){
            return this.gk;
        }
        public int getCate() {
            return cate;
        }
    }
}
