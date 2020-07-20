package ProjectManagement;


import PriorityQueue.MaxHeap;
import RedBlack.RBTree;
import Trie.Trie;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class Scheduler_Driver extends Thread implements SchedulerInterface {
     Trie projectTrie;
     MaxHeap jobHeap;
     int globalTime ;
     List<Job> list;
     List<User> userList;
     List<Job> failed;
     List<Job> total;
     RBTree<String, Job> Not_Used;
     int timeStamp;

     public Scheduler_Driver(){
         projectTrie = new Trie();
         jobHeap = new MaxHeap();
         globalTime =0;
         list = new ArrayList<>();
         userList = new ArrayList<>();
         failed = new ArrayList<>();
         Not_Used = new RBTree<>();
         total = new ArrayList<>();
         timeStamp=0;
     }



     public static void main(String[] args) throws IOException {
//

        Scheduler_Driver scheduler_driver = new Scheduler_Driver();
        File file;
        if (args.length == 0) {
            URL url = Scheduler_Driver.class.getResource("INP");
            file = new File(url.getPath());
        } else {
            file = new File(args[0]);
        }

        scheduler_driver.execute(file);
    }

    public void execute(File commandFile) throws IOException {


        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(commandFile));

            String st;
            while ((st = br.readLine()) != null) {
                String[] cmd = st.split(" ");
                if (cmd.length == 0) {
                    System.err.println("Error parsing: " + st);
                    return;
                }
                String project_name, user_name;
                Integer start_time, end_time;

                long qstart_time, qend_time;

                switch (cmd[0]) {
                    case "PROJECT":
                        handle_project(cmd);
                        break;
                    case "JOB":
                        handle_job(cmd);
                        break;
                    case "USER":
                        handle_user(cmd[1]);
                        break;
                    case "QUERY":
                        handle_query(cmd[1]);
                        break;
                    case "": // HANDLE EMPTY LINE
                        handle_empty_line();
                        break;
                    case "ADD":
                        handle_add(cmd);
                        break;
                    //--------- New Queries
                    case "NEW_PROJECT":
                    case "NEW_USER":
                    case "NEW_PROJECTUSER":
                    case "NEW_PRIORITY":
                        timed_report(cmd);
                        break;
                    case "NEW_TOP":
                        qstart_time = System.nanoTime();
                        timed_top_consumer(Integer.parseInt(cmd[1]));
                        qend_time = System.nanoTime();
                        System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                        break;
                    case "NEW_FLUSH":
                        qstart_time = System.nanoTime();
                        timed_flush( Integer.parseInt(cmd[1]));
                        qend_time = System.nanoTime();
                        System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                        break;
                    default:
                        System.err.println("Unknown command: " + cmd[0]);
                }

            }


            run_to_completion();
            print_stats();

        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. " + commandFile.getAbsolutePath());
        } catch (NullPointerException ne) {
            ne.printStackTrace();

        }
    }

    @Override
    public ArrayList<JobReport_> timed_report(String[] cmd) {
        long qstart_time, qend_time;
        ArrayList<JobReport_> res = null;
        switch (cmd[0]) {
            case "NEW_PROJECT":
                qstart_time = System.nanoTime();
                res = handle_new_project(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
            case "NEW_USER":
                qstart_time = System.nanoTime();
                res = handle_new_user(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));

                break;
            case "NEW_PROJECTUSER":
                qstart_time = System.nanoTime();
                res = handle_new_projectuser(cmd);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
            case "NEW_PRIORITY":
                qstart_time = System.nanoTime();
                res = handle_new_priority(cmd[1]);
                qend_time = System.nanoTime();
                System.out.println("Time elapsed (ns): " + (qend_time - qstart_time));
                break;
        }

        return res;
    }

    @Override
    public ArrayList<UserReport_> timed_top_consumer(int top) {
        return null;
    }



    @Override
    public void timed_flush(int waittime) {

    }
    

    private ArrayList<JobReport_> handle_new_priority(String s) {
        return null;
    }

    private ArrayList<JobReport_> handle_new_projectuser(String[] cmd) {
        return null;
    }

    private ArrayList<JobReport_> handle_new_user(String[] cmd) {
        return null;
    }

    private ArrayList<JobReport_> handle_new_project(String[] cmd) {
        return null;
    }




    public void schedule() {
            execute_a_job();
    }

    public void run_to_completion() {
        while (jobHeap.getSize()>0) {
            System.out.println("Running code");
            System.out.println("Remaining jobs: "+ jobHeap.getSize());
            schedule();
            System.out.println("System execution completed");
        }

    }

    public void print_stats() {
        System.out.println("--------------STATS---------------");
        System.out.println("Total jobs done: " + list.size());
        for(Job e : list){
            System.out.println(e.toString());
        }
        System.out.println("------------------------");
        System.out.println("Unfinished jobs:");
        for(Job e: failed){
            System.out.println(e.toString());
        }
        System.out.println("Total unfinished jobs: " + failed.size());
        System.out.println("--------------STATS DONE---------------");

    }

    public void handle_add(String[] cmd) {

    }

    public void handle_empty_line() {
       schedule();
    }


    public void handle_query(String key) {

        System.out.println("Querying");
        Job x = null;
        boolean flag1 = false;
        boolean flag2 = false;
        for(Job e: list){
            if(key.equals(e.getName())){
                x=e;
                flag1 = true;
                break;
            }
        }
        for(Job e: total){
            if(key.equals(e.getName())){
                x=e;
                flag2 = true;
                break;
            }
        }
        if(flag2) {

            if(!flag1)System.out.println(x.getName() + ": NOT FINISHED");
            if(flag1)System.out.println(x.getName() + ": COMPLETED");
        }
        else System.out.println(key + ": NO SUCH JOB");

    }

    public void handle_user(String name) {
        System.out.println("Creating user");
        User us = new User(name);
        userList.add(us);

    }

    public void handle_job(String[] cmd) {
        System.out.println("Creating job");
        boolean flag = false;
        for(int i =0; i< userList.size();i++){
            if(userList.get(i).getName().equals(cmd[3].trim())){
                flag = true;
                break;
            }
        }
        if(projectTrie.search(cmd[2].trim())!= null && flag){
            timeStamp++;
            Project pr = (Project) projectTrie.search(cmd[2].trim()).getValue();
            Job j = new Job(cmd[1].trim(),cmd[2].trim(), cmd[3].trim(),cmd[4].trim(),pr.getPriority());
            j.setTimestamp(timeStamp);
            jobHeap.insert(j);
            total.add(j);
        }
        else {
            if (!flag) System.out.println("No such user exists: " + cmd[3].trim());
            if(projectTrie.search(cmd[2].trim())== null) System.out.println("No such project exists. " + cmd[2].trim());
        }

    }

    public void handle_project(String[] cmd) {
        System.out.println("Creating project");
        Project pr = new Project(cmd[1].trim(),cmd[2].trim(),cmd[3].trim());
        projectTrie.insert(cmd[1].trim(),pr);

    }

    public void execute_a_job() {
        if(jobHeap.getSize()==0) return;
        while (jobHeap.getSize()!=0) {
            Job j = (Job) jobHeap.extractMax();
            Project p = (Project) projectTrie.search(j.getProject()).getValue();
            System.out.println("Executing: " + j.getName() + " from: " + j.getProject());
            if (p.getBudget() >= j.getRuntime()) {
                j.setComplete(); //// Set completed time to current global time???????????
                globalTime += j.getRuntime();
                j.setEndtime(globalTime);
                p.setBudget(p.getBudget() - j.getRuntime());
                String s = j.getName();
                list.add(j);
                Not_Used.insert(j.getProject(),j);
                System.out.println("Project: " + p.getName() + " budget remaining: " + p.getBudget());
                break;
            } else {
                failed.add(j);

                System.out.println("Un-sufficient budget.");
            }
        }
    }
}
