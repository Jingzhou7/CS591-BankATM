/**
 * Make String a class instead of a String
 * String can be printed out in different ways
 */


public class Name {
    private String fname, mname, lname;
    public Name(String name) {
        fname = name;
        lname = "";
        mname = "";
    }
    public Name(String fname, String mname, String lname ) {
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
    }
    public String toString() {
        //return( fname + " " + mname + ". " + lname);
    	return fname;
    }

    @Override
    public int hashCode() {
        return fname.hashCode();
    }

}
