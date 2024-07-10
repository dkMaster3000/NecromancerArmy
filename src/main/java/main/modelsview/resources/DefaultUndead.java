package main.modelsview.resources;

import main.models.undead.Undead;
import main.models.undead.UndeadParameters;

public enum DefaultUndead {
    ZOMBIE(new UndeadParameters(
            "Zombie",
            12,
            0,
            "1d6 Pro Zombie im Stack",
            1,
            0,
            0,
            " infizieren Ziele, bei Tod eines infizierten Ziels wird die Leiche dem Stack hinzugefügt",
            false)),
    SKELETAL_WARRIOR(new UndeadParameters(
            "Skelettkrieger",
            6,
            0,
            "1d8 Pro Skelettkrieger im Stack",
            1,
            0,
            0,
            "10% DR",
            false)),
    SKELETAL_ARCHER(new UndeadParameters(
            "Skelettschütze",
            6,
            0,
            "1d8 Pro Skelettschütze im Stack",
            0,
            1,
            0,
            "25% Dodge",
            false)),
    BANSHEE(new UndeadParameters(
            "Banhee",
            12,
            0,
            "",
            0,
            0,
            2,
            "Seelenentzug: Fügt dem Ziel 1d20+Wissen des Stacks Schaden zu, 7 Range, Das Ziel ist markiert und die Seele des Ziels wird als Banshee dem Stack nach dem Tod hinzugefügt",
            false)),
    REAPER(new UndeadParameters(
            "Sensenmann",
            80,
            15,
            "1d12 + GES des Stacks",
            0,
            4,
            0,
            "Ermöglicht fliegen, Angriffe fügen 15% des gewürfelten Schadens als zusätzlichen True Damage Schaden zu, Getötete Ziele werden zu Banshees",
            true)),
    LICH(new UndeadParameters(
            "Lich",
            80,
            15,
            "1d12+Int des Stacks",
            0,
            0,
            4,
            "",
            true)),
    MONSTRO(new UndeadParameters(
            "Monstrosität",
            120,
            20,
            "1d20+Stärke des Stacks",
            6,
            0,
            0,
            "Feinde in der Nähe der Monstrosität werden infiziert, infizierte Feinde erhalten zu Beginn jeder Runde 5% MAX HP Schaden, der Stack erhält DR und macht % mehr Schaden in Höhe der Gesamteinheiten im Stack(Max 30%)",
            true)),
    BONE_DRAGON(new UndeadParameters(
            "Knochendrache",
            120,
            20,
            "1d20+STK+INT+Ges des Stacks",
            2,
            2,
            2,
            "Ermöglicht fliegen, Angriffe verlangsamen Gegner um 1, Getötete Feinde werden als Skelettkrieger oder Skelettschütze dem Stack hinzugefügt, Stackeigenschaften von Skeletten werde verdoppelt, Schaden wird mit jeder Einheit im Stack um 2% erhöht (Max 50)",
            true));

    private final Undead undead;

    DefaultUndead(UndeadParameters parameters) {
        // Create each undead instance here
        undead = new Undead(parameters);
    }

    public Undead getUndead() {
        return undead;
    }
}
