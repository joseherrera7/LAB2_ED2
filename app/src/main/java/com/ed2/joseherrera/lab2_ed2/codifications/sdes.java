package com.ed2.joseherrera.lab2_ed2.codifications;

import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang.*;

public class sdes {

    String[][] sbox0 = new String[4][4];
    String[][] sbox1 = new String[4][4];

    public String desCipherSdes(String text, String key){

        Character[] charcatersarray = ArrayUtils.toObject(text.toCharArray());
        ArrayList<Character> characters = new ArrayList<>(Arrays.asList(charcatersarray));
        ArrayList<String> keys = generateKey(key);
        String out = "";

        for (char character: characters
                ) {
            int num = (int)character;
            String binary = obtainBinary(num);
            String IP = initialPermutation(binary);
            String Fk = IP.substring(0,4);
            String Fp = IP.substring(4, 8);
            String EP = expandAndPermute(Fp);
            String returnS0 = s0(xor(EP, keys.get(1)).substring(0,4));
            String returnS1 = s1(xor(EP, keys.get(1)).substring(4,8));
            String P4 = permutate4(returnS0+returnS1);



            String switch1= xor(Fk, P4);
            String switch2=Fp;

            Fk=switch2;
            Fp=switch1;

            EP = expandAndPermute(Fp);
            returnS0 = s0(xor(EP, keys.get(0)).substring(0,4));
            returnS1 = s1(xor(EP, keys.get(0)).substring(4,8));
            P4 = permutate4(returnS0+returnS1);
            String keyIPInverse = xor(Fk, P4)+Fp;
            out += (char)calculateDecimal(Integer.valueOf(invertInitialPermutation(keyIPInverse)));
        }
        return out;
    }

    public String CipherSdes(String text, String key){

        Character[] charcatersarray = ArrayUtils.toObject(text.toCharArray());
        ArrayList<Character> characters = new ArrayList<>(Arrays.asList(charcatersarray));
        ArrayList<String> keys = generateKey(key);
        String out = "";

        for (char character: characters
                ) {
            int num = (int)character;
            String binary = obtainBinary(num);
            String IP = initialPermutation(binary);
            String Fk = IP.substring(0,4);
            String Fp = IP.substring(4, 8);
            String EP = expandAndPermute(Fp);
            String returnS0 = s0(xor(EP, keys.get(0)).substring(0,4));
            String returnS1 = s1(xor(EP, keys.get(0)).substring(4,8));
            String P4 = permutate4(returnS0+returnS1);



            String switch1= xor(Fk, P4);
            String switch2=Fp;

            Fk=switch2;
            Fp=switch1;

            EP = expandAndPermute(Fp);
            returnS0 = s0(xor(EP, keys.get(1)).substring(0,4));
            returnS1 = s1(xor(EP, keys.get(1)).substring(4,8));
            P4 = permutate4(returnS0+returnS1);
            String keyIPInverse = xor(Fk, P4)+Fp;
            out += (char)calculateDecimal(Integer.valueOf(invertInitialPermutation(keyIPInverse)));
        }
        return out;
    }
    public sdes() {
        sbox0[0][0] = "01";
        sbox0[0][1] = "00";
        sbox0[0][2] = "11";
        sbox0[0][3] = "10";

        sbox0[1][0] = "11";
        sbox0[1][1] = "10";
        sbox0[1][2] = "01";
        sbox0[1][3] = "00";

        sbox0[2][0] = "00";
        sbox0[2][1] = "10";
        sbox0[2][2] = "01";
        sbox0[2][3] = "11";

        sbox0[3][0] = "11";
        sbox0[3][1] = "01";
        sbox0[3][2] = "11";
        sbox0[3][3] = "10";

        sbox1[0][0] = "00";
        sbox1[0][1] = "01";
        sbox1[0][2] = "10";
        sbox1[0][3] = "11";

        sbox1[1][0] = "10";
        sbox1[1][1] = "00";
        sbox1[1][2] = "01";
        sbox1[1][3] = "11";

        sbox1[2][0] = "11";
        sbox1[2][1] = "00";
        sbox1[2][2] = "01";
        sbox1[2][3] = "00";

        sbox1[3][0] = "10";
        sbox1[3][1] = "01";
        sbox1[3][2] = "00";
        sbox1[3][3] = "11";

    }

