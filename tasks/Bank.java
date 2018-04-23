package Boellis_Tanner.tasks;

import Boellis_Tanner.Antiban;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import Boellis_Tanner.Task;
import org.powerbot.script.Random;


import java.util.concurrent.Callable;

public class Bank extends Task{

    public static int randomizer1 = Random.nextInt(200,300);
    public static int withdrawRandomizer = Random.nextInt(27,495);
    public static final int GREENDRAGONHIDE = 1753;
    public static final int GREENDRAGONLEATHER = 1745;

    Antiban antiban = new Antiban(new ClientContext(ctx));


    public Bank(ClientContext ctx) {
        super(ctx);
    }


    @Override
    public boolean active() {
        //If the inventory is full and the nearest bank is within 6 spaces of the player, open the bank
        return ctx.inventory.select().id(GREENDRAGONLEATHER).count() > 26 && (ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 6);
    }

    @Override
    public void execute() {
        ctx.bank.open();
        if(ctx.bank.opened()){
            Condition.sleep(org.powerbot.script.Random.nextInt(250, 600));
            ctx.bank.depositInventory();
            Condition.sleep(org.powerbot.script.Random.nextInt(8000, 1100));
            ctx.bank.withdraw(GREENDRAGONHIDE, withdrawRandomizer);
            Condition.sleep(org.powerbot.script.Random.nextInt(950, 1250));
            ctx.bank.close();
        } else {
            if(ctx.bank.inViewport()){
                if(ctx.bank.open()){
                    //Breaks when the bank has been opened and sleeps for 5 seconds between clicks
                    Condition.wait(new Callable<Boolean>() {
                        @Override
                        public Boolean call() throws Exception {
                            return ctx.bank.opened();
                        }
                    }, randomizer1, 20);
                }
            } else {
                System.out.println("Made it to else statement in banking");
                ctx.camera.turnTo(ctx.bank.nearest());
                antiban.execute(300,500);
            }
        }
        Condition.sleep(org.powerbot.script.Random.nextInt(300, 850));
    }
}
