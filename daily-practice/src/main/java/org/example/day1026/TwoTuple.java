package org.example.day1026;

/**
 * 二元组
 */
public class TwoTuple<A, B> {

    private A first;
    private B second;


    public TwoTuple(A first, B second) {
        this.first = first;
        this.second = second;
    }


    public TwoTuple() {
    }


    public static void main(String[] args) {
        TwoTuple<Integer, String> twoTuple = new TwoTuple<>(1, "1");


        Integer first = twoTuple.first;
        String second = twoTuple.second;




    }



}
