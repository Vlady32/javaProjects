package by.gsu.asoilab;

public class ComparatorMark1 implements Sorter{
	public int compare(Trial trial1, Trial trial2){
    	return trial1.mark1 - trial2.mark1;
    }
}	
