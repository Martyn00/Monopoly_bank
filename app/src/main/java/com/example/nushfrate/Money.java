package com.example.nushfrate;

public class Money {
    private long sum;
    public Money(long x){
        this.sum = x;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }
    public long getSum(){
        return this.sum;
    }

    public void scadeBani(long s){
        if(getSum() > s){
            setSum(getSum() - s);
        }else{
            setSum(-1);

        }
    }
    public String toString(){
        return String.valueOf(sum);
    }

    public void cresteBani(long s){
        setSum(getSum() + s);
    }

    public boolean checkSum(){
        return false;
    }
}
