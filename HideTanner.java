package Boellis_Tanner;

import Boellis_Tanner.tasks.TanHides;
import org.powerbot.script.PaintListener;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.Constants;
import osrs.tasks.*;

import Boellis_Tanner.Task;
import Boellis_Tanner.tasks.Bank;
import Boellis_Tanner.tasks.Walk;
import Boellis_Tanner.Antiban;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="Boellis_Tanner", description="Tans Green,Blue,Red,Black Dragonhide at Al-Kharid",properties="client=4; author=Brandon; topic=999;")


public class HideTanner extends PollingScript<ClientContext> implements PaintListener {

    //Rendering Variable for client GUI
    private final RenderingHints antialiasing = new RenderingHints(
            RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

    //Color Variables for GUI
    private final Color color1 = new Color(153, 153, 153);
    private final Color color2 = new Color(0, 51, 51);
    private final Color color3 = new Color(48, 169, 169);
    private final Color color4 = new Color(51, 0, 51);
    private final Color color5 = new Color(255, 255, 255);
    private final Color color6 = new Color(204, 204, 204);

    //Stroke Variable for GUI
    private final BasicStroke stroke1 = new BasicStroke(1);

    //Font Variables for GUI
    private final Font font1 = new Font("Juice ITC", 1, 25);
    private final Font font2 = new Font("Juice ITC", 1, 20);

    List<Task> taskList = new ArrayList<Task>();

    Antiban antiban = new Antiban(new ClientContext(ctx));


    @Override
    public void start() {
        taskList.add(new TanHides(ctx));
        taskList.add(new Walk(ctx));
        taskList.add(new Bank(ctx));



    }

    @Override
    public void poll() {

        for(Task task : taskList){
            if(ctx.controller.isStopping()){
                break;
            }

            if(task.active()){
                task.execute();
//                antiban.execute(500,800);
                break;
            }
        }

    }

    @Override
    public void repaint(Graphics graphics) {
        long milliseconds = this.getTotalRuntime();
        long seconds = (milliseconds / 1000) % 60;
        long minutes = (milliseconds / (1000 * 60) % 60);
        long hours = (milliseconds / (1000 * 60 * 60)) % 24;


        Graphics2D g = (Graphics2D)graphics;

        g.setColor(color1);
        g.fillRect(5, 6, 216, 138);
        g.setColor(color2);
        g.setStroke(stroke1);
        g.drawRect(5, 6, 216, 138);
        g.setColor(color3);
        g.fillRect(2, 3, 216, 138);
        g.setColor(color4);
        g.drawRect(2, 3, 216, 138);
        g.setFont(font1);
        g.setColor(color5);
        g.drawString("BoellisTanner", 11, 40);
        g.setFont(font2);
        g.drawString("Running: " + String.format("%02d:%02d:%02d", hours, minutes, seconds), 30, 74);
        g.drawString("Profit/Hr:", 28, 108);

    }
    private void sleepTimer(int x){

        //Sleeps for 1 second
        try
        {
            Thread.sleep(x);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
