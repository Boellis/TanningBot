package Boellis_Tanner.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import Boellis_Tanner.Task;
import Boellis_Tanner.Walker;
import Boellis_Tanner.Antiban;

public class Walk extends Task {

    public static final Tile pathtoBank1[] = {new Tile(3269, 3167, 0), new Tile(3272, 3167, 0), new Tile(3274, 3170, 0), new Tile(3275, 3173, 0), new Tile(3275, 3176, 0), new Tile(3277, 3179, 0), new Tile(3279, 3182, 0), new Tile(3280, 3185, 0), new Tile(3280, 3188, 0), new Tile(3279, 3191, 0), new Tile(3276, 3190, 0), new Tile(3273, 3192, 0)};
    public static final Tile pathtoBank2[] = {new Tile(3269, 3168, 0), new Tile(3273, 3167, 0), new Tile(3275, 3171, 0), new Tile(3275, 3175, 0), new Tile(3277, 3179, 0), new Tile(3280, 3183, 0), new Tile(3280, 3187, 0), new Tile(3279, 3191, 0), new Tile(3275, 3191, 0)};
    public static final Tile pathtoBank3[] = {new Tile(3269, 3164, 0), new Tile(3273, 3167, 0), new Tile(3275, 3172, 0), new Tile(3276, 3177, 0), new Tile(3279, 3182, 0), new Tile(3280, 3187, 0), new Tile(3277, 3191, 0), new Tile(3272, 3191, 0)};
    public static final Tile pathtoBank4[] = {new Tile(3269, 3167, 0), new Tile(3273, 3167, 0), new Tile(3275, 3171, 0), new Tile(3275, 3175, 0), new Tile(3277, 3179, 0), new Tile(3280, 3182, 0), new Tile(3281, 3186, 0), new Tile(3281, 3190, 0), new Tile(3276, 3191, 0), new Tile(3272, 3192, 0)};
    public static final Tile pathtoBank5[] = {new Tile(3269, 3166, 0), new Tile(3273, 3169, 0), new Tile(3275, 3174, 0), new Tile(3277, 3179, 0), new Tile(3280, 3183, 0), new Tile(3281, 3189, 0), new Tile(3276, 3191, 0)};
    public static final Tile pathtoBank6[] = {new Tile(3269, 3169, 0), new Tile(3272, 3167, 0), new Tile(3275, 3165, 0), new Tile(3276, 3168, 0), new Tile(3276, 3171, 0), new Tile(3276, 3174, 0), new Tile(3277, 3177, 0), new Tile(3277, 3180, 0), new Tile(3280, 3183, 0), new Tile(3280, 3186, 0), new Tile(3280, 3189, 0), new Tile(3277, 3191, 0), new Tile(3274, 3191, 0)};
    public static final Tile pathtoBank7[] = {new Tile(3269, 3166, 0), new Tile(3272, 3166, 0), new Tile(3273, 3169, 0), new Tile(3275, 3172, 0), new Tile(3275, 3175, 0), new Tile(3276, 3178, 0), new Tile(3278, 3181, 0), new Tile(3280, 3184, 0), new Tile(3280, 3187, 0), new Tile(3280, 3190, 0), new Tile(3277, 3191, 0), new Tile(3274, 3191, 0), new Tile(3277, 3193, 0)};
    public static final Tile pathtoBank8[]= {new Tile(3271, 3168, 0), new Tile(3275, 3171, 0), new Tile(3276, 3175, 0), new Tile(3279, 3178, 0), new Tile(3282, 3181, 0), new Tile(3282, 3185, 0), new Tile(3282, 3189, 0), new Tile(3278, 3191, 0), new Tile(3274, 3191, 0)};
    public static final Tile pathtoBank9[] = {new Tile(3270, 3165, 0), new Tile(3273, 3169, 0), new Tile(3275, 3174, 0), new Tile(3277, 3179, 0), new Tile(3280, 3183, 0), new Tile(3280, 3188, 0), new Tile(3276, 3191, 0)};
    public static final Tile pathtoBank10[] = {new Tile(3271, 3170, 0), new Tile(3275, 3171, 0), new Tile(3275, 3175, 0), new Tile(3278, 3179, 0), new Tile(3281, 3182, 0), new Tile(3282, 3186, 0), new Tile(3281, 3190, 0), new Tile(3277, 3191, 0), new Tile(3273, 3192, 0)};
    public static final Tile pathtoBank11[] = {new Tile(3276, 3191, 0), new Tile(3280, 3189, 0), new Tile(3280, 3185, 0), new Tile(3280, 3181, 0), new Tile(3280, 3177, 0), new Tile(3277, 3173, 0), new Tile(3275, 3169, 0), new Tile(3271, 3167, 0)};


    private final Walker walker = new Walker(ctx);

    public static final int randomEnergyLevel = Random.nextInt(70,99);

    public static int pathRandomizer = Random.nextInt(1,10);

    public static int GREEN_DHIDE = 1753;
    public static int GREEN_DLEATHER = 1745;
    public static int BLACK_DHIDE = 1747;
    public static int BLACK_LEATHER = 2509;


    Antiban antiban = new Antiban(new ClientContext(ctx));

