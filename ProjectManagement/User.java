package ProjectManagement;

public class User implements Comparable<User> {
    String name;

    public User(String s){
        name = s;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(User user) {
        return name.compareTo(user.toString());
    }
    @Override
    public String toString (){
        return name;
    }
}
