/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ed2.joseherrera.lab2_ed2.codifications;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author godin
 */
public class rsa {

    private ArrayList<String> keys;

    public ArrayList<String> getKeys() {
        return keys;
    }

    public void setKeys(ArrayList<String> keys) {
        this.keys = keys;
    }

    public rsa(BigInteger p, BigInteger q) {
        keys = generatekeys(p, q);
    }

    public ArrayList<String> generatekeys(BigInteger p, BigInteger q) {
        keys = new ArrayList<>();
        BigInteger n = p.multiply(q);
        BigInteger fi = (p.subtract(new BigInteger("1"))).multiply(q.subtract(new BigInteger("1")));
        BigInteger e = returnrandom(fi);
        BigInteger d = e.modInverse(fi);
        keys.add(n.toString() + "," + d.toString());
        keys.add(n.toString() + "," + e.toString());

        return keys;

    }

    public String Cipher(BigInteger number) {

      

        String[] publickeys = keys.get(0).split(",");
        BigInteger n = new BigInteger(publickeys[0]);
        BigInteger e = new BigInteger(publickeys[1]);
        return number.modPow(e, n).toString();
    }

    public String desCipher(BigInteger ciphernumber) {

       

        String[] privatekeys = keys.get(1).split(",");
        BigInteger n = new BigInteger(privatekeys[0]);
        BigInteger d = new BigInteger(privatekeys[1]);
        return ciphernumber.modPow(d, n).toString();
    }

    public BigInteger returnrandom(BigInteger fi) {

        BigInteger min = new BigInteger("1");
        BigInteger limits = fi.subtract(min);
        int maxNumBitLength = limits.bitLength();
        Random r = new Random();

        BigInteger e = new BigInteger(maxNumBitLength, r);
        while (!e.gcd(fi).equals(min)) {
            e = new BigInteger(maxNumBitLength, r);
            if (e.compareTo(min) < 0) {
                e = e.add(min);
            }
            if (e.compareTo(fi) >= 0) {
                e = e.mod(fi).add(min);
            }

        }

        return e;

    }

}
