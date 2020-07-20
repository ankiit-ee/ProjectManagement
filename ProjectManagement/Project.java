package ProjectManagement;


public class Project {
    String name;
    int priority;
    int budget;
    public Project(String name, String priority, String budget){
        this.name = name;
        this.priority = Integer.parseInt(priority);
        this.budget = Integer.parseInt(budget);
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    public int getBudget() {
        return budget;
    }
}
