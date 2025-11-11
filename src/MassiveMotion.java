import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class MassiveMotion extends JPanel implements ActionListener {
    protected Timer tm;

    // Configuration variables
    static int timer_delay, window_size_x, window_size_y, body_size, star_position_x;
    static int star_position_y, star_size;
    static double gen_x, gen_y, body_velocity, star_velocity_x, star_velocity_y;
    static String list_type;

    // List to hold all celestial bodies
    private List<CelestialBody> celestialBodies;
    private Random rand;

    public MassiveMotion(String propfile) {
        // Read configuration file
        readProperties(propfile);

        // Initialize the appropriate list type
        celestialBodies = createList();

        rand = new Random();

        // Create the star and add it to the list
        CelestialBody star = new CelestialBody(star_position_x, star_position_y,
                star_velocity_x, star_velocity_y,
                star_size, true);
        celestialBodies.add(star);

        // Initialize timer with delay from config
        tm = new Timer(timer_delay, this);
    }

    /**
     * Reads configuration from property file and initializes simulation parameters
     * @param filename name of the properties file to read
     */
    private void readProperties(String filename) {
        FileReader fR;
        Properties prop = new Properties();
        try {
            fR = new FileReader(filename);
            prop.load(fR);
            fR.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        timer_delay = Integer.parseInt(prop.getProperty("timer_delay"));
        list_type = prop.getProperty("list");

        window_size_x = Integer.parseInt(prop.getProperty("window_size_x"));
        window_size_y = Integer.parseInt(prop.getProperty("window_size_y"));

        gen_x = Double.parseDouble(prop.getProperty("gen_x"));
        gen_y = Double.parseDouble(prop.getProperty("gen_y"));

        body_size = Integer.parseInt(prop.getProperty("body_size"));
        body_velocity = Double.parseDouble(prop.getProperty("body_velocity"));

        star_position_x = Integer.parseInt(prop.getProperty("star_position_x"));
        star_position_y = Integer.parseInt(prop.getProperty("star_position_y"));
        star_size = Integer.parseInt(prop.getProperty("star_size"));

        star_velocity_x = Double.parseDouble(prop.getProperty("star_velocity_x"));
        star_velocity_y = Double.parseDouble(prop.getProperty("star_velocity_y"));
    }

    /**
     * Creates appropriate List implementation based on configuration
     * @return List instance of the type specified in config file
     */
    private List<CelestialBody> createList() {
        switch(list_type) {
            case "arraylist":
                return new ArrayList<>();
            case "single":
                return new LinkedList<>();
            case "double":
                return new DoublyLinkedList<>();
            case "dummyhead":
                return new DummyHeadLinkedList<>();
            default:
                System.out.println("Invalid list type: " + list_type);
                System.out.println("Valid types are: arraylist, single, double, dummyhead");
                System.exit(1);
                return null;
        }
    }

    /**
     * Paints all celestial bodies on the canvas
     * @param g Graphics object for drawing
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Paint each celestial body
        for (int i = 0; i < celestialBodies.size(); i++) {
            CelestialBody body = celestialBodies.get(i);

            if (body.isStar()) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }

            // Draw oval centered at (x, y) by subtracting the radius
            int diameter = body.getSize() * 2;
            g.fillOval((int)body.getX() - body.getSize(),
                    (int)body.getY() - body.getSize(),
                    diameter, diameter);
        }
        tm.start();
    }

    /**
     * Updates simulation state at each timer tick. Generates new comets,
     * updates positions, and removes out-of-bounds bodies
     * @param actionEvent the timer event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Generate new comets based on probabilities
        generateNewComets();

        // Update positions and remove out-of-bounds bodies
        for (int i = celestialBodies.size() - 1; i >= 0; i--) {
            CelestialBody body = celestialBodies.get(i);
            body.updatePosition();

            if (body.isOutOfBounds(window_size_x, window_size_y)) {
                celestialBodies.remove(i);
            }
        }

        repaint();
    }

    /**
     * Generates new comets at canvas edges based on probability settings
     */
    private void generateNewComets() {
        // Generate from x-axis (top or bottom)
        if (rand.nextDouble() < gen_x) {
            double x = rand.nextDouble() * window_size_x;
            double y = rand.nextBoolean() ? 0 : window_size_y;
            double vx = randomVelocity();
            double vy = randomVelocity();

            CelestialBody comet = new CelestialBody(x, y, vx, vy, body_size, false);
            celestialBodies.add(comet);
        }

        // Generate from y-axis (left or right)
        if (rand.nextDouble() < gen_y) {
            double x = rand.nextBoolean() ? 0 : window_size_x;
            double y = rand.nextDouble() * window_size_y;
            double vx = randomVelocity();
            double vy = randomVelocity();

            CelestialBody comet = new CelestialBody(x, y, vx, vy, body_size, false);
            celestialBodies.add(comet);
        }
    }

    /**
     * Generates a random velocity value in the configured range, excluding zero
     * @return random velocity between -body_velocity and +body_velocity
     */
    private double randomVelocity() {
        double vel = rand.nextDouble() * body_velocity * 2 - body_velocity;
        // Exclude 0
        if (vel == 0) {
            vel = body_velocity;
        }
        return vel;
    }

    /**
     * Main method to start the Massive Motion simulation
     * @param args command line arguments, first argument should be property file name
     */
    public static void main(String[] args) {
        System.out.println("Massive Motion starting...");
        MassiveMotion mm = new MassiveMotion(args[0]);
        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(window_size_x, window_size_y);
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}