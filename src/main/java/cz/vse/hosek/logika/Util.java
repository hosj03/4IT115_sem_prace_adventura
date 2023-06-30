package cz.vse.hosek.logika;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;

/**
 * Třída Util - implemetuje metody zobrazObrazekk, drawImageScale a vypisStringNazvu
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Jakub Hošek
 * @version 25. 5. 2020
 */
public class Util {

    /**
     * Metoda zobrazObrazek pro zobrazení obrázku.
     *
     * @param cesta cesta věci, k souboru požadovaného obrázku
     */
    public static void zobrazObrazek(String cesta) {
        BufferedImage obrazek = null;
        try {
            obrazek = ImageIO.read(Util.class.getResource(cesta));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (obrazek != null) {
            Dimension velikostObrazovky = Toolkit.getDefaultToolkit().getScreenSize();
            int vyskaObrazovky = velikostObrazovky.height;
            int sirkaObrazovky = velikostObrazovky.width;

//            if (obrazek.getWidth() > sirkaObrazovky || obrazek.getHeight() > vyskaObrazovky) {
//                scaledImg = obrazek.getScaledInstance(sirkaObrazovky - obrazek.getWidth()/20,
//                        vyskaObrazovky - obrazek.getHeight()/20, Image.SCALE_DEFAULT);
//            } else {
//                scaledImg =obrazek.getScaledInstance(obrazek.getWidth(), obrazek.getHeight(), Image.SCALE_DEFAULT);
//            }

            ImageIcon icon = new ImageIcon(obrazek);
            JFrame frame = new JFrame();
            frame.setLayout(null);
            frame.setSize(sirkaObrazovky, vyskaObrazovky);
            JLabel lbl = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    ImageIcon icon = (ImageIcon) getIcon();
                    if (icon != null) {
                        drawScaledImage(icon.getImage(), this, g);
                    }
                }
            };
            lbl.setSize(sirkaObrazovky, vyskaObrazovky - 70);
            lbl.setIcon(icon);
            frame.add(lbl);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }

    /**
     * Metoda drawScalesImage je použita na přepsání metody paintComponent v JLabel.
     * Tato medota přizpůsobí obrázek velikosti obrazovky.
     *
     * @param image zobrazovaný obrázek
     * @param canvas objekt Component
     * @param g objekt Graphics
     */
    public static void drawScaledImage(Image image, Component canvas, Graphics g) {
        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);

        double imgAspect = (double) imgHeight / imgWidth;

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        double canvasAspect = (double) canvasHeight / canvasWidth;

        int x1 = 0; // top left X position
        int y1 = 0; // top left Y position
        int x2 = 0; // bottom right X position
        int y2 = 0; // bottom right Y position

        if (imgWidth < canvasWidth && imgHeight < canvasHeight) {
            // the image is smaller than the canvas
            x1 = (canvasWidth - imgWidth)  / 2;
            y1 = (canvasHeight - imgHeight) / 2;
            x2 = imgWidth + x1;
            y2 = imgHeight + y1;

        } else {
            if (canvasAspect > imgAspect) {
                y1 = canvasHeight;
                // keep image aspect ratio
                canvasHeight = (int) (canvasWidth * imgAspect);
                y1 = (y1 - canvasHeight) / 2;
            } else {
                x1 = canvasWidth;
                // keep image aspect ratio
                canvasWidth = (int) (canvasHeight / imgAspect);
                x1 = (x1 - canvasWidth) / 2;
            }
            x2 = canvasWidth + x1;
            y2 = canvasHeight + y1;
        }

        g.drawImage(image, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
    }


    /**
     * Metoda pro výpis Stringů názvů.
     *
     * @param collection pole vypisovaných věcí
     * @param <T>
     * @return zpráva, kterou vypíše hra hráči
     */
    public static <T extends INazev> String vypisStringNazvu(Collection<T> collection) {
        String seznamVeci = "";
        boolean prvni = true;
        for (T nazev: collection) {
            if (prvni) {
                seznamVeci += nazev.getNazev();
                prvni = false;
            } else {
                seznamVeci += ", " + nazev.getNazev();
            }
        }
        return seznamVeci;
    }
}
