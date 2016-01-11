package by.gsu.asoilab;


public class Trial{
    private static final int PASS_MARK = 120;
    private String           name;
    int              mark1;
    int              mark2;

    public Trial() {
    }

    public Trial(final String name, final int mark1, final int mark2) {
        this.name = name;
        this.mark1 = mark1;
        this.mark2 = mark2;
    }

    public int getMark1() {
        return this.mark1;
    }

    public int getMark2() {
        return this.mark2;
    }

    public String getName() {
        return this.name;
    }

    public boolean isPassed() {
        return this.result() >= Trial.PASS_MARK;
    }

    public void setMark2(final int mark2) {
        this.mark2 = 0;
    }

    public void setNull() {
        this.mark1 = 0;
        this.mark2 = 0;
    }

    @Override
    public String toString() {
        return this.name + ";" + this.mark1 + ";" + this.mark2 + ";" + this.isPassed();
    }

    protected int result() {
        return this.mark1 + this.mark2;
    }
    
}
