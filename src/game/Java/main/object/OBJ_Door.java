package main.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject {

    /**
     * Constructs a new door object.
     */
    public OBJ_Door() {
        super();

        setName("Door");
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/objects/door.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "OBJ_Door{}";
    }
}
