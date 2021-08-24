package main;

import cui.MunchkinApplicatie;

/**
 *
 * @author stijn
 */
public class StartUpCUI {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        MunchkinApplicatie ma = new MunchkinApplicatie();

        ma.startNieuwSpel();
        ma.speelSpel();
    }

}
