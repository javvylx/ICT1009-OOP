package com.mycompany.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHandler implements ActionListener{
    GUI ref;
    public ButtonHandler(GUI g) {
        ref=g;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Button Handler for Analysis & News Search Bar
        if(e.getActionCommand().equalsIgnoreCase("Click here for Bar Chart"))// Button for Bar Chart Analysis
        {
                ref.barChart=new BarChartSample();
                ref.barChart.main(null);
        }
        if(e.getActionCommand().equalsIgnoreCase("search"))// News Search Bar Action
        {
            ref.search.setText("Search Found Successfully");
        }
    }
    
}
