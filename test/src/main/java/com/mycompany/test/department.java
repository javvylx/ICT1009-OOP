/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.test;
import java.util.*;
/**
 *
 * @author chinb
 */
public class department 
{
    private long id;
    private String name;
    private String[] users;
 
    //Getters and Setters
 
    @Override
    public String toString() {
        return "Department [id=" + id + ", name=" + name + ", users=" + Arrays.toString(users) + "]";
    }
}
