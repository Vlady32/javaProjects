package by.gsu.asoilab;

public class ComparatorMark2 implements Sorter{
	public int compare(Trial trial1, Trial trial2){
    	return trial1.mark2 - trial2.mark2;
    }
}
