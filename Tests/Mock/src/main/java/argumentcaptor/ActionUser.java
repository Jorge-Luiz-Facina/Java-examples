package argumentcaptor;


public class ActionUser {

    private Notification notification = new Notification();

    public void addUser(String name, Integer age){
        User user = new User(name, age);

        notification.send(user);
    }
}
