/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;

/**
 *
 * @author prasanth
 */
public class TestArraylist {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>(1);
        strings.add("string1");
        strings.add("string2");
        strings.add("string3");
        
        strings.add("string4");
        System.out.println("" + strings);
    }
    
}