    public Walk(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean active() {
        //If the inventory is full or its not full and we're not at the rocks, go to bank
        return ctx.inventory.select().id(GREEN_DHIDE).count() > 26 || ((ctx.inventory.select().id(GREEN_DLEATHER).count() > 26 && pathtoBank1[0].distanceTo(ctx.players.local()) > 3));
    }

    @Override
    public void execute() {

        if(!ctx.movement.running() && ctx.movement.energyLevel()> Random.nextInt(40,60)){
            ctx.movement.running(true);
        }

        //If we're not already walking, or we have no destination, or if our destination is close to us,
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            //If the inventory is full, walk to bank
            if(ctx.inventory.select().id(GREEN_DHIDE).count() > 26){
                switch(pathRandomizer){
                    default:
                        //Sleeps for a time specified between random integers ranging from 900 to 2500 milliseconds
                        antiban.antiban(1300,3000);
                    break;

                    case 1:
                        if(pathRandomizer == 1){
                            System.out.println("Selected Path 1");
                            walker.walkPath(pathtoBank1);
                            pathRandomizer += 1;
                            antiban.antiban(2000,3000);
                        } break;
                    case 2:
                        if (pathRandomizer == 2) {
                            System.out.println("Selected Path 2");
                            walker.walkPath(pathtoBank2);
                            pathRandomizer += 2;
                            antiban.antiban(2000,3000);
                        } break;
                    case 3:
                        if(pathRandomizer == 3){
                            System.out.println("Selected Path 3");
                            walker.walkPath(pathtoBank3);
                            pathRandomizer -= 2;
                            antiban.antiban(2000,3000);
                } break;
                    case 4:
                        if (pathRandomizer == 4) {
                            System.out.println("Selected Path 4");
                            walker.walkPath(pathtoBank4);
                            pathRandomizer += 4;
                            antiban.antiban(2000,3000);
                } break;
                    case 5:
                        if(pathRandomizer == 5){
                            System.out.println("Selected Path 5");
                            walker.walkPath(pathtoBank5);
                            pathRandomizer += 4;
                            antiban.antiban(2000,3000);
                } break;
                    case 6:
                        if (pathRandomizer == 6) {
                            System.out.println("Selected Path 6");
                            walker.walkPath(pathtoBank6);
                            pathRandomizer += 4;
                            antiban.execute(2000,3000);
                } break;
                    case 7:
                        if(pathRandomizer == 7){
                    System.out.println("Selected Path 7");
                    walker.walkPath(pathtoBank7);
                    pathRandomizer -= 1;
                    antiban.execute(2000,3000);
                } break;
                    case 8:
                 if (pathRandomizer == 8) {
                    System.out.println("Selected Path 8");
                    walker.walkPath(pathtoBank8);
                    pathRandomizer -= 3;
                    antiban.execute(2000,3000);
                }break;
                    case 9:
                        if(pathRandomizer == 9){
                    System.out.println("Selected Path 9");
                    walker.walkPath(pathtoBank9);
                    pathRandomizer -= 2;
                    antiban.execute(2000,3000);
                } break;
                    case 10:
                        if (pathRandomizer == 10) {
                    System.out.println("Selected Path 10");
                    walker.walkPath(pathtoBank10);
                    pathRandomizer -= 7;
                    antiban.antiban(2000,3000);
                } break;
                }
            } else {
                switch(pathRandomizer){
                    default:
                        //Sleeps for a time specified between random integers ranging from 900 to 2500 milliseconds
                        Condition.sleep(org.powerbot.script.Random.nextInt(900, 2500));
                        break;
                    case 1:
                        if(pathRandomizer == 1){
                            System.out.println("Selected reversePath 1");
                            walker.walkPathReverse(pathtoBank1);
                            pathRandomizer += 4;
                            antiban.antiban(2000,3000);
                        } break;
                    case 2:
                        if (pathRandomizer == 2) {
                            System.out.println("Selected reversePath 2");
                            walker.walkPathReverse(pathtoBank2);
                            pathRandomizer += 1;
                            antiban.antiban(2000,3000);
                        } break;
                    case 3:
                        if(pathRandomizer == 3){
                            System.out.println("Selected reversePath 3");
                            walker.walkPathReverse(pathtoBank3);
                            pathRandomizer += 7;
                            antiban.antiban(2000,3000);
                        } break;
                    case 4:
                        if (pathRandomizer == 4) {
                            System.out.println("Selected reversePath 4");
                            walker.walkPathReverse(pathtoBank11);
                            pathRandomizer -= 3;
                            antiban.antiban(2000,3000);
                        } break;
                    case 5:
                        if(pathRandomizer == 5){
                            System.out.println("Selected reversePath 5");
                            walker.walkPathReverse(pathtoBank5);
                            pathRandomizer += 2;
                            antiban.antiban(2000,3000);
                        } break;
                    case 6:
                        if (pathRandomizer == 6) {
                            System.out.println("Selected reversePath 6");
                            walker.walkPathReverse(pathtoBank6);
                            pathRandomizer += 3;
                            antiban.antiban(2000,3000);
                        } break;
                    case 7:
                        if(pathRandomizer == 7){
                            System.out.println("Selected reversePath 7");
                            walker.walkPathReverse(pathtoBank7);
                            pathRandomizer -= 1;
                            antiban.antiban(2000,3000);
                        } break;
                    case 8:
                        if (pathRandomizer == 8) {
                            System.out.println("Selected reversePath 8");
                            walker.walkPathReverse(pathtoBank8);
                            pathRandomizer -= 6;
                            antiban.antiban(2000,3000);
                        }break;
                    case 9:
                        if(pathRandomizer == 9){
                            System.out.println("Selected reversePath 9");
                            walker.walkPathReverse(pathtoBank9);
                            pathRandomizer -= 1;
                            antiban.antiban(2000,3000);
                        } break;
                    case 10:
                        if (pathRandomizer == 10) {
                            System.out.println("Selected reversePath 10");
                            walker.walkPathReverse(pathtoBank10);
                            pathRandomizer -= 6;
                            antiban.antiban(4000,5000);
                        } break;
                }// End of switch statement
            }
        }
    }
}
