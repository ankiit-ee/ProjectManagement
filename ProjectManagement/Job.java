package ProjectManagement;

public class Job implements Comparable<Job> {
    String name;
    String project;
    String user;
    String Status;
    int priority;
    int endtime;
    boolean complete;
    int runtime;
    int timestamp;
    public Job(String name, String project, String user, String runtime, int priority){
        this.name = name;
        this.project = project;
        this.user = user;
        this.runtime = Integer.parseInt(runtime);
        this.complete=false;
        this.priority=priority;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public int getRuntime(){
        return runtime;
    }


    public boolean equals(Job j){
        if(this.priority==j.priority) return  true;
        else return false;
    }

    public void setEndtime(int endtime) {
        this.endtime = endtime;
    }

    public int getEndtime() {
        return endtime;
    }

    public String getProject(){
        return project;
    }

    public void setComplete() {
        this.complete = true;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        if(isComplete()) return "COMPLETED";
        else return "REQUESTED";
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public boolean equals(Object obj) {
        if(this.compareTo((Job) obj)==0) return true;
        return false;
    }

    @Override
    public int compareTo(Job job) {
        return this.priority-job.getPriority();
    }
    @Override
    public String toString(){
        if(this.endtime!=0)return "Job{user=" + "'" + this.user +"'" + ", " + "project='" + this.project + "'," + " jobstatus=" + this.getStatus() + ", execution_time=" + this.runtime + ", end_time=" + this.endtime + ", name='" + this.name + "'}";
        return "Job{user=" + "'" + this.user +"'" + ", " + "project='" + this.project + "'," + " jobstatus=" + this.getStatus() + ", execution_time=" + this.runtime + ", end_time=" + "null" + ", name='" + this.name + "'}";
    }

}