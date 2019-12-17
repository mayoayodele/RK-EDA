/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Comparator;
import representation.rk;

/**
 *
 * @author 1013288
 */
public class RKCompare implements Comparator<rk> {

	    @Override
	    public int compare(rk rk1, rk rk2) {
	        return (int) (rk1.fitness - rk2.fitness);
//                return (int) (rk1.sFitness - rk2.sFitness);
	    }
	    
	  
	  
	}