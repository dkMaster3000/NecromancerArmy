package main.armyview;

public abstract class ArmyViewUpdateableComponent extends ArmyViewComponentPanel {

    public ArmyViewUpdateableComponent() {
        super();
    }

    //invoke this in ArmyView
    protected void updateComponent() {
        removeAll();

        updateThisComponent();

        revalidate();
        repaint();
    }

    //implement components appearence here
    protected abstract void updateThisComponent();
}
