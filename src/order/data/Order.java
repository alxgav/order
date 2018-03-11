package order.data;

import order.common.CustomDate;

import java.util.Date;

public class Order {



    private Date order_date;

    private String bayer;


    private String description;


    private Integer st;


    private Integer price;


    private Integer priceTotall;


    private Double priceKPI;


    private Integer priceRepear;


    private Integer priceAll;


    private String noteCell;


    private Double priceMain;


    private Double priceMarg;

    public Order(){

    }


    public Order(Date order_date) {
        this.order_date = order_date;
    }

    public Order(Date order_date, String bayer, String description) {
        this.order_date = order_date;
        this.bayer = bayer;
        this.description = description;
    }

    public Order(Date order_date, String bayer, String description, Integer st, Integer price, Integer priceTotall, Double priceKPI, Integer priceRepear, Integer priceAll, String noteCell, Double priceMain, Double priceMarg) {
        this.order_date = order_date;
        this.bayer = bayer;
        this.description = description;
        this.st = st;
        this.price = price;
        this.priceTotall = priceTotall;
        this.priceKPI = priceKPI;
        this.priceRepear = priceRepear;
        this.priceAll = priceAll;
        this.noteCell = noteCell;
        this.priceMain = priceMain;
        this.priceMarg = priceMarg;
    }

    public Date getOrder_date() {
        return new CustomDate(order_date.getTime());
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getBayer() {
        return bayer;
    }

    public void setBayer(String bayer) {
        this.bayer = bayer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSt() {
        return st;
    }

    public void setSt(Integer st) {
        this.st = st;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPriceTotall() {
        return priceTotall;
    }

    public void setPriceTotall(Integer priceTotall) {
        this.priceTotall = priceTotall;
    }

    public Double getPriceKPI() {
        return priceKPI;
    }

    public void setPriceKPI(Double priceKPI) {
        this.priceKPI = priceKPI;
    }

    public Integer getPriceRepear() {
        return priceRepear;
    }

    public void setPriceRepear(Integer priceRepear) {
        this.priceRepear = priceRepear;
    }

    public Integer getPriceAll() {
        return priceAll;
    }

    public void setPriceAll(Integer priceAll) {
        this.priceAll = priceAll;
    }

    public String getNoteCell() {
        return noteCell;
    }

    public void setNoteCell(String noteCell) {
        this.noteCell = noteCell;
    }

    public Double getPriceMain() {
        return priceMain;
    }

    public void setPriceMain(Double priceMain) {
        this.priceMain = priceMain;
    }

    public Double getPriceMarg() {
        return priceMarg;
    }

    public void setPriceMarg(Double priceMarg) {
        this.priceMarg = priceMarg;
    }
}