    public String Leftshift1(String binary) {
        String stringwithshift = "";
        char[] string;
        char temp;
        string = binary.toCharArray();
        temp = binary.charAt(0);

        string[0] = string[1];
        string[1] = string[2];
        string[2] = string[3];
        string[3] = string[4];
        string[4] = temp;
        stringwithshift += string[0];
        stringwithshift += string[1];
        stringwithshift += string[2];
        stringwithshift += string[3];
        stringwithshift += string[4];

        return stringwithshift;

    }

    public String Leftshift2(String binary) {
        String stringwithshift = "";
        char[] string;
        char temp1;
        char temp2;
        string = binary.toCharArray();

        temp1 = binary.charAt(0);
        temp2 = binary.charAt(1);

        string[0] = string[2];
        string[1] = string[3];
        string[2] = string[4];
        string[3] = temp1;
        string[4] = temp2;

        stringwithshift += string[0];
        stringwithshift += string[1];
        stringwithshift += string[2];
        stringwithshift += string[3];
        stringwithshift += string[4];

        return stringwithshift;

    }

    public String permutate8(String key){
        String strings = "";
        char[] characterArray = (key.toCharArray());
        ArrayList<Character> newCharacters = new ArrayList<>(10);
        newCharacters.add(characterArray[4]);
        newCharacters.add(characterArray[5]);
        newCharacters.add(characterArray[1]);
        newCharacters.add(characterArray[2]);
        newCharacters.add(characterArray[7]);
        newCharacters.add(characterArray[3]);
        newCharacters.add(characterArray[6]);
        newCharacters.add(characterArray[0]);

        for (Character character:  newCharacters
                ) {
            strings+=character;
        }

        return strings;
    }

    public String permutate4(String key) {
        String strings = "";
        char[] charactersArray = (key.toCharArray());
        ArrayList<Character> newCharacters = new ArrayList<>(4);
        newCharacters.add(charactersArray[0]);
        newCharacters.add(charactersArray[3]);
        newCharacters.add(charactersArray[1]);
        newCharacters.add(charactersArray[2]);
        for (Character character : newCharacters
                ) {
            strings += character;
        }

        return strings;
    }

    public String permutate10(String key) {
        char[] charcatersarray = (key.toCharArray());
        String strings = "";
        ArrayList<Character> newcharacters = new ArrayList<>(10);
        newcharacters.add(0, charcatersarray[4]);
        newcharacters.add(1, charcatersarray[7]);
        newcharacters.add(2, charcatersarray[1]);
        newcharacters.add(3, charcatersarray[2]);
        newcharacters.add(4, charcatersarray[9]);
        newcharacters.add(5, charcatersarray[3]);
        newcharacters.add(6, charcatersarray[8]);
        newcharacters.add(7, charcatersarray[0]);
        newcharacters.add(8, charcatersarray[6]);
        newcharacters.add(9, charcatersarray[5]);

        for (Character newcharacter : newcharacters) {
            strings += newcharacter;
        }

        return strings;
    }

    public String xor(String string1, String string2) {

        char[] charcatersarray1 = (string1.toCharArray());
        char[] charcatersarray2 = (string2.toCharArray());
        String strings = "";
        ArrayList<Character> newcharacters = new ArrayList<>(10);

        int cont = 0;
        for (Character character : charcatersarray1) {
            if (character.equals(charcatersarray2[cont])) {
                strings += '0';
            } else {
                strings += '1';
            }
            cont++;
        }

        return strings;
    }

    private static int calculateDecimal(int binario){
        int decimal = 0, exponente = 0;
        int digito;
        while(binario>0){
            digito = binario%10;
            decimal = decimal + digito * (int)Math.pow(2, exponente);
            binario /= 10;
            exponente++;
        }
        return decimal;
    }

