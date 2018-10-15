package com.ed2.joseherrera.lab2_ed2.codifications;

import org.apache.commons.lang.ArrayUtils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class sdes {
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
    public String permutate10(String key) {
        char[] charcatersarray = (key.toCharArray());
        String strings ="";
        ArrayList<Character> newcharacters = new ArrayList<>(10);
        newcharacters.add(0,charcatersarray[4]);
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
            strings+=newcharacter;
        }

        return strings;
    }

    public String initialpermutation(String key) {
        char[] charcatersarray = (key.toCharArray());
        String strings ="";
        ArrayList<Character> newcharacters = new ArrayList<>(8);
        newcharacters.add(0,charcatersarray[7]);
        newcharacters.add(1, charcatersarray[5]);
        newcharacters.add(2, charcatersarray[4]);
        newcharacters.add(3, charcatersarray[0]);
        newcharacters.add(4, charcatersarray[3]);
        newcharacters.add(5, charcatersarray[1]);
        newcharacters.add(6, charcatersarray[6]);
        newcharacters.add(7, charcatersarray[2]);


        for (Character newcharacter : newcharacters) {
            strings+=newcharacter;
        }

        return strings;
    }

    public String invertinitialpermutation(String key) {
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
    public String permutate8(String key){
        String strings = "";
        Character[] characterArray = ArrayUtils.toObject(key.toCharArray());
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
        Character[] charactersArray = ArrayUtils.toObject(key.toCharArray());
        ArrayList<Character> newCharacters = new ArrayList<>(4);
        newCharacters.add(charactersArray[4]);
        newCharacters.add(charactersArray[3]);
        newCharacters.add(charactersArray[1]);
        newCharacters.add(charactersArray[2]);
        for (Character character : newCharacters
                ) {
            strings += character;
        }

        return strings;
    }
}
