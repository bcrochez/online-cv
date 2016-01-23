/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.upem.onlinecv.bean;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 *
 * @author Bastien
 */
public class DateBean {

    /**
     * Creates a new instance of DateBean
     */
    public DateBean() {
    }
    
    public String now() {
        return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }
}
