public class Fregues {

    private int id;

    public Fregues(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString(){
        return "F" + id;
    }

}
