//Phase 4
public static void main(String[] args){
    SmartScheduler.SmartScheduler scheduler = new SmartScheduler.SmartScheduler(10);
    SmartScheduler.Task low = new SmartScheduler.Task("Low Priority", 1);
    SmartScheduler.Task mid = new SmartScheduler.Task("Mid Priority", 5);
    SmartScheduler.Task high = new SmartScheduler.Task("High Priority", 10);
    //Scenario 1: Insert tasks and find task at the front
    scheduler.insert(low);
    scheduler.insert(high);
    scheduler.insert(mid);
    if(scheduler.peek().getPriority() == 10){
        System.out.println("PASS");
    }
    else{
        System.out.println("FAIL");
    }
    //Scenario 2: Remove tasks
    scheduler.remove();
    if(scheduler.peek().getPriority()==5){
        System.out.println("PASS");
    }
    else{
        System.out.println("FAIL");
    }
}