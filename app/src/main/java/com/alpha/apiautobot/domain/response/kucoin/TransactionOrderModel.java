package com.alpha.apiautobot.domain.response.kucoin;

import java.util.List;

public class TransactionOrderModel extends BaseModel {
    Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        private String _comment;
        private List<double[]> SELL ;
        private List<double[]> BUY ;

        public String get_comment() {
            return _comment;
        }

        public void set_comment(String _comment) {
            this._comment = _comment;
        }

        public List<double[]> getSELL() {
            return SELL;
        }

        public void setSELL(List<double[]> SELL) {
            this.SELL = SELL;
        }

        public List<double[]> getBUY() {
            return BUY;
        }

        public void setBUY(List<double[]> BUY) {
            this.BUY = BUY;
        }
    }

}
