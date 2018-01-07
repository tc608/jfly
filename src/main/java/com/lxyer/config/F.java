package com.lxyer.config;

/**
 * Created by JUECHENG at 2018/1/7 14:35.
 */
public class F {
    public enum DynamicAttr {
        //table,id_k,gk, cate
        NEWS("dyna_attr", "contentId", "tid", 1),
        USER("dyna_attr", "userId", "tid", 2)
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