    public String s0(String binario) {
        String filas=String.valueOf(binario.charAt(0))+String.valueOf(binario.charAt(3));
        String rows=String.valueOf(binario.charAt(1))+String.valueOf(binario.charAt(2));

        return sbox0[calculateDecimal(Integer.valueOf(filas))][calculateDecimal(Integer.valueOf(rows))];

    }

    public String s1(String binario) {
        String filas=String.valueOf(binario.charAt(0))+String.valueOf(binario.charAt(3));
        String rows=String.valueOf(binario.charAt(1))+String.valueOf(binario.charAt(2));

        return sbox1[calculateDecimal(Integer.valueOf(filas))][calculateDecimal(Integer.valueOf(rows))];

    }

    public ArrayList<String> generateKey(String key){

        ArrayList<String> keys = new ArrayList<>();
        String text = permutate10(key);

        String text0 = Leftshift1(text.substring(0,5));
        String text1 = Leftshift1(text.substring(5, 10));

        keys.add(permutate8(text0+text1.substring(0,3)));

        String text2 = Leftshift2(text0);
        String text3 = Leftshift2(text1);

        keys.add(permutate8(text2+text3.substring(0,3)));
        return keys;
    }




    public String expandAndPermute(String binary) {

        char[] charcatersarray = (binary.toCharArray());
        String strings = "";
        ArrayList<Character> newcharacters = new ArrayList<>(8);
        newcharacters.add(0, charcatersarray[3]);
        newcharacters.add(1, charcatersarray[0]);
        newcharacters.add(2, charcatersarray[0]);
        newcharacters.add(3, charcatersarray[1]);
        newcharacters.add(4, charcatersarray[3]);
        newcharacters.add(5, charcatersarray[2]);
        newcharacters.add(6, charcatersarray[1]);
        newcharacters.add(7, charcatersarray[2]);

        for (Character newcharacter : newcharacters) {
            strings += newcharacter;
        }

        return strings;
    }

    public String invertInitialPermutation(String key) {
        char[] charcatersarray = (key.toCharArray());
        String strings = "";
        ArrayList<Character> newcharacters = new ArrayList<>(8);
        newcharacters.add(0, charcatersarray[3]);
        newcharacters.add(1, charcatersarray[5]);
        newcharacters.add(2, charcatersarray[7]);
        newcharacters.add(3, charcatersarray[4]);
        newcharacters.add(4, charcatersarray[2]);
        newcharacters.add(5, charcatersarray[1]);
        newcharacters.add(6, charcatersarray[6]);
        newcharacters.add(7, charcatersarray[0]);

        for (Character newcharacter : newcharacters) {
            strings += newcharacter;
        }

        return strings;
    }
    public static String obtainBinary(int number){
        ArrayList<String> binary = new ArrayList<>();
        int rest;
        String binaryString = "";

        while(number > 0){
            rest = number%2;
            number = number/2;
            binary.add(0, Integer.toString(rest));
        }
        if (binary.size()!=8){
            binary.add(0,Integer.toString(number));
        }




        for(int i = 0; i < 8- binary.size(); i++){
            binaryString += 0;
        }

        for(int i = 0; i < binary.size(); i++){
            binaryString += binary.get(i);
        }



        return binaryString;
    }
    public String initialPermutation(String binary){
        char[] charcatersarray = (binary.toCharArray());
        String strings = "";
        ArrayList<Character> newcharacters = new ArrayList<>(8);
        newcharacters.add(0, charcatersarray[7]);
        newcharacters.add(1, charcatersarray[5]);
        newcharacters.add(2, charcatersarray[4]);
        newcharacters.add(3, charcatersarray[0]);
        newcharacters.add(4, charcatersarray[3]);
        newcharacters.add(5, charcatersarray[1]);
        newcharacters.add(6, charcatersarray[6]);
        newcharacters.add(7, charcatersarray[2]);

        for (Character newcharacter : newcharacters) {
            strings += newcharacter;
        }

        return strings;
    }
}