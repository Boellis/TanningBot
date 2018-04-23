package Boellis_Tanner.tasks;

import Boellis_Tanner.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Npc;
import Boellis_Tanner.Antiban;

import java.util.concurrent.Callable;

public class TanHides extends Task{

    public static int ELLIS = 3231;
    public Component greenHideImg  = ctx.widgets.component(324,104);
    public Component greenHideName  = ctx.widgets.component(324,112);
    public Component greenHidePrice  = ctx.widgets.component(324,120);

    public Component blackHideImg  = ctx.widgets.component(324,107);
    public Component blackHideName  = ctx.widgets.component(324,115);
    public Component blackHidePrice  = ctx.widgets.component(324,123);


    public Component exitButton = ctx.widgets.component(324,89);

    Antiban antiban = new Antiban(new ClientContext(ctx));

    public static final Tile EllisTile = new Tile(3273, 3192, 0);


    public static final int GREENDRAGONHIDE = 1753;
    public static final int GREENDRAGONLEATHER = 1745;

    final Npc Ellis = ctx.npcs.select().id(ELLIS).select().nearest().poll();




    public TanHides(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean active() {
        //checks to see if the player is within a certain amount of tiles from the specified tile
        //return tile(3274,3192).distanceTo(ctx.players.local()) < 6;
//        return((EllisTile.distanceTo(ctx.players.local()) < 6) && (ctx.inventory.select().id(GREENDRAGONHIDE).count() > 26));
        return((Ellis.tile().distanceTo(ctx.players.local()) < 5) && (ctx.inventory.select().id(GREENDRAGONHIDE).count() > 26)) || ((EllisTile.distanceTo(ctx.players.local()) < 5)&& (ctx.inventory.select().id(GREENDRAGONHIDE).count() > 26));



    }

    @Override
    public void execute() {
        System.out.println("Attemping to Interact with Ellis");
        ellisInteraction();
        System.out.println("Traded with Ellis");

    }

    public void ellisInteraction(){
        final Npc Ellis = ctx.npcs.select().id(ELLIS).select().nearest().poll();
        if(Ellis.inViewport()){
            System.out.println("Attempting to right click Ellis");
            Ellis.click(false);
            System.out.println("Successfully right clicked Ellis");
            Ellis.interact("Trade");
            //Condition.sleep(600);
            greenHideName.hover();
            System.out.println("Attemping to right click green hide image");
            //true to click left, false to click right
            greenHideName.click(false);
            System.out.println("Successfully right clicked green hide image");
            Condition.sleep(2200);
        } else if(!Ellis.inViewport()) {
            System.out.println("Turned to Ellis");
            ctx.camera.turnTo(Ellis);
            Condition.sleep(800);
            Ellis.interact("Trade");
        }


        if(greenHideImg.visible()){
            Condition.sleep(org.powerbot.script.Random.nextInt(250, 300));
            greenHideImg.interact("Tan All");
            System.out.println("Attempting to tan all");
        } else{
            antiban.antiban(2300,2400);

        }

        //        Waits .3 seconds interacting with Ellis to check and see if we need to trade him again
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {

                return ctx.inventory.select().id(GREENDRAGONLEATHER).count() > 26;

            }
        }, 300, 5);


    }

}
